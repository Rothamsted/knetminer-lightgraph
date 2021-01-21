
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

abstract class Benchmark {
	private ArrayList<String> dataKey;
	private ArrayList<String> dataValue;
	private long start;
	private long finish;
	private long timeElapsed;
	
	abstract void createData();
	abstract long printReport();
	
	public void runBenchmark() {
		
		
	}
	
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
//		
//	}
	
//	class ExistenceBenchmark extends Benchmarks {
//		
//	}
	
	public static void main(String[] args) {
		DB db = DBMaker
				.fileDB("file.db")
				.fileDeleteAfterClose()
				.make();
		
		HTreeMap<String,String> data = db
				.hashMap("data", Serializer.STRING, Serializer.STRING)
				.counterEnable()
				.createOrOpen();
		
//	    for (Map.Entry<String,String> entry : data1.entrySet()) {
//	    	String k = entry.getKey();
//	    	String v = entry.getValue();
//	    	data.put(k, v);
//	    }
		
//		System.out.println(data);
		
		Benchmark benchmark = new StringsPopulationBenchmark();
		benchmark.runBenchmark();
		data.close();

	}
	

}
