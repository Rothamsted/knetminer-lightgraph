package uk.ac.rothamsted.knetminer.lightgraphs.benchmark;

import org.apache.commons.lang3.RandomStringUtils;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

// TODO: these comments are to be removed
//
// Runnable is required by picocli (in the subclasses), it's the way it recognises a line command, 
// it invokes Runnable.run()
//
// picocli is able to merge all the annotations (@Command, @Option) in a class hierarchy (class, superclasses).
// So, it's a form of inheritance, similar to the regular class inheritance.
//
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
	
	//sets the variables to values in-case it's not specified by command line
	@Option(names={"-string-min", "--min-len"}, description="The minimum length of randomised strings")
	protected int stringMinLen = 3;
	
	@Option(names={"-string-max", "--max-len"}, description="The maxium length of the randomised strings")
	protected int stringMaxLen = 100;
	
	//command line way of instantly adding the inputted data into the global variable
	@Option(names={"-s", "--test-size"}, description="The number of MapDB entries to be used for the test")
	protected int testSize;
	
	@Option(names={"-c", "--test-count"}, description="The number of times the certain action is repeated")
	protected int testCount;
	
	protected int nTest;
	
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
	}
	
	//externally able to set size & enumerations (before the change to command line)
	public void setTestSize(int a) {
		this.testSize = a;
	}
	
	public void setTestCount(int a) {
		this.testCount = a;
	}
	
	//sets all global variables and puts methods in order of compilation
	public void runAll () {
		nTest = 0;
		timeElapsed = 0;
		init();
		createData();
		runBenchmark();
		printReport();
		data.close();
	}


	// Implemmenting this is required by Runnable
	@Override
	public void run ()
	{
		// Just invoking another method is a way to create method aliases. 
		this.runAll ();
	}
}