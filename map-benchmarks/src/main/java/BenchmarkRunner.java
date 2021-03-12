import picocli.CommandLine;

public class BenchmarkRunner {
	public static void main(String[] args) 
	{
		// TODO: complete this with picocli invocation of our Benchmark, which is 
		// to be interpreted as a line command, and cause the subclasses to be considered as sub-commands
		// Note that they deprecated CommandLine.run() and rplaced it with this execute() method at the object level
		// (ie, you need to create a CommandLine first) 
		CommandLine cli = new CommandLine ( new BenchmarkCLI () );
		cli.execute ( args );
		
		// That's all we need! picocli will select the right Benchmark subclass (from args[0]), set the benchmark
		// fields and invoke Benchmark.run() for the selected subclass
	}

}
