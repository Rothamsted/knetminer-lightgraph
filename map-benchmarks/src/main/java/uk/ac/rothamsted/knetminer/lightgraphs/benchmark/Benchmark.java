package uk.ac.rothamsted.knetminer.lightgraphs.benchmark;

import org.apache.commons.lang3.RandomStringUtils;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import picocli.CommandLine.Option;

/**
 * 
 *
 * Runnable is required by picocli (in the subclasses), it's the way it recognises a line command, 
 * it invokes Runnable.run().
 * 
 * picocli is able to merge all the annotations (@Command, @Option) in a class hierarchy (class, superclasses). 
 * So, it's a form of inheritance, similar to the regular class inheritance.
 * 
 */
public abstract class Benchmark implements Runnable {
	/**
	 * this sets the package visibility, so that only BenchmarkRunner can invoke this top level class.
	 * We want this because it isn't useful elsewhere.
	 */
	Benchmark ()
	{
	}
	
	//initialises all the global variables
	protected long timeElapsed;
	protected HTreeMap<Integer,String> data;
	
	/** 
	 * Sets the variables to values in-case it's not specified by command line.
	 * 
	 * The common convention is to use single-dash and single-letter for the short version of the option and
	 * double dash for the longer version. 
	 * 
	 **/
	@Option(names={"-l", "--min-len"}, description="The minimum length of randomised strings")
	protected int stringMinLen = 3;
	
	@Option(names={"-h", "--max-len"}, description="The maxium length of the randomised strings")
	protected int stringMaxLen = 100;
	
	/**
	 * Command line way of instantly adding the inputed data into the global variable.
	 */
	@Option(names={"-s", "--test-size"}, description="The number of MapDB entries to be used for the test")
	protected int testSize = 1000;
	
	@Option(names={"-c", "--test-count"}, description="The number of times the certain action is repeated")
	protected int testCount = 1000;
		
	public void createData() {
		for (int i=0; i<testSize; i++) {
			int index = data.size();
			String generatedValue = RandomStringUtils.randomAlphanumeric(stringMinLen, stringMaxLen);
			data.put(index, generatedValue);
		}	
	}
	
	public abstract void printReport();
	
	public abstract void runBenchmark();
	
	public void init() {
		//creates database file
		DB db = DBMaker
				.fileDB("target/file.db")
				.fileDeleteAfterClose()
				.make();
		//creates map within that database file
		data = db
				.hashMap("data", Serializer.INTEGER, Serializer.STRING)
				.counterEnable()
				.createOrOpen();
		
		timeElapsed = 0;
	}
	
	//externally able to set size & enumerations (before the change to command line)
	public void setTestSize(int a) {
		this.testSize = a;
	}
	
	public void setTestCount(int a) {
		this.testCount = a;
	}
	
	/**
	 * Runs the predefined order of operations: {@link #init()}, {@link #createData()}, {@link #runBenchmark()}, 
	 * {@link #printReport()}.
	 * 
	 */
	public void runAll () {
		init();
		createData();
		runBenchmark();
		printReport();
		data.close();
	}


	// Implementing this is required by Runnable
	@Override
	public void run ()
	{
		// Just invoking another method is a way to create method aliases. 
		this.runAll ();
	}
}