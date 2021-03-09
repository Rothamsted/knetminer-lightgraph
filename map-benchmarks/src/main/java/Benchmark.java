import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

// TODO: these comments are to be removed
// Runnable is required by picocli, it's the way it recognises a line command, it invokes Runnable.run()
// Moreover, we switched Benchmark from being abstract to a concrete class with dummy methods. This is 
// to be able to use command/subcommands in picocli, which requires a top-level class to be instantiated
// in this case.
//
// picocli is able to merge all the annotations (@Command, @Option) in a class hierarchy (class, superclasses).
// So, it's a form of inheritance, similar to the regular class inheritance.
//
// 
@Command ( 
	subcommands = { 
		StringsPopulationBenchmark.class
		// TODO: annotate add the other subcommands
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
	
	// TODO: remove this comment
	// Because of the inheritance mentioned above, all the Benchmark command lines will have this 
	// common command line argument.
	@Option ( names = { "-s", "--test-size" }, description = "The number of MapDB entries to be used for the test" )
	protected int testSize;

	protected int testCount;
	protected int nTest;

	
	private final static String CONCRETE_IMPL_MSG = "This method needs an actual implementation in the subclass";
	
	// As said above, dummy implementations are provided that return an error, so that the sub-class writer is forced
	// to override them with a real implementation.
	public void createData() {
		throw new UnsupportedOperationException ( CONCRETE_IMPL_MSG );
	}
	
	public void printReport() {
		throw new UnsupportedOperationException ( CONCRETE_IMPL_MSG );
	}
	
	public void runBenchmark() {
		throw new UnsupportedOperationException ( CONCRETE_IMPL_MSG );
	}
	
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
		// TODO: we need to be able to set this from the caller, before runAll() or something
		// ie, we need to be able to write setTestSize( 100 ), set
		stringMinLen = 3;
		stringMaxLen = 7;
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
