  
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class ExistenceRetrievalBenchmark extends Benchmark{

	private long totalExistence;
	private long totalFetch;
	private List<String> list;
	
	public void createData() {
		for (int i=0; i<testSize; i++) {
			int index = data.size();
			String generatedValue = RandomStringUtils.randomAlphanumeric(stringMinLen, stringMaxLen);
			data.put(index, generatedValue);
		}		    
	}
	
	@Override
	public void runBenchmark() {
		// TODO: clearly, you need separated total variables (totalExistence, totalFetch), 
		// but you might want to simplify things by using start/end variables, by having just a pair of them,
		// to be used multiple times before/after something to be clocked.
		// (this other way of yours is a bit more verbose/readable, not sure it's so useful here)
		long startExistence = 0;
		long startFetch = 0;
		long finishExistence = 0;
		long finishFetch = 0;
		totalExistence = 0;
		totalFetch = 0;
		list = new ArrayList<String>();
		
		while (nTest != testCount) {
    		int randomNum = ThreadLocalRandom.current().nextInt(0, (data.size()*2) + 1);
    	
    	// TODO: when testing with millions of entries you won't be able to afford that much output
    	// One way to fix this is to remove all the println() command, leaving data.get() only
    	// 
    	// A Better alternative is to do that plus reporting a message from time to time, let's say
    	// every 1000 consumedValues. See here on how to do that:
    	// https://www.java67.com/2015/10/how-to-solve-fizzbuzz-in-java.html
    	// (you're going to have only one if in this case, checking if consumedValue is 1000, 2000, etc) 
   
    		// TODO: see if like this more:
    		// start = <currentTime>
    		// boolean doesExist = data.containsKey(randomNum)
    		// totalExistence += <currentTime> - start
    		// // var += expr is a shorthand for: var = var + <expr>, but write it in the form you prefer (ref: https://www.w3schools.com/java/java_operators.asp)
    		// 
    		// if ( doesExist ) ... 
    		//
    		
    		startExistence = System.currentTimeMillis();
    		boolean doesExist = data.containsKey(randomNum);
    		finishExistence = System.currentTimeMillis();
			totalExistence += (finishExistence - startExistence);
    		
    		if (doesExist) {
    			startFetch = System.currentTimeMillis();
    			String consumedValue = data.get(randomNum);
    			finishFetch = System.currentTimeMillis();
    			totalFetch += (finishFetch - startFetch);
    			list.add(consumedValue);
    		} else {
    			//
    		}	    
    		
    		timeElapsed = (totalExistence + totalFetch);
    		nTest += 1;
    	}		
	}

	public void printReport() {
		System.out.println("--- Existence & Retrieval ---"); 
		System.out.println("Fetched Values: " + list.size() + "/" + testCount);
	    System.out.println("Existence Total Time: " + totalExistence + "ms");
		System.out.println("Fetch Total Time: " + totalFetch + "ms");
		System.out.println("Total time: " + timeElapsed + "ms");		
	}

}