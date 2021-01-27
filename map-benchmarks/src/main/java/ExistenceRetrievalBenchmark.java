import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class ExistenceRetrievalBenchmark extends Benchmark{

	private Map<Integer,String> map;
	private int consumedValues;
	
	void createData() {
		timeElapsed = 0;
		for (int i=0; i<100000; i++) {
			int minLen = 3;
			int maxLen = 7;
			int index = data.size();
			String generatedValue = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
			data.put(index, generatedValue);
		}		
	    consumedValues = 0;	    
		map = new HashMap<Integer,String>();
		
	    for (Entry<Integer, String> entry : data.entrySet()) {
	    	Integer k = entry.getKey();
	    	String v = entry.getValue();
	    	map.put(k, v);    	
	    }
	}

	void printReport() {
		System.out.println("--- Existence & Retrieval ---");
		
	    while (consumedValues != 3) {
	    	long start = System.currentTimeMillis();
	    	int randomNum = ThreadLocalRandom.current().nextInt(0, data.size() + 1);
	    	
	    	if (map.containsKey(randomNum)) {
	    		System.out.println("The key of '" + randomNum + "' exists in the map");
	    		System.out.println("The value related to this key is: " + data.get(randomNum));
	    	} else {
	    		System.out.println(randomNum + " isn't a valid key in the map");
	    	}	    
	    	
	    	long finish = System.currentTimeMillis();
	    	timeElapsed = timeElapsed + (finish - start);
	    	consumedValues = consumedValues + 1;
	    }		
	    
		System.out.println("Amount of keys/values in map: " + data.size());
		System.out.println("Amount of times checked for a valid key: " + consumedValues);
		System.out.println("Took : " + timeElapsed + "ms");		
	}
	
	
	

}
