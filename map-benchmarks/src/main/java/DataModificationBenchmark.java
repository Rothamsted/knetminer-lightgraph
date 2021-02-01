
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class DataModificationBenchmark extends Benchmark {
	
	private int nTest;
	private int randomNum;
	private int minLen;
	private int maxLen;
	private String generatedValue;
	private Map<Integer,String> map;
	
	void createData() {
		timeElapsed = 0;
		for (int i=0; i<10; i++) {
			minLen = 3;
			maxLen = 7;
			int index = data.size();
			generatedValue = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
			data.put(index, generatedValue);
		}			
	}

	void printReport() {
		System.out.println("--- Data Modification ---");
		System.out.println(map);	
		System.out.println("Took : " + timeElapsed + "ms");	
		
	}

	@Override
	void runBenchmark() {
		randomNum = ThreadLocalRandom.current().nextInt(0, 2);
		nTest = 0;		
		generatedValue = "";
		
		while (nTest != 3) {
			if (randomNum == 0) {
				//delete
				int positionDel = ThreadLocalRandom.current().nextInt(0,(data.size()-1));
				data.remove(positionDel);
				System.out.println("The operation chosen was: delete" );
				System.out.println("The position deleted was: " + positionDel);
				nTest = nTest + 1;
			} else if (randomNum == 1) {
				//modify
				int positionMod = ThreadLocalRandom.current().nextInt(0,(data.size()-1));
				generatedValue = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
		    	data.replace(positionMod, generatedValue);
		    	System.out.println("The operation chosen was: modify" );
		    	System.out.println("The key modified was: " + positionMod);
		    	nTest = nTest + 1;
			} else {
				//add
				generatedValue = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
				int positionAdd = data.size();
				data.put(positionAdd, generatedValue);
				System.out.println("The operation chosen was: add" );
				nTest = nTest + 1;
			}	
			randomNum = ThreadLocalRandom.current().nextInt(0, 2);
			
			map = new HashMap<Integer,String>();
			
		    for (Entry<Integer, String> entry : data.entrySet()) {
		    	long start = System.currentTimeMillis();
		    	Integer k = entry.getKey();
		    	String v = entry.getValue();
		    	map.put(k, v);
		    	long finish = System.currentTimeMillis();
		    	timeElapsed = timeElapsed + (finish - start);	    	
		    }
		    
//		    System.out.println(map);
		    
		}
		
	}

}
