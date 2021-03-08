
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class ExistenceRetrievalBenchmark extends Benchmark{

	private long totalExistence;
	private long totalFetch;
	private int timesFetched;
	
	public void createData() {
		for (int i=0; i<testSize; i++) {
			int index = data.size();
			String generatedValue = RandomStringUtils.randomAlphanumeric(stringMinLen, stringMaxLen);
			data.put(index, generatedValue);
		}		    
	}
	
	@Override
	public void runBenchmark() {
		long start = 0;
		long finish = 0;
		totalExistence = 0;
		totalFetch = 0;
		timesFetched = 0;
		
		while (nTest != testCount) {
    		int randomNum = ThreadLocalRandom.current().nextInt(0, (data.size()*2) + 1);
   
    		// TODO: see if like this more:
    		// start = <currentTime>
    		// boolean doesExist = data.containsKey(randomNum)
    		// totalExistence += <currentTime> - start
    		// // var += expr is a shorthand for: var = var + <expr>, but write it in the form you prefer (ref: https://www.w3schools.com/java/java_operators.asp)
    		// 
    		// if ( doesExist ) ... 
    		//
    		
    		start = System.currentTimeMillis();
    		boolean doesExist = data.containsKey(randomNum);
    		finish = System.currentTimeMillis();
			totalExistence += (finish - start);

    		if (doesExist) {
    			start = System.currentTimeMillis();
    			String consumedValue = data.get(randomNum);
    			finish = System.currentTimeMillis();
    			totalFetch += (finish - start);
    			timesFetched += 1;
    		} else {
    			//
    		}	    
    		
    		timeElapsed = (totalExistence + totalFetch);
    		nTest += 1;
    	}		
	}

	public void printReport() {
		System.out.println("--- Existence & Retrieval ---"); 
		System.out.println("Data Size: " + testSize);
		System.out.println("Fetched Values: " + timesFetched + "/" + testCount);
	    System.out.println("Existence Total Time: " + totalExistence + "ms");
		System.out.println("Fetch Total Time: " + totalFetch + "ms");
		System.out.println("Total time: " + timeElapsed + "ms");		
	}

}