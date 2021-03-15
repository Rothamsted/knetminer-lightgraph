import picocli.CommandLine.Command;

/**
 * This is just a stub needed to fit picocli commands that are subclasses of a main command
 * (https://github.com/remkop/picocli/issues/787).
 *
 * When this command is run via picocli, the library selects one of the commands. It should
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
	@Override
	public void run ()
	{
		throw new UnsupportedOperationException (
			"This shouldn't be run, provide a valid subcommand/benchmark to the command line arguments"
		);		
	}
}
