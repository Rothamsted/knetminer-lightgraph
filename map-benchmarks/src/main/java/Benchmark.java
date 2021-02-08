
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
		
	public void runAll () {
		stringMinLen = 3;
		stringMaxLen = 7;
		testCount = 200000;
		testSize = 10000;
		nTest = 0;
		timeElapsed = 0;
		init();
		createData();
		runBenchmark();
		printReport();
		data.close();
	}
}
