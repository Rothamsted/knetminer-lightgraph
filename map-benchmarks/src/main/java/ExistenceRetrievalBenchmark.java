
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class ExistenceRetrievalBenchmark extends Benchmark{

	private long totalExistence;
	private long totalFetch;
	private List<String> map;
	
	public void createData() {
		for (int i=0; i<testSize; i++) {
			int index = data.size();
			String generatedValue = RandomStringUtils.randomAlphanumeric(stringMinLen, stringMaxLen);
			data.put(index, generatedValue);
		}		    
	}
	
	@Override
	public void runBenchmark() {		
		long startExistence = 0;
		long startFetch = 0;
		long finishExistence = 0;
		long finishFetch = 0;
		totalExistence = 0;
		totalFetch = 0;
		map = new ArrayList<String>();
		
		while (nTest != testCount) {
    		int randomNum = ThreadLocalRandom.current().nextInt(0, (data.size()*2) + 1);
    	
    	// TODO: when testing with millions of entries you won't be able to afford that much output
    	// One way to fix this is to remove all the println() command, leaving data.get() only
    	// 
    	// A Better alternative is to do that plus reporting a message from time to time, let's say
    	// every 1000 consumedValues. See here on how to do that:
    	// https://www.java67.com/2015/10/how-to-solve-fizzbuzz-in-java.html
    	// (you're going to have only one if in this case, checking if consumedValue is 1000, 2000, etc) 
   
    		startExistence = System.currentTimeMillis();
    		
    		if (data.containsKey(randomNum)) {
    			finishExistence = System.currentTimeMillis();
    			totalExistence = totalExistence + (finishExistence - startExistence);
    			startFetch = System.currentTimeMillis();
    			String consumedValue = data.get(randomNum);
    			finishFetch = System.currentTimeMillis();
    			totalFetch = totalFetch + (finishFetch - startFetch);
    			map.add(consumedValue);
    		} else {
    			finishExistence = System.currentTimeMillis();
    			totalExistence = totalExistence + (finishExistence - startExistence);
    		}	    
    		
    		timeElapsed = totalExistence + totalFetch;
    		nTest = nTest + 1;
    	}		
	}




	public void printReport() {
		System.out.println("--- Existence & Retrieval ---");
		System.out.println("Fetched Values: " + map.size() + "/" + data.size());
	    System.out.println("Existence Total Time: " + totalExistence + "ms");
		System.out.println("Fetch Total Time: " + totalFetch + "ms");
		System.out.println("Total time: " + timeElapsed + "ms");		
	}

}
