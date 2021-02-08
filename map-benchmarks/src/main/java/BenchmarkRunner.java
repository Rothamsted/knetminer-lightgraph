
public class BenchmarkRunner {

	public static void main(String[] args) {
		Benchmark spBenchmark = new StringsPopulationBenchmark();
		Benchmark rBenchmark = new RetrievalBenchmark();
		Benchmark erBenchmark = new ExistenceRetrievalBenchmark();
		Benchmark dmBenchmark = new DataModificationBenchmark();
		//spBenchmark.runAll();
//		rBenchmark.runAll();
//		rBenchmark.runAll();
//		rBenchmark.runAll();		
		// TODO: we should set parameters here (data size, test count) and then run it)
		// When you increase the data size, probably you'll want to run one benchmark at a time, but that's up to you
		erBenchmark.runAll();
		erBenchmark.runAll();
		erBenchmark.runAll();
		//dmBenchmark.runAll();
	}

}
