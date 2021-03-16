package uk.ac.rothamsted.knetminer.lightgraphs.benchmark;
  
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

import picocli.CommandLine.Command;

@Command(name="existence")
public class ExistenceRetrievalBenchmark extends Benchmark{

	private long totalExistence;
	private long totalFetch;
	private List<String> list;
	
	@Override
	public void runBenchmark() {
		long start = 0;
		long finish = 0;
		totalExistence = 0;
		totalFetch = 0;
		list = new ArrayList<String>();
		
		while (nTest != testCount) {
    		int randomNum = ThreadLocalRandom.current().nextInt(0, (data.size()*2) + 1);
    		
    		start = System.currentTimeMillis();
    		boolean doesExist = data.containsKey(randomNum);
    		finish = System.currentTimeMillis();
			totalExistence += (finish - start);
    		
    		if (doesExist) {
    			start = System.currentTimeMillis();
    			String consumedValue = data.get(randomNum);
    			finish = System.currentTimeMillis();
    			totalFetch += (finish - start);
    			list.add(consumedValue);
    		} else {
    			//
    		}	    
    		timeElapsed = (totalExistence + totalFetch);
    		nTest ++;
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