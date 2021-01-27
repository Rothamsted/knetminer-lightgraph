
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

/** 
 * 
 * TODO: comment me!
 *
 * TODO: Review visibility. Many class and methods intended for general use should be public
 * review Java rules for visibility.
 * 
 * TODO: It's not good that everything stays in the default package. Move stuff into proper
 * packages, eg, uk.ac.rothamsted.knetlight.benchmark.* 
 * References: 
 *   https://www.baeldung.com/java-packages
 *   https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html
 * 
 */
public abstract class Benchmark {
	
	// TODO: these are needed inside a loop, so, very local, they shouldn't be class fields
	// Moreover, consider the more expressive StopWatch: https://www.oreilly.com/library/view/jakarta-commons-cookbook/059600706X/ch01s20.html 
	private long start;
	private long finish;
	protected long timeElapsed;
	protected HTreeMap<Integer,String> data;
	
	abstract void createData();
	
	abstract void printReport();
	
	public void init() 
	{
		// TODO: this should be based on a configuration parameter and it's better to put the default
		// in target/ cause this is where Maven sends the generated disposable files (mvn clean deletes such
		// dir).
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
	}
	
	
	// TODO: These classes shouldn't have any main()
	// We should have a BenchmarkRunner having a main() that instantiate a benchmark and calls benchmark.runBenchmark()
	//

		// TODO: this is common initialisation, ie, goes into some method like Benchmark.init()
		// Not sure you need it as a class field (or a protected class field, see notes on data below), initially
		// makes it so and we can review it later.
		// (db)
		
		// TODO: Likely, data will be used by all the classes, so it would be good to have this as a field
		// of Benchmark. Also Make it protected so that all subclasses can see it (again, review Java visibility rules/mofifiers)
		// (HTreeMap)	

}
