import org.apache.commons.lang3.RandomStringUtils;

public class StringsPopulationBenchmark extends Benchmark {
	
	void createData() {
		for (int i=0; i<100000; i++) {
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
		System.out.println("--- String Population ---");
		System.out.println("Amount of values populated: " + data.size());
		System.out.println("Took : " + (timeElapsed/1000) + "seconds");
	}

}
