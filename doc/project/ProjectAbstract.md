# Evaluating the feasibility of on-disk implementations of Ondex data interfaces

Knetminer is based on Ondex, which is essentially a framework to manage graphs of knowledge. Ondex graphs are 
made of concept nodes, where each concept has certain elements (eg, name, accession, attributes) and typically 
represents a biological entity such as a gene, a protein, a biological pathway, or even other entity types like 
a scientific publication or a gene set computed from some data analysis.  

Similarly, concepts can be linked by typed relations like in “gene encodes protein”, “protein participates in pathway”, 
or “publication mentions gene”.  

Some background: 
* https://vimeo.com/22439267
* https://academic.oup.com/bioinformatics/article/22/11/1383/237770

Ondex is use the base that we use to create and access data from the Knetminer application. About Knetminer:
* https://www.biorxiv.org/content/10.1101/2020.04.02.017004v2
* https://www.youtube.com/watch?v=PvxHTems3pA
  

Currently, the mentioned data structures are implemented through standard Java collections (maps, sets, lists, etc). 
Since the Ondex data sets have become bigger and bigger over the years, we are having very hard time managing these 
data with these traditional implementations. In fact, they require up to 128GB of RAM and long data loading times.  

In order to cope with these issues, we would like to assess the feasibility of an alternative implementation of our 
interfaces, based on libraries like Chronicle Map (preferred) or MapDB. A new implementation based on one of those 
libraries should be highly transparent, that is, code that currently works with in-memory collections should be able 
to keep working with minimal changes (eg, on the initialisation only).  

The project should first test the performance of the above libraries by simulating tasks typical of our Knetminer and 
Ondex software. Examples desired benchmarks: 

* Create recursive maps of key-value pairs (ie, `Map<String, Object>`) backed by on-disk maps and consisting of 
randomly-generated data of various types (string, number, etc), to be created from a fixed set of key names 
(to be defined). Using such data set, test operations like:
  * Search objects by key (eg, type = ‘Gene’) and scan all the results
  * Find objects referred by certain keys (eg, fetch all ‘encodes’ values in: `{ type = ‘Gene’, encodes = Map<...> }`) 
    and change them (eg, replace `name` with `new name $oldName`)
  * Export data to CSV and JSON
* Do the step 2 above, but after having taken data from a simple JSON importer from a real dataset.

If the tests above yield promising results, we could move to the implementation of Ondex interfaces by means of an 
on-disk library of choice, which should include writing proper unit tests for most common operations (eg, creating 
and finding data), as well as developing benchmarks similar to the ones outlined above.  

Detailed use cases are collected [here](OndexBenchmarks.md) and [here](MapBenchmarks.md).



