import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.RandomStringUtils;

public class RetrievalBenchmark extends Benchmark{

	private Map<Integer,String> map;

	void createData() {
		
		timeElapsed = 0;
		
		for (int i=0; i<2000; i++) {
			int minLen = 3;
			int maxLen = 7;
			int index = data.size();
			String generatedValue = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
			data.put(index, generatedValue);
		}
		
		map = new HashMap<Integer,String>();
		
	    for (Entry<Integer, String> entry : data.entrySet()) {
	    	long start = System.currentTimeMillis();
	    	Integer k = entry.getKey();
	    	String v = entry.getValue();
	    	map.put(k, v);
	    	long finish = System.currentTimeMillis();
	    	timeElapsed = timeElapsed + (finish - start);	    	
	    }
	}

	void printReport() {
		System.out.println("--- Retrieval ---");
		System.out.println(map);
		System.out.println("Took : " + timeElapsed + "ms");
	}

}
