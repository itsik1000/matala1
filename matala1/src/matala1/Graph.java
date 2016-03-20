package matala1;

//A Java program for Bellman-Ford's single source shortest path
//algorithm.
import java.util.*;
import java.lang.*;
import java.io.*;

//A class to represent a connected, directed and weighted graph

public class Graph
{
	// A class to represent a weighted edge in graph
	class Edge {
		int src, dest;
		double weight;
		Edge() {
			src = dest = 0;
			weight = 0;
		}
	};

	static int V;
	static int E;
	Edge edge[];
	static Vector<Integer> blackList;
	static Graph graph;


	// Creates a graph with V vertices and E edges
	Graph(int v, int e)
	{
		V = v;
		E = e;
		edge = new Edge[e];
		for (int i=0; i<e; ++i)
			edge[i] = new Edge();
	}

	// The main function that finds shortest distances from src
	// to all other vertices using Bellman-Ford algorithm.  The
	// function also detects negative weight cycle
	double BellmanFord(int src, Vector<Integer> black, int dest)
	{
		if(black != null)
			blackList = new Vector<>(black);
		else blackList = new Vector<>();
		
		int V = graph.V, E = graph.E;
		double dist[] = new double[V];

		// Step 1: Initialize distances from src to all other
		// vertices as INFINITY
		for (int i=0; i<V; ++i)
			dist[i] = Double.POSITIVE_INFINITY;
		dist[src] = 0;

		// Step 2: Relax all edges |V| - 1 times. A simple
		// shortest path from src to any other vertex can
		// have at-most |V| - 1 edges
		for (int i=1; i<V; ++i)
		{
			for (int j=0; j<E; ++j)
			{
				int u = graph.edge[j].src;
				int v = graph.edge[j].dest;
				double weight = graph.edge[j].weight;
				if (dist[u]!=Double.POSITIVE_INFINITY &&
						dist[u]+weight<dist[v] && !blackList.contains(v))
					dist[v]=dist[u]+weight;
			}
		}


//		printArr(dist, V);
		return dist[dest];
	}

	// A utility function used to print the solution
//	void printArr(double[] dist, int V)
//	{
//		System.out.println("Vertex   Distance from Source");
//		for (int i=0; i<V; ++i)
//			System.out.println(i+"\t\t"+dist[i]);
//	}


	// Driver method to test above function
	public static void main(String[] args)
	{
		int V = 5;  // Number of vertices in graph
		int E = 8;  // Number of edges in graph

		Vector<Integer> blackList = new Vector<>();
		//     blackList.add(1);

		//     graph = new Graph(V, E);
		//
		//     // add edge 0-1 (or A-B in above figure)
		//     graph.edge[0].src = 0;
		//     graph.edge[0].dest = 1;
		//     graph.edge[0].weight = 1;
		//
		//     // add edge 0-2 (or A-C in above figure)
		//     graph.edge[1].src = 0;
		//     graph.edge[1].dest = 2;
		//     graph.edge[1].weight = 4;
		//
		//     // add edge 1-2 (or B-C in above figure)
		//     graph.edge[2].src = 1;
		//     graph.edge[2].dest = 2;
		//     graph.edge[2].weight = 3;
		//
		//     // add edge 1-3 (or B-D in above figure)
		//     graph.edge[3].src = 1;
		//     graph.edge[3].dest = 3;
		//     graph.edge[3].weight = 2;
		//
		//     // add edge 1-4 (or A-E in above figure)
		//     graph.edge[4].src = 1;
		//     graph.edge[4].dest = 4;
		//     graph.edge[4].weight = 2;
		//
		//     // add edge 3-2 (or D-C in above figure)
		//     graph.edge[5].src = 3;
		//     graph.edge[5].dest = 2;
		//     graph.edge[5].weight = 5;
		//
		//     // add edge 3-1 (or D-B in above figure)
		//     graph.edge[6].src = 3;
		//     graph.edge[6].dest = 1;
		//     graph.edge[6].weight = 1;
		//
		//     // add edge 4-3 (or E-D in above figure)
		//     graph.edge[7].src = 4;
		//     graph.edge[7].dest = 3;
		//     graph.edge[7].weight = 3;

		readGraph("C:\\Users\\Ori\\Desktop\\tinyEWD.txt");
		waze("C:\\Users\\Ori\\Desktop\\test1.txt", "C:\\Users\\Ori\\Desktop\\ans1.txt");

		double ans= graph.BellmanFord(0, blackList, 3);
		//     System.out.println(ans);
	}
	
	public static void waze(String from, String TheAnsFile){
		try {
			// the readFile
			String name = from;
			FileReader fr = null;
			BufferedReader br = null;
			fr = new FileReader(name);
			br = new BufferedReader(fr);
			// /the ansFile
			String name2 = TheAnsFile;
			FileWriter fw = null;
			BufferedWriter bw = null;
			fw = new FileWriter(name2);
			bw = new BufferedWriter(fw);
			// if (!name.endsWith(".txt")) {
			// name2 = name + ".txt";
			// }
			// כתיבת כמות הבדיקות
			int numberChecks = Integer.parseInt(br.readLine());
			bw.write(numberChecks);

			for (int i = 0; i < numberChecks; i++) {
				String ans = "";
				String s = br.readLine();
				ans = s;
				StringTokenizer help = new StringTokenizer(s);
				int start = Integer.parseInt((String) help.nextElement());
				int end = Integer.parseInt((String) help.nextElement());
				int numberOfBlacked = Integer.parseInt((String) help
						.nextElement());
				Vector<Integer> blacked = new Vector<Integer>(numberOfBlacked);
				// blacked =
				for (int j = 0; j < numberOfBlacked; j++) {
					blacked.add(Integer.parseInt((String) help.nextElement()));
				}
				
				ans += " "+graph.BellmanFord(start, blackList, end);
				bw.write(ans);
			}
			bw.close();
			fw.close();
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readGraph(String s){
		try {
			String name = s;
			FileReader fr = null;
			BufferedReader br = null;
			fr = new FileReader(name);
			br = new BufferedReader(fr);
			int numberNodes = Integer.parseInt(br.readLine());
			//			graph = new double[numberNodes][numberNodes];
			int numberEdges = Integer.parseInt(br.readLine());

			V=numberNodes;
			E=numberEdges;
			graph = new Graph(V, E);

			// while ((str = br.readLine()) != null) {
			for (int i = 0; i < numberEdges; i = i + 1) {
				StringTokenizer help = new StringTokenizer(br.readLine());
				int first = Integer.parseInt((String) help.nextElement());
				int second = Integer.parseInt((String) help.nextElement());
				double weight = Double.parseDouble((String) help.nextElement());
				graph.edge[i].src = first;
				graph.edge[i].dest = second;
				graph.edge[i].weight = weight;
			}
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
