
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
}
