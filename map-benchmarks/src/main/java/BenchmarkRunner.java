import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
		name = "BenchmarkRunner",
		description = "Runs the separate Benchmarks"
		)

public class BenchmarkRunner implements Runnable{
	
	private Benchmark benchmark;
	
	@Option(names = {"-sp", "stringPop"})
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

	public  void main(String[] args) {
		
		CommandLine.run(new BenchmarkRunner(), args);
		
		if (dataSize != null) {
			if (stringPop != null) {
				benchmark = new StringsPopulationBenchmark();
				benchmark.setTestSize(dataSize);
				benchmark.runAll();
			} else if (retrieval != null) {
				benchmark = new RetrievalBenchmark();
				benchmark.setTestSize(dataSize);
				if (testCount != null) {
					benchmark.setTestCount(testCount);
					benchmark.runAll();
				} else {
					System.out.println("TEST COUNT LEFT EMPTY : USE '-tc' OR 'testCount' FOLLOWED BY A NUMBER");
				}		
			} else if (exRetrieval != null) {
				benchmark = new ExistenceRetrievalBenchmark();
				benchmark.setTestSize(dataSize);
				if (testCount != null) {
					benchmark.setTestCount(testCount);
					benchmark.runAll();
				} else {
					System.out.println("TEST COUNT LEFT EMPTY : USE '-tc' OR 'testCount' FOLLOWED BY A NUMBER");
				}				
			} else if (dataMod != null) {
				benchmark = new DataModificationBenchmark();
				benchmark.setTestSize(dataSize);				
				if (testCount != null) {
					benchmark.setTestCount(testCount);
					benchmark.runAll();
				} else {
					System.out.println("TEST COUNT LEFT EMPTY : USE '-tc' OR 'testCount' FOLLOWED BY A NUMBER");
				}				
			} else {			
				System.out.println("NO BENCHMARK SELECTED");			
			}			
		} else {
			System.out.println("DATA SIZE LEFT EMPTY : USE '-ds' OR 'dataSize' FOLLOWED BY A NUMBER");
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
