
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
	
	// TODO: I don't get it: what are these for?
	// It should be like the loop in StringsPopulationBenchmark.main():
	//   for i = 1..n: generate key, generate value, add key/value to the map, count the time
	private ArrayList<String> dataKey;
	private ArrayList<String> dataValue;
	
	// TODO: these are needed inside a loop, so, very local, they shouldn't be class fields
	// printReport doesn't need to do the calculations, createData() or runBenchmark() can do that in advance
	// and save the result in timeElapsed
	// Moreover, consider the more expressive StopWatch: https://www.oreilly.com/library/view/jakarta-commons-cookbook/059600706X/ch01s20.html 
	private long start;
	private long finish;
	// TODO: Likely, timeElapsed is specific of the subclasses that need it, some of them will need to report different
	// total times, eg, 'Data modification benchmark' 
	private long timeElapsed;
	
	abstract void createData();
	
	// TODO: in general, I don't expect this to return a result. It's supposed to print something, maybe it would
	// make sense to return something like a string, but for the moment I'd go with void
	// 
	abstract long printReport();
	
	public void runBenchmark() {
		//TODO
	}
	
	// TODO: this should be a separated file
	class StringsPopulationBenchmark extends Benchmark {
		void createData() {
			for (int i=0; i<5; i++) {
				int minLen = 3;
				int maxLen = 7;
				start = System.currentTimeMillis();
				String generatedValue = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
				String generatedKey = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
				dataKey.add(generatedKey);
				dataValue.add(generatedValue);
				finish = System.currentTimeMillis();
			}
		}

		long printReport() {
			timeElapsed = timeElapsed + (finish - start);
			return timeElapsed;
		}
	}
	
//	class RetrievalBenchmark extends Benchmark {
//		TODO
//	}
	
//	class ExistenceBenchmark extends Benchmarks {
//		TODO
//	}
	
	// TODO: These classes shouldn't have any main()
	// We should have a BenchmarkRunner having a main() that instantiate a benchmark and calls benchmark.runBenchmark()
	//
	public static void main(String[] args) {

		// TODO: this is common initialisation, ie, goes into some method like Benchmark.init()
		// Not sure you need it as a class field (or a protected class field, see notes on data below), initially
		// makes it so and we can review it later.
		//
		DB db = DBMaker
				.fileDB("file.db")
				.fileDeleteAfterClose()
				.make();
		
		// TODO: Likely, data will be used by all the classes, so it would be good to have this as a field
		// of Benchmark. Also Make it protected so that all subclasses can see it (again, review Java visibility rules/mofifiers)
		HTreeMap<String,String> data = db
				.hashMap("data", Serializer.STRING, Serializer.STRING)
				.counterEnable()
				.createOrOpen();
		
		// This is wrong cause StringsPopulationBenchmark should be defined in an independent .java file
		//
		Benchmark benchmark = new StringsPopulationBenchmark();
		benchmark.runBenchmark(); //to complete the runBenchmark() abstract method
		data.close();

	}
	

}
