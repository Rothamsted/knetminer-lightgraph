package uk.ac.rothamsted.knetminer.lightgraphs.benchmark;

import java.util.concurrent.ThreadLocalRandom;

import picocli.CommandLine.Command;

@Command(name="retrieval")
public class RetrievalBenchmark extends Benchmark{

	private long valuesLens = 0;
	
	public void printReport() {
		System.out.println("Times Retrieved: " + testCount);
		System.out.println("Took : " + timeElapsed + "ms");
		System.out.printf ( "Average: %.5f ms/string\n", 1d * timeElapsed / testCount );
		System.out.printf ( "Total value lenght: %d\n", valuesLens );
	}

	@Override
	public void runBenchmark() {
		
		System.out.println("--- Retrieval ---");
		
		valuesLens = 0;
		for ( int nTest = 0; nTest != testCount; nTest++ ) 
		{
			int randomKey = ThreadLocalRandom.current().nextInt(0, data.size());
			long start = System.currentTimeMillis();
			String retrievedValue = data.get(randomKey);
			long finish = System.currentTimeMillis();
			timeElapsed += (finish - start);
			valuesLens += retrievedValue.length ();
		}
	}
}
