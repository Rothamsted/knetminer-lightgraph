import java.util.concurrent.ThreadLocalRandom;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

public class ExistenceRetrievalBenchmark {

	public static void main(String[] args) {
		
		DB db = DBMaker
				.fileDB("file.db")
				.make();	
		
		HTreeMap<String, String> data = db
				.hashMap("data", Serializer.STRING, Serializer.STRING)
				.counterEnable()
				.createOrOpen();
		
		long timeElapsed = 0;
		
	    int consumedValues = 0;
	    //TODO
	    while (consumedValues != data.size()) {
	    	long start = System.currentTimeMillis();
	    	int randomNum = ThreadLocalRandom.current().nextInt(0, data.size() + 1);
	    	
	    	
	    	//map.put(k, v);
	    	long finish = System.currentTimeMillis();
	    	timeElapsed = timeElapsed + (finish - start);
	    	consumedValues = consumedValues + 1;
	    }
		
		

	}

}
