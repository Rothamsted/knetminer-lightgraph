package uk.ac.rothamsted.knetminer.lightgraphs.benchmark;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

import picocli.CommandLine.Command;

@Command(name="data-modification")
public class DataModificationBenchmark extends Benchmark {
	
	private long totalDelete;	
	private long totalModify;
	private long totalAdd;
	private String generatedValue;
	private int deleteCounter;
	private int modifyCounter;
	private int addCounter;

	public void printReport() {
		System.out.println("--- Data Modification ---");
		System.out.println("Total time taken to complete " + deleteCounter + " 'delete' operations: " + totalDelete + "ms");
		System.out.println("Total time taken to complete " + modifyCounter + " 'modify' operations: " + totalModify + "ms");
		System.out.println("Total time taken to complete " + addCounter + " 'add' opertaions: " + totalAdd + "ms");	
	}

	@Override
	public void runBenchmark() {		
		generatedValue = "";
		totalDelete = 0;
		deleteCounter = 0;
		totalModify = 0;
		modifyCounter = 0;
		totalAdd = 0;
		addCounter = 0;
		
		for ( int nTest = 0; nTest != testCount; nTest++ ) 
		{
			int operationID = ThreadLocalRandom.current().nextInt(0, 3);
			if (operationID == 0) {
				//delete		
				int positionDel = ThreadLocalRandom.current().nextInt(0,(data.size()));
				long start = System.currentTimeMillis();
				data.remove(positionDel);
				long finish = System.currentTimeMillis();
				totalDelete += (finish - start);
				deleteCounter ++;
			} else if (operationID == 1) {
				//modify
				int positionMod = ThreadLocalRandom.current().nextInt(0,(data.size()));
				generatedValue = RandomStringUtils.randomAlphanumeric(stringMinLen, stringMaxLen);
				long start = System.currentTimeMillis();
		    	data.replace(positionMod, generatedValue);
		    	long finish = System.currentTimeMillis();
		    	totalModify += (finish - start);
		    	modifyCounter ++;
			} else {
				//add
				generatedValue = RandomStringUtils.randomAlphanumeric(stringMinLen, stringMaxLen);
				int positionAdd = data.size();
				long start = System.currentTimeMillis();
				data.put(positionAdd, generatedValue);
				long finish = System.currentTimeMillis();
				totalAdd += (finish - start);
				addCounter ++;
			}	    
		}
	}
}