import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class ExistenceRetrievalBenchmark extends Benchmark{

	private Map<Integer,String> map;
	private int consumedValues;
	
	public void createData()
	{
		timeElapsed = 0;
		for (int i=0; i<100000; i++) {
			int minLen = 3;
			int maxLen = 7;
			int index = data.size();
			String generatedValue = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
			data.put(index, generatedValue);
		}		
	  
		// TODO: initialisations should go either on a dedicated method, or, in this case, close to 
		// the code that is about to use the thing you initialise (so, in which method should you move consumedValues?)
		// Another option in cases like this is to initialise fields while you declare them (line 10 and 11)  
		consumedValues = 0;	    
		
		map = new HashMap<Integer,String>();

		// TODO: I re-indendented correctly, Eclipse has a function for that (Source/Format or 'Format Element')
    for (Entry<Integer, String> entry : data.entrySet()) {
    	Integer k = entry.getKey();
    	String v = entry.getValue();
    	map.put(k, v);    	
    }
	}
	
	@Override
	public void runBenchmark ()
	{
		// TODO: I moved this code here, as described on the changes made on Benchmark
		// TODO: this 3 needs to eventually become a configuration parameter   
		
    while (consumedValues != 3) {
    	long start = System.currentTimeMillis();
    	int randomNum = ThreadLocalRandom.current().nextInt(0, data.size() + 1);
    	
    	// TODO: when testing with millions of entries you won't be able to afford that much output
    	// One way to fix this is to remove all the println() command, leaving data.get() only
    	// 
    	// A Better alternative is to do that plus reporting a message from time to time, let's say
    	// every 1000 consumedValues. See here on how to do that:
    	// https://www.java67.com/2015/10/how-to-solve-fizzbuzz-in-java.html
    	// (you're going to have only one if in this case, checking if consumedValue is 1000, 2000, etc) 
    	// 
    	if (map.containsKey(randomNum)) {
    		System.out.println("The key of '" + randomNum + "' exists in the map");
    		System.out.println("The value related to this key is: " + data.get(randomNum));
    	} else {
    		System.out.println(randomNum + " isn't a valid key in the map");
    	}	    
    	
    	// TODO
    	long finish = System.currentTimeMillis();
    	timeElapsed = timeElapsed + (finish - start);
    	consumedValues = consumedValues + 1;
    }		
	}




	public void printReport() {
		System.out.println("--- Existence & Retrieval ---");
	    
		System.out.println("Amount of keys/values in map: " + data.size());
		System.out.println("Amount of times checked for a valid key: " + consumedValues);
		System.out.println("Took : " + timeElapsed + "ms");		
	}

}
