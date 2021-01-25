import java.util.HashMap;
import java.util.Map;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

public class RetrievalBenchmark {

	public static void main(String[] args) {
		
		DB db = DBMaker
				.fileDB("file.db")
				.make();	
		
		HTreeMap<String, String> data = db
				.hashMap("data", Serializer.STRING, Serializer.STRING)
				.counterEnable()
				.createOrOpen();
		
		long timeElapsed = 0;
		
		Map<String,String> map = new HashMap<String,String>();
		
	    for (Map.Entry<String,String> entry : data.entrySet()) { //can't use the entry way because can't search for specific pos
	    	long start = System.currentTimeMillis();
	    	String k = entry.getKey();
	    	String v = entry.getValue();
	    	map.put(k, v);
	    	long finish = System.currentTimeMillis();
	    	timeElapsed = timeElapsed + (finish - start);	    	
	    }
	    
		System.out.println(map);
		
	    System.out.println("Took : " + timeElapsed + "ms");
	   
		db.commit();
		db.close();

	}

}
