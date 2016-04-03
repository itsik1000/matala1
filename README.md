# matala1
מטלה 1 במבנה תוכנה

This is our first project using GitHub.

This project includes an algorithm finding the shortest path between two vertices in a graph, including a black list.

At first we thought of trying to write an algorithm ourselves. After being written we had found out that the algorithm is not good enough to solve the large graph. (class Graph)

While writing this code we found an open-source based on the Bellman-Ford algorithm. We had found out that also this algorithm was not able to solve the large graph. (class Graph_algo_First)

Finally, we found an open-source based on Dijkstra algorithm. This algorithm was able to solve also the large graph. (class Graph_algo)

We have added a JUnit that checks that an output file equals precicely to the requested file (class testFiles).
In addition, we added a generator of tests that generated files for testing (writeTests). We have genereted text files 10-29, 40-59, 80-99.
