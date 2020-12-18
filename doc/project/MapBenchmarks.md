# Generic benchmarks with on-disk maps

Benchmarks in this paragraphs are to test basic features of MapDB (or, later, of ChronicleMap or other similar on-disk 
implementations of Map and collections) and using data of basic types (int, strings).  

Some notes on how to arrange a first Maven project are reported.

## Map of strings
* Create MapDB of <String, String> (let's call it `data`), populate it with datasetSize random entries, 
  report the total time taken for 	
  storing (ie, for `Map.put()`)
  * Generate strings of random lengths, picking random lengths between minLen and maxLen
* Scan the map at the previous point and "consume" the values (eg, assign them to a local variable, to force
  their retrieval). report the total time taken for the operation

## Retrieval benchmark
* Populate a MapDB of <Integer, String> (again, let's call it `data`), using a counter to create integer 
  keys and the random string method 
  for the values. So, we will have keys from 0 to `initialDatasetSize - 1`  
* Then run this loop nTests times:
  * generate a random index between 0 and data.size() - 1
  * retrieve the corresponding key and consume the value
* At the end, report the total time taken for the retrieval operations

## Existence/retrieval benchmark
* Use the same MapDB at the previous point, with integer keys
* Run this for nTests times:
  * Generate a random number between 0 and (`initialDatasetSize * 2 - 1`) (ie, non-existing key half the 
    times)
  * Check if they corresponding key exists
  * If yes, consume it
* At the end, report the total times taken to check the existence of keys and, for the keys that exist, the total time 
  for doing everything (test they exist + retrieval)
* Note that the point of this test is to see how MapDB performs when requests are not concentrated on a 
  particular pool of data entries, which is the condition allowing for caching subsets of disk data in memory. In other 
  words, we'd like to see if it performs well when the kind of application access to the data doesn't allow for taking 
  advantage from caching techniques.

## Data modification benchmark
* Use the map wit integer keys
* keep track of the `maxKey` used for the keys with a variable (initially it will be == `data.size() - 1`)
* Repeat for nTests times:
  * generate a random value operation picking randomly one of: `OP_DEL = 0`, `OP_MOD = 1`, `OP_ADD = 2`
	  (with this, I mean use integers, but use named variables rather than straight numbers, later refine it 
	  with Java enums)
  * If `OP_DEL` or `OP_MOD`:
    * generate a random from 0 to `maxKey`
    * if the key doesn't exist (cause it was deleted), skip the steps below and repeat from the loop's begin
    * If `OP_DEL`, delete the selected entry (using the key and `Map.remove()`)
    * If OP_MOD, fetch/consume the old value, generate a new value and replace the old one 
		(using `Map.put()` again)
  * If `OP_ADD`:
    *  increase `maxKey` and use its updated value to store a new randomly generated string
* Eventually, report the total time taken for deletion/modification/addition operations. Count the time 
  taken for the operations themselves (`delete()`, `put()`), not other times (eg, generating keys, checking
	if they exist)
* It is recommended to split the three cases above into separated methods (`doDel( key )`, `doMod( key )`, 
  `doAdd()`)


## General notes

* **IMPORTANT**: **Draw the application architecture as first step**, then **let's discuss the 
  architecture**, and **only after agreement on that** start coding

* Try to run each benchmark multiple times and with different `initialDatasetSize` (eg, 1000, 1E6, 10E6)
* Try all the tests with different RAM/heap options:
  `JAVA_TOOL_OPTIONS="-Xmx1G"`, or 4G, 8G, 12G (if your system has at least 16GB)
	  * Initially, do this for the case `initialDatasetSize` = 10E6

## Suggestions for the architecture
Define an abstract class `Benchmark` (why abstract?), each of the benchmarks above will be a particular 
subclass of this (eg, `StringsPopulationBenchmark`, `RetrievalBenchmark`). Think carefully of what is 
specific of each benchmark (ie what you should put in `Benchmark` vs what goes into subclasses).

Hints (in pseudocode):

```java
public abstract class Benchmark
  private MapDB data;	
  private String title; // Useful for reports, subclasses initialises it.
	private initialDatasetSize;

	public void init() 
	  basic initialisation, eg, instantiates data

  public void createData() // can contain time measurements, eg, in the case of StringsPopulationBenchmark 

  public void runBenchmark() // possibly empty, cause createData() might contain measurments

	public printReport () // Prints the kind of report requested for a benchmark

  public runAll()
```

Ideally, A separated class containing main() should get (from the command line) the test class name, a list of 
-param=value instructions, use this to instantiate the benchmark, setup its parameters and run the test. 

Example:

`java BenchMarkRun StringsPopulationBenchmark -initialDatasetSize=10000`
 
**NOTE**: this isn't very easy. Initially, it's fine to hardcode the code to run a benchmark in main(), ie, 
```java
Benchmark benchmark = new StringsPopulationBenchmark();
benchmark.setInitialDatasetSize ( 1000 );
benchmark.runAll();
```

## Other tips.
* [This][10] is how the timing of a method or instruction can be done 
  in Java. However, **try not to do it this way**. It's easy with [XStopWatch][20], which is an extension of
  [StopWatch][30]. Both can be used in similar ways, it just has a couple of additional methods to ease
  typical tasks.

[10]: https://stackoverflow.com/a/180179/529286
[20]: https://github.com/marco-brandizi/jutils/blob/master/jutils/src/main/java/uk/ac/ebi/utils/time/XStopWatch.java
[30]: https://kodejava.org/calculate-timings-using-commons-lang-stopwatch/

## Benchmarks with structured values (ie, JavaBeans)
TODO

## Benchmarks with Key -> Set of values
TODO
