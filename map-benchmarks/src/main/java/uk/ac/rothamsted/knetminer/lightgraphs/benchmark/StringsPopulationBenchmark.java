package uk.ac.rothamsted.knetminer.lightgraphs.benchmark;
import org.apache.commons.lang3.RandomStringUtils;

import picocli.CommandLine.Command;

//initialises this class when called specifically in command line 
@Command ( name = "string-population" )
public class StringsPopulationBenchmark extends Benchmark {
	
	//method to create data to fill the map of 'testSize's size

	@Override
	public void createData() {
		//makes sure to not create double the amount of data
	}
	
	public void printReport() {
		System.out.println("--- String Population ---");
		System.out.println("Amount of values populated: " + data.size());
		System.out.println("Took : " + timeElapsed + "ms");
	}

	@Override
	public void runBenchmark() {
		for (int i=0; i<testSize; i++) {
			long start = System.currentTimeMillis(); //storage variable for start time
			int index = data.size(); 
			String generatedValue = RandomStringUtils.randomAlphanumeric(stringMinLen, stringMaxLen); //generating a random string of a certain length
			data.put(index, generatedValue); //placing generatedString in the list
			long finish = System.currentTimeMillis(); //storage variable for end time
			timeElapsed += (finish - start); //adding all the total times together by taking the start time away from the end time
		}
	}

}

