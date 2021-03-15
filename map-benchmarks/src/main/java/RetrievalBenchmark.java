
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

import picocli.CommandLine.Command;

@Command(name="retrieval")
public class RetrievalBenchmark extends Benchmark{

	public void createData() {
		
		for (int i=0; i<testSize; i++) {
			int index = data.size();
			String generatedValue = RandomStringUtils.randomAlphanumeric(stringMinLen, stringMaxLen);
			data.put(index, generatedValue);
		}		
	}

	public void printReport() {
		System.out.println("Times Retrieved: " + testCount);
		System.out.println("Took : " + timeElapsed + "ms");
	}

	@Override
	public void runBenchmark() {
		
		System.out.println("--- Retrieval ---");
		
		while (nTest < testCount) {
			int randomKey = ThreadLocalRandom.current().nextInt(0, data.size());
			long start = System.currentTimeMillis();
			String retrievedValue = data.get(randomKey);
			long finish = System.currentTimeMillis();
			timeElapsed += (finish - start);
			nTest += 1;
		}
	}
}
