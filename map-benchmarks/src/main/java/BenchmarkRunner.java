import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
		name = "BenchmarkRunner",
		description = "Runs the separate Benchmarks"
		)

public class BenchmarkRunner implements Runnable{
	
	@Option(names = {"-sp", "stringPopulation"})
	private static String stringPop;
	
	@Option(names = {"-r", "retrieval"})
	private static String retrieval;
	
	@Option(names = {"-er", "existenceRet"})
	private static String exRetrieval;
	
	@Option(names = {"-dm", "dataMod"})
	private static String dataMod;
	
	@Option(names = {"-ds", "dataSize"})
	private static Integer dataSize;
	
	@Option(names = {"-tc", "testCount"})
	private static Integer testCount;

	public static void main(String[] args) {
		Benchmark spBenchmark = new StringsPopulationBenchmark();
		Benchmark rBenchmark = new RetrievalBenchmark();
		Benchmark erBenchmark = new ExistenceRetrievalBenchmark();
		Benchmark dmBenchmark = new DataModificationBenchmark();
		
		CommandLine.run(new BenchmarkRunner(), args);
		
		if (stringPop != null) {
			
			if (dataSize != null) {
				spBenchmark.setTestSize(dataSize);
				spBenchmark.runAll();
			} else {
				System.out.println("DATA SIZE LEFT EMPTY : USE '-ds' OR 'dataSize' FOLLOWED BY A NUMBER");
			}
			
		} else if (retrieval != null) {
			if (dataSize != null) {
				rBenchmark.setTestSize(dataSize);
				if (testCount != null) {
					rBenchmark.setTestCount(testCount);
					rBenchmark.runAll();
				} else {
					System.out.println("TEST COUNT LEFT EMPTY : USE '-tc' OR 'testCount' FOLLOWED BY A NUMBER");
				}
			} else {
				System.out.println("DATA SIZE LEFT EMPTY : USE '-ds' OR 'dataSize' FOLLOWED BY A NUMBER");
			}
			
		} else if (exRetrieval != null) {
			if (dataSize != null) {
				erBenchmark.setTestSize(dataSize);
				if (testCount != null) {
					erBenchmark.setTestCount(testCount);
					erBenchmark.runAll();
				} else {
					System.out.println("TEST COUNT LEFT EMPTY : USE '-tc' OR 'testCount' FOLLOWED BY A NUMBER");
				}
			} else {
				System.out.println("DATA SIZE LEFT EMPTY : USE '-ds' OR 'dataSize' FOLLOWED BY A NUMBER");
			}
			
		} else if (dataMod != null) {
			
			if (dataSize != null) {
				dmBenchmark.setTestSize(dataSize);
				if (testCount != null) {
					dmBenchmark.setTestCount(testCount);
					dmBenchmark.runAll();
				} else {
					System.out.println("TEST COUNT LEFT EMPTY : USE '-tc' OR 'testCount' FOLLOWED BY A NUMBER");
				}
			} else {
				System.out.println("DATA SIZE LEFT EMPTY : USE '-ds' OR 'dataSize' FOLLOWED BY A NUMBER");
			}
			
		} else {			
			System.out.println("NO BENCHMARK SELECTED");			
		}	
		
//		spBenchmark.setTestSize(10000);
//		spBenchmark.runAll();
		
//		rBenchmark.setTestSize(1000);
//		rBenchmark.setTestCount(10000);
//		rBenchmark.runAll();
		
//		erBenchmark.setTestSize(1000);
//		erBenchmark.setTestCount(10000);
//		erBenchmark.runAll();
	
//		dmBenchmark.setTestSize(1000);
//		dmBenchmark.setTestCount(6000);
//		dmBenchmark.runAll();
			
//		rBenchmark.runAll();		
//		erBenchmark.runAll();
//		dmBenchmark.runAll();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
