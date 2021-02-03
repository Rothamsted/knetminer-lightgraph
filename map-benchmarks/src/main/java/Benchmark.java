
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

public abstract class Benchmark {
	
	protected long timeElapsed;
	protected HTreeMap<Integer,String> data;
	protected int stringMinLen;
	protected int stringMaxLen;
	protected int testSize;
	protected int testCount;
	protected int nTest;
	
	abstract void createData();
	
	abstract void printReport();
	
	abstract void runBenchmark();
	
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
		
	/**
	 * Runs everything in one go.
	 */
	public void runAll () {
		// TODO: we need to be able to set this from the caller, before runAll() or something
		// ie, we need to be able to write setTestSize( 100 ), set
		stringMinLen = 3;
		stringMaxLen = 7;
		// TODO: see if you prefer better names, eg, dataSize, testRepeats
		testCount = 1000;
		testSize = 1000;
		// TODO: it's not so worth to give a field the scope of a top-level class just because you're going
		// to use many times locally. nTest is only needed during loops, you don't really need to expose
		// it outside and doing it unnecessarily is error-prone
		nTest = 0;
		init();
		createData();
		runBenchmark ();
		printReport();
		data.close();
	}
}
