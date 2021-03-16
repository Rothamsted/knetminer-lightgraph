package uk.ac.rothamsted.knetminer.lightgraphs.benchmark;
import picocli.CommandLine;

public class BenchmarkRunner {
	public static void main(String[] args) 
	{
		CommandLine cli = new CommandLine ( new BenchmarkCLI () );
		cli.execute(args);
		
		// That's all we need! picocli will select the right Benchmark subclass (from args[0]), set the benchmark
		// fields and invoke Benchmark.run() for the selected subclass
	}

}
