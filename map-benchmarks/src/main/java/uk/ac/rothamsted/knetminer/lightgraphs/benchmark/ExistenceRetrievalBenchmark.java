package uk.ac.rothamsted.knetminer.lightgraphs.benchmark;
  
import java.util.concurrent.ThreadLocalRandom;

import picocli.CommandLine.Command;

@Command(name="existence")
public class ExistenceRetrievalBenchmark extends Benchmark{

	private long totalExistence;
	private long totalFetch;
	private int fetchedCount;
	private long totalStrLen = 0;
	
	
	@Override
	public void runBenchmark() {
		long start = 0;
		long finish = 0;
		totalExistence = 0;
		totalFetch = 0;
		totalStrLen = 0;
		
		for ( int nTest = 0; nTest != testCount; nTest++ ) 
		{
    		int randomNum = ThreadLocalRandom.current().nextInt(0, (data.size()*2) + 1);
    		
    		start = System.currentTimeMillis();
    		boolean doesExist = data.containsKey(randomNum);
    		finish = System.currentTimeMillis();
    		totalExistence += (finish - start);
    		
    		if (doesExist) {
    			start = System.currentTimeMillis();
    			String consumedValue = data.get ( randomNum );
    			finish = System.currentTimeMillis();
    			totalFetch += (finish - start);
    			fetchedCount++;
    			totalStrLen += consumedValue.length ();
    		}     
    		timeElapsed = (totalExistence + totalFetch);
    	}		
	}

	public void printReport() {
		System.out.println("--- Existence & Retrieval ---"); 
		System.out.println("Fetched Values: " + fetchedCount + "/" + testCount);
	  System.out.println("Existence Total Time: " + totalExistence + "ms");
	  System.out.printf ("Existence Average Time: %.2f ms/key", 1d * totalExistence / testCount );
	  
		System.out.println("Fetch Total Time: " + totalFetch + "ms");
	  System.out.printf ("Fetch Average Time: %.2f ms/key", 1d * totalFetch / fetchedCount );

	  System.out.println("Total time: " + timeElapsed + "ms");		
	  System.out.println("Total string length: " + totalStrLen );		
	}

}