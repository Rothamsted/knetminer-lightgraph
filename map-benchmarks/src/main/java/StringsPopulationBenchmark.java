import org.apache.commons.lang3.RandomStringUtils;

public class StringsPopulationBenchmark extends Benchmark {
	
	@Override
	public void createData() {
		for (int i=0; i<testSize; i++) {
			long start = System.currentTimeMillis();
			int index = data.size();
			String generatedValue = RandomStringUtils.randomAlphanumeric(stringMinLen, stringMaxLen);
			data.put(index, generatedValue);
			long finish = System.currentTimeMillis();
			timeElapsed = timeElapsed + (finish - start);
		}
	}

	public void printReport() {
		System.out.println("--- String Population ---");
		System.out.println("Amount of values populated: " + data.size());
		System.out.println("Took : " + timeElapsed + "ms");
	}

	@Override
	void runBenchmark() {
		// Not needed within this benchmark
	}

}
