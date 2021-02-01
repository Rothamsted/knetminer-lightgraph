
public class BenchmarkRunner {

	public static void main(String[] args) {
		Benchmark spBenchmark = new StringsPopulationBenchmark();
		Benchmark rBenchmark = new RetrievalBenchmark();
		Benchmark erBenchmark = new ExistenceRetrievalBenchmark();
		Benchmark dmBenchmark = new DataModificationBenchmark();
		spBenchmark.runBenchmark();
		rBenchmark.runBenchmark();
		erBenchmark.runBenchmark();
		dmBenchmark.runBenchmark();
	}

}
