import org.apache.commons.lang3.RandomStringUtils;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

public class StringsPopulationBenchmark extends Benchmark {
	
	void createData() {
		for (int i=0; i<10; i++) {
			int minLen = 3;
			int maxLen = 7;
			long start = System.currentTimeMillis();
			int index = data.size();
			String generatedValue = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
			data.put(index, generatedValue);
			long finish = System.currentTimeMillis();
			timeElapsed = timeElapsed + (finish - start);
		}
	}

	void printReport() {
		System.out.println("Took : " + timeElapsed + "ms");
	}

}
