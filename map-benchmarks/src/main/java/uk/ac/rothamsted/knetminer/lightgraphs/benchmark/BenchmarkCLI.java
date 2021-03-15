package uk.ac.rothamsted.knetminer.lightgraphs.benchmark;
import picocli.CommandLine.Command;

/**
 * This is just a stub needed to fit picocli commands that are subclasses of a main command
 * (details <a href = "https://github.com/remkop/picocli/issues/787">here</a>).
 *
 * When this command is run via picocli, the library selects one of the subcommands. It should
 * never invoke the hereby {@link #run()} method, which is here just to comply to the {@link Runnable}
 * interface.
 *
 */
@Command ( 
	subcommands = { 
		StringsPopulationBenchmark.class,
		RetrievalBenchmark.class,
		ExistenceRetrievalBenchmark.class,
		DataModificationBenchmark.class
	}
)
public class BenchmarkCLI implements Runnable
{
	/**
	 * @throws UnsupportedOperationException, for this class is just a stub, as said above, and this method
	 * isn't expected to be run.
	 */
	@Override
	public void run ()
	{
		throw new UnsupportedOperationException (
			"This shouldn't be run, provide a valid subcommand/benchmark to the command line arguments"
		);		
	}
}
