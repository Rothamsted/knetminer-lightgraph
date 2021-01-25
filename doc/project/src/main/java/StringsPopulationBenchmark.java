import org.apache.commons.lang3.RandomStringUtils;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

public class StringsPopulationBenchmark extends Benchmark {
	
	public static void main(String[] args) {
		
		DB db = DBMaker
				.fileDB("file.db")
				.make();
		
		HTreeMap<String, String> data = db
				.hashMap("data", Serializer.STRING, Serializer.STRING)
				.counterEnable()
				.createOrOpen();
		
		long timeElapsed = 0;
		
		for (int i=0; i<5; i++) {
			int minLen = 3;
			int maxLen = 7;
			long start = System.currentTimeMillis();
			String generatedKey = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
			String generatedValue = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
			data.put(generatedKey, generatedValue);
			long finish = System.currentTimeMillis();
			timeElapsed = timeElapsed + (finish - start);
		}
	   
	    System.out.println("Took : " + timeElapsed + "ms");
	    
		db.commit();
		db.close();

	}	

	@Override
	void createData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void printReport() {
		// TODO Auto-generated method stub
		
	}

}
