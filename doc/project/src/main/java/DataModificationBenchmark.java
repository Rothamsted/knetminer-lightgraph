
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

public class DataModificationBenchmark {

	public static void main(String[] args) {
		DB db = DBMaker
				.fileDB("file.db")
				.make();	
		
		HTreeMap<String, String> data = db
				.hashMap("data", Serializer.STRING, Serializer.STRING)
				.counterEnable()
				.createOrOpen();
		
		long timeElapsed = 0;
		
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		keys.addAll(data.getKeys());
		values.addAll(data.getValues());
		
		int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
		int nTest = 0;		
		int minLen = 3;
		int maxLen = 7;
		String generatedValue = "";
		
		//TODO: delete and replace need to be finished
		
		while (nTest != 3) {
			if (randomNum == 0) {
				//delete
				
				nTest = nTest + 1;
			} else if (randomNum == 1) {
				//modify
				Map.Entry<String,String> entry : data.entrySet();
			   	String k = entry.getKey();
			   	String k2= keys.get(0);
			   	String v = entry.getValue();
		    	String v2= values.get(0);
		    	data.replace(k, v2);
		    	nTest = nTest + 1;
			} else {
				//add
				String generatedKey = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
				generatedValue = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
				data.put(generatedKey, generatedValue);
				nTest = nTest + 1;
			}	
		}

		



	}

}
