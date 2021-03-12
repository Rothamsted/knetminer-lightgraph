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

@Command ( 
	subcommands = { 
		StringsPopulationBenchmark.class,
		RetrievalBenchmark.class,
		ExistenceRetrievalBenchmark.class,
		DataModificationBenchmark.class
	}
)
public class Benchmark implements Runnable {
	/**
	 * this sets the package visibility, so that only BenchmarkRunner can invoke this top level class.
	 * We want this because it isn't useful elsewhere.
	 */
	Benchmark ()
	{
	}
	
	protected long timeElapsed;
	protected HTreeMap<Integer,String> data;
	protected int stringMinLen;
	protected int stringMaxLen;
	
	@Option (names={"-s", "--test-size"}, description="The number of MapDB entries to be used for the test")
	protected int testSize;
	
	@Option(names={"-c", "--test-count"}, description="The number of times the certain action is repeated")
	protected int testCount;
	
	protected int nTest;
	
	public abstract void createData();
	
	public abstract void printReport();
	
	public abstract void runBenchmark();
	
	public void init() {
		DB db = DBMaker
				.fileDB("target/file.db")
				.fileDeleteAfterClose()
				.make();
		
		data = db
				.hashMap("data", Serializer.INTEGER, Serializer.STRING)
				.counterEnable()
				.createOrOpen();
	}
	
	public void setTestSize(int a) {
		this.testSize = a;
	}
	
	public void setTestCount(int a) {
		this.testCount = a;
	}
		
	public void runAll () {
		stringMinLen = 3;
		stringMaxLen = 7;
		setTestSize(testSize);
		setTestCount(testCount);
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
