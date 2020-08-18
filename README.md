# Retrival-Project
search engine that can run queries on a corpus and return a ranked list of documents
This assignment requires that you construct a search engine that can run queries on a corpus and return a ranked list of documents. Database for you to use for this assignment are taken from TREC-09. It is a set of 348566 references from MEDLINE, the on-line medical information database, consisting of titles or abstracts from 270 medical journals over a five year period (1987-1991). Also includes a set of 4904 queries that have been judged by TREC-09 against the MEDLINE references. The following is the hyperlink of the corpus.
 http://trec.nist.gov/data/t9_filtering.html

       From the site download the file whose name is "ohsu-trec.tar.gz" and then unzip it you will find three directories "pre-test", "trec9-test" and "trec9-train". From the "trec9-train" directory take the file called ohsumed.87 which includes the text of the corpus documents and the file query.mesh.1-4904 which includes the text of the judged queries. From the "trec9-test" directory take the file called "qrels.mesh.88-91" which includes the relevance judgment of each query.

Note: use porter Stemer from the link: https://tartarus.org/martin/PorterStemmer/java.txt
And use the from the link: http://xpo6.com/list-of-english-stop-words/

Implement the following variations of a retrieval system over the aforementioned corpus:
1.	Vector space model, terms weighted by term frequency i.e., without frequency normalization and without inverse document frequency, inner product similarity between vectors are used to measure the similarity between a document and a query. 
2.	Vector space model, terms weighted by term frequency and inverse document frequency as explained in the lectures, cosine similarity between vectors are used to measure the similarity between a document and a query
       
Run the two models against the above corpus, creating two output files, each with approximately 4904 lines of output (each line represent the id's of documents return as an answer of a query which consists of 50 ids i.e., each query has at max 50 documents as an answer, taking into a count the ranking is based on the similarity between documents and query). Each output file is referred to as a ‘run’. For each output file find the average recall and precession of the queries
