package uk.ac.rothamsted.knetminer.lightgraphs.benchmark;

import java.util.concurrent.ThreadLocalRandom;

import picocli.CommandLine.Command;

@Command(name="retrieval")
public class RetrievalBenchmark extends Benchmark{

	public void printReport() {
		System.out.println("Times Retrieved: " + testCount);
		System.out.println("Took : " + timeElapsed + "ms");
		System.out.printf ( "Average: %.2f ms/string\n", 1d * timeElapsed / data.size () );
	}

	@Override
	public void runBenchmark() {
		
		System.out.println("--- Retrieval ---");
		
		for ( int nTest = 0; nTest != testCount; nTest++ ) 
		{
			int randomKey = ThreadLocalRandom.current().nextInt(0, data.size());
			long start = System.currentTimeMillis();
			String retrievedValue = data.get(randomKey);
			long finish = System.currentTimeMillis();
			timeElapsed += (finish - start);
		}
	}
}
