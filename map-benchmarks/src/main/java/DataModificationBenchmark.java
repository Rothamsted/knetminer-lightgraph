
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class DataModificationBenchmark extends Benchmark {
	
	private long totalDelete;	
	private long totalModify;
	private long totalAdd;
	private String generatedValue;
	private int deleteCounter;
	private int modifyCounter;
	private int addCounter;
	
	void createData() {
		for (int i=0; i<testSize; i++) {
			int index = data.size();
			generatedValue = RandomStringUtils.randomAlphanumeric(stringMinLen, stringMaxLen);
			data.put(index, generatedValue);
		}			
	}

	void printReport() {
		System.out.println("--- Data Modification ---");
		System.out.println("Total time taken to complete " + deleteCounter + " 'delete' operations: " + totalDelete + "ms");
		System.out.println("Total time taken to complete " + modifyCounter + " 'modify' operations: " + totalModify + "ms");
		System.out.println("Total time taken to complete " + addCounter + " 'add' opertaions: " + totalAdd + "ms");	
	}

	@Override
	void runBenchmark() {		
		generatedValue = "";
		totalDelete = 0;
		deleteCounter = 0;
		totalModify = 0;
		modifyCounter = 0;
		totalAdd = 0;
		addCounter = 0;
		
		while (nTest != testCount) {
			int operationID = ThreadLocalRandom.current().nextInt(0, 3);
			if (operationID == 0) {
				//delete
				
				/*
				 TODO: do this (remove() + timing) only when the random key exists. We want to count the time
				 for actual deletions, without including the time that mapdb takes to verify it has nothing to remove.
				 So, change it like this:
				 
				 if operationID is DELETE:
				   int positionDel = generateRandomExistingKey ();
				
				 where generateRandomExistingKey () is a utility method (private? public? On which class?) that does something like:
				 
				 <visibility> int generateRandomExistingKey ()
				 {
				   int key = -1;
				   do
					   key = ThreadLocalRandom.current().nextInt(0, data.size ());
				   while ( !data.containsKey ( key ) );
				   return key;
				  }
				  
				  The method is useful on the other operations too (see below)
				*/ 
				
			 
				// TODO: as you wrote rightly in other cases, you need nextInt ( 0, data.size() ), without - 1, 
				// because the comments on nextInt() clearly say the generated numbers fall in [lo, hi), ie, the second
				// extreme is exclusive, precisely because most cases where this is used are like the hereby
				int positionDel = ThreadLocalRandom.current().nextInt(0,(data.size()-1));
				long startDelete = System.currentTimeMillis();
				data.remove(positionDel);
				long finishDelete = System.currentTimeMillis();
				totalDelete = totalDelete + (finishDelete - startDelete);
				deleteCounter = deleteCounter + 1;
				nTest = nTest + 1;
			} else if (operationID == 1) {
				//modify
				
				// TODO: as above, we want to know the times for changing surely existing keys, without mixing it with   
				// the non-existing key case. So, use generateRandomExistingKey() as above
				//
				int positionMod = ThreadLocalRandom.current().nextInt(0,(data.size()-1));
				generatedValue = RandomStringUtils.randomAlphanumeric(stringMinLen, stringMaxLen);
				long startModify = System.currentTimeMillis();
		    	data.replace(positionMod, generatedValue);
		    	long finishModify = System.currentTimeMillis();
		    	totalModify = totalModify + (finishModify - startModify);
		    	modifyCounter = modifyCounter + 1;
		    	nTest = nTest + 1;
			} else {
				//add
				generatedValue = RandomStringUtils.randomAlphanumeric(stringMinLen, stringMaxLen);
				int positionAdd = data.size();
				long startAdd = System.currentTimeMillis();
				data.put(positionAdd, generatedValue);
				long finishAdd = System.currentTimeMillis();
				totalAdd = totalAdd + (finishAdd - startAdd);
				addCounter = addCounter + 1;
				nTest = nTest + 1;
			}	    
		}
	}
}
