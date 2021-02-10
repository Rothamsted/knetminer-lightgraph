
public class BenchmarkRunner {

	public static void main(String[] args) {
		Benchmark spBenchmark = new StringsPopulationBenchmark();
		Benchmark rBenchmark = new RetrievalBenchmark();
		Benchmark erBenchmark = new ExistenceRetrievalBenchmark();
		Benchmark dmBenchmark = new DataModificationBenchmark();
		
		spBenchmark.setTestSize(1000);
		spBenchmark.runAll();
		
		rBenchmark.setTestSize(1000);
		rBenchmark.setTestCount(10000);
		rBenchmark.runAll();		
//		erBenchmark.runAll();
//		dmBenchmark.runAll();
	}

}
