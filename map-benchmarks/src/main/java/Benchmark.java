
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

/** 
 * 
 * TODO: comment me!
 * 
 * TODO: It's not good that everything stays in the default package. Move stuff into proper
 * packages, eg, uk.ac.rothamsted.knetlight.benchmark.* 
 * References: 
 *   https://www.baeldung.com/java-packages
 *   https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html
 * 
 */
public abstract class Benchmark {
	
	protected long timeElapsed;
	protected HTreeMap<Integer,String> data;
	
	abstract void createData();
	
	abstract void printReport();
	
	abstract void runBenchmark();
	
	public void init() 
	{
		DB db = DBMaker
				.fileDB("target/file.db")
				.fileDeleteAfterClose()
				.make();
		
		data = db
				.hashMap("data", Serializer.INTEGER, Serializer.STRING)
				.counterEnable()
				.createOrOpen();
	}
		
	
	/**
	 * TODO: Sorry I had proposed this in a rather wrong way.
	 * 
	 * Initially I was imagining runBenchMark() as the place where specific tests are run (and benchmarked/clocked) after
	 * data creation/population/etc. Both this and createData() can be empty (if they don't need to do anything 
	 * in a specific case).
	 * 
	 * I've moved the task of running the whole sequence to a new method runAll().
	 * Sorry for the confusion on this!
	 * 
	 */

	
	// TODO: it might be useful to have these public. Review the visibility rules in Java
	// an then review the methods without any visibility modifier (ie, being 'package' visible)


	/**
	 * Runs everything in one go.
	 */
	public void runAll () {
		init();
		createData();
		runBenchmark ();
		printReport();
		data.close();
	}

}
