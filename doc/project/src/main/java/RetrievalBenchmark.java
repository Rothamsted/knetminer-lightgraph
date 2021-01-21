import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

public class RetrievalBenchmark {

	public static void main(String[] args) {
		
		DB db = DBMaker
				.fileDB("file2.db")
				.fileDeleteAfterClose()
				.make();
		
		HTreeMap<Integer, String> data = db
				.hashMap("data", Serializer.INTEGER, Serializer.STRING)
				.counterEnable()
				.createOrOpen();
		
		long timeElapsed = 0;
		
		for (int i=0; i<10000; i++) {
			int minLen = 3;
			int maxLen = 7;
			String generatedString = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
			int index = data.size();                 //string keys as well (seen in Ondex) -> to check for the low 
			long start = System.currentTimeMillis(); //chance of the string key already exists in the map
			data.put(index, generatedString);
			long finish = System.currentTimeMillis();
			timeElapsed = timeElapsed + (finish - start);
		}
		
		Map<Integer,String> map = new HashMap<Integer,String>();
		
	    for (Map.Entry<Integer,String> entry : data.entrySet()) {
	    	Integer k = entry.getKey();
	    	String v = entry.getValue();
	    	map.put(k, v);
	    }
		
		System.out.println(map);
		
	    System.out.println("Took : " + timeElapsed + "ms");
	   
		db.commit();
		db.close();

	}

}
