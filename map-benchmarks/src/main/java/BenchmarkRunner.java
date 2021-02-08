
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
		erBenchmark.runAll();
		erBenchmark.runAll();
		erBenchmark.runAll();
		//dmBenchmark.runAll();

	}

}
