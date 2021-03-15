package uk.ac.rothamsted.knetminer.lightgraphs.benchmark;
import org.apache.commons.lang3.RandomStringUtils;

import picocli.CommandLine.Command;

@Command ( name = "string-population" )
public class StringsPopulationBenchmark extends Benchmark {
	
	@Override
	public void createData() {
		for (int i=0; i<testSize; i++) {
			long start = System.currentTimeMillis();
			int index = data.size();
			String generatedValue = RandomStringUtils.randomAlphanumeric(stringMinLen, stringMaxLen);
			data.put(index, generatedValue);
			long finish = System.currentTimeMillis();
			timeElapsed += (finish - start);
		}
	}

	public void printReport() {
		System.out.println("--- String Population ---");
		System.out.println("Amount of values populated: " + data.size());
		System.out.println("Took : " + timeElapsed + "ms");
	}

	@Override
	public void runBenchmark() {
		// Not needed within this benchmark
	}

}
