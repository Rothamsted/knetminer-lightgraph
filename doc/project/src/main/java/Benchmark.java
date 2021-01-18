import org.apache.commons.lang3.RandomStringUtils;

abstract class Benchmark {
	private MapDB data;
	
	abstract
	
	void createData();
	
	void printReport() {
		
	}
	
	class StringsPopulationBenchmark extends Benchmark {
		void createData() {
			
			
			for (int i=0; i<5; i++) {
				int minLen = 3;
				int maxLen = 7;
				String generatedString = RandomStringUtils.randomAlphanumeric(minLen, maxLen);
				String itoString = ("" + i);
				data.put(itoString, generatedString);
			
		}
		
		
	}
	
	class RetrievalBenchmark extends Benchmark {
		
		
		
	}
	
	
}
