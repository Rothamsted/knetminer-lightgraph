
public class BenchmarkRunner {

	public static void main(String[] args) {
		Benchmark spBenchmark = new StringsPopulationBenchmark();
		Benchmark rBenchmark = new RetrievalBenchmark();
		Benchmark erBenchmark = new ExistenceRetrievalBenchmark();
		spBenchmark.runBenchmark();
		rBenchmark.runBenchmark();
		erBenchmark.runBenchmark();

	}

}
