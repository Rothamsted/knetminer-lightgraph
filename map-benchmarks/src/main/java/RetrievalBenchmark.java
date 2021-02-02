
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class RetrievalBenchmark extends Benchmark{
	
	private List<String> map;

	public void createData() {
		
		for (int i=0; i<testSize; i++) {
			int index = data.size();
			String generatedValue = RandomStringUtils.randomAlphanumeric(stringMinLen, stringMaxLen);
			data.put(index, generatedValue);
		}		
	}

	public void printReport() {
		System.out.println("Times Retrieved: " + map.size());
		System.out.println("Took : " + timeElapsed + "ms");
	}

	@Override
	void runBenchmark() {
		
		System.out.println("--- Retrieval ---");
		map = new ArrayList<String>();
		
		while (nTest < testCount) {
			int randomKey = ThreadLocalRandom.current().nextInt(0, data.size());
			long start = System.currentTimeMillis();
			String retrievedValue = data.get(randomKey);
			long finish = System.currentTimeMillis();
			timeElapsed = timeElapsed + (finish - start);
			map.add(retrievedValue);
			nTest = nTest + 1;
		}
	}
}
