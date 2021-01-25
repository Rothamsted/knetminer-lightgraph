
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
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
	// printReport doesn't need to do the calculations, createData() or runBenchmark() can do that in advance
	// and save the result in timeElapsed
	// Moreover, consider the more expressive StopWatch: https://www.oreilly.com/library/view/jakarta-commons-cookbook/059600706X/ch01s20.html 
	private long start;
	private long finish;
	// TODO: Likely, timeElapsed is specific of the subclasses that need it, some of them will need to report different
	// total times, eg, 'Data modification benchmark' 
	private long timeElapsed;
	private HTreeMap<String,String> data;
	
	abstract void createData(); ///Don't believe this is needed in all benchmarks (only needed in StringPop)
	
	// TODO: in general, I don't expect this to return a result. It's supposed to print something, maybe it would
	// make sense to return something like a string, but for the moment I'd go with void
	
	abstract void printReport();
	
	public static void init() {
		DB db = DBMaker
				.fileDB("file.db")
				.fileDeleteAfterClose()
				.make();
		
		HTreeMap<String,String> data = db
				.hashMap("data", Serializer.STRING, Serializer.STRING)
				.counterEnable()
				.createOrOpen();
	}
	
	public void runBenchmark() {
		//TODO
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
