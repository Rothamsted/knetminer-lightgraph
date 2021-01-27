
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

/** 
 * 
 * TODO: comment me!
 * 
 * TODO: It's not good that everything stays in the default package. Move stuff into proper
 * packages, eg, uk.ac.rothamsted.knetlight.benchmark.* 
 * References: 
 *   https://www.baeldung.com/java-packages
 *   https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html
 * 
 */
public abstract class Benchmark {
	
	protected long timeElapsed;
	protected HTreeMap<Integer,String> data;
	
	abstract void createData();
	
	abstract void printReport();
	
	public void init() 
	{
		DB db = DBMaker
				.fileDB("target/file.db")
				.fileDeleteAfterClose()
				.make();
		
		data = db
				.hashMap("data", Serializer.INTEGER, Serializer.STRING)
				.counterEnable()
				.createOrOpen();
	}
	
	public void runBenchmark() {
		init();
		createData();
		printReport();
		data.close();
	}

}
