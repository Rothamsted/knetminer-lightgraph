# Ondex typical use cases

This is a collection of typical ways in which the Knetbuilder (aka Ondex) interfaces (`ONDEXGraph`, `ONDEXConcept`, 
`Relation`, etc) are used in knetminer. We plan to test more [generic cases](MapBenchmarks.md) before the ones in this 
document.

* Store/get an attribute
* Same for ConceptClass, RelationType
	* Many maps are BidiMap
* getConceptsOfConceptClass()
  * Cases of Map<String, Set<T>>
* Scan all concepts (of a given type) and consume some attribute/accession/name/field
* Scan all concepts (of a given type), get the relations, and consume some attribute/accession/name/field
* Scan all relations (of a given type), and consume some attribute/accession/name/field
* Scan all relations (of a given type), get the concepts, and consume some attribute/accession/name/field
* Scan all concepts (of a given type) and replace some attribute/accession/name/field
* Scan all concepts (of a given type) and delete some attribute/accession/name
* Scan all relations (of a given type) and replace some attribute/accession/name/field
* Scan all relations (of a given type) and delete some attribute/accession/name
* Traverse a graph, ie:
  ```groovy
	for a number of iterations:
	  get a random concept c
		while end or random no of steps:
		  get a random relation r(c, d)
			c <- d
		consume c
	```
* scan all the graph
* random ID selection and fetch of concepts (tests non-sparse use)
* random ID selection and modification (again, tests non-sparse use)
