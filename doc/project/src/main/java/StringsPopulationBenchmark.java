import org.apache.commons.lang3.RandomStringUtils;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

public class StringsPopulationBenchmark {

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		
		DB db = DBMaker
				.fileDB("file.db")
				.fileDeleteAfterClose()
				.make();
		
		HTreeMap<String, String> data = db
				.hashMap("data", Serializer.STRING, Serializer.STRING)
				.counterEnable()
				.createOrOpen();
		
		for (int i=0; i<5; i++) {
			int minLen = 3;
			int maxLen = 7;
			String generatedString = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
			String itoString = ("" + i); //generatedKey as well 
			data.put(itoString, generatedString);
		}
		
	    
	    long finish = System.currentTimeMillis();
	    long timeElapsed = finish - start;
	    
	    System.out.println("Took : " + timeElapsed + "ms");
	    
		db.commit();
		db.close();

	}

}
