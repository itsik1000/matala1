package matala1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.Vector;

//import java.util.logging.Logger;

public class Graph_algo {
	private double[][] graph;
	private double[][] graphFirst;
	private boolean isDirected;
	private String theGraphFile;
	private String ThePathAndBlockedFile;
	private String TheAnsFile;

	public Graph_algo(String theGraphFile, String ThePathAndBlockedFile) {
		this.ThePathAndBlockedFile = ThePathAndBlockedFile;
		this.theGraphFile = theGraphFile;
		readGraph();
		makeFirstGraph();
	}

	public Vector<Integer> thePath(int start, int end) {
		// ////////למלא
		System.out.println("למלא");
		Vector<Integer> path = new Vector<Integer>();
		return null;
	}

	public double distance(int start, int end) {
		return graphFirst[start][end];
	}

	public double distance(int start, int end, Vector<Integer> blackList) {
		// if in the original graph there no connection.
		if (graphFirst[start][end] == Double.POSITIVE_INFINITY)
			return Double.POSITIVE_INFINITY;
		double[][] currentGraph = graph.clone();
		// we mark all the blocked Nodes by: =Double.POSITIVE_INFINITY
		for (int k = 0; k < blackList.size(); k++)
			for (int i = 0; i < currentGraph.length; i++) {
				currentGraph[blackList.get(k)][i] = Double.POSITIVE_INFINITY;
				currentGraph[i][blackList.get(k)] = Double.POSITIVE_INFINITY;
			}

		for (int k = 0; k < currentGraph.length; k++)
			for (int i = 0; i < currentGraph.length; i++)
				for (int j = 0; j < currentGraph.length; j++)
					currentGraph[i][j] = Math.min(currentGraph[i][k]
							+ currentGraph[k][j], currentGraph[i][j]);
		return currentGraph[start][end];
	}

	// checks if the original graph is directed.
	private void isOriginalDirected() {
		isDirected = isDirected(graphFirst);
	}

	public void TheQuation() {

		try {
			// the readFile
			String name = ThePathAndBlockedFile;
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

			for (long i = 0; i < numberChecks; i++) {
				String ans = "";
				String s = br.readLine();
				ans = s;
				StringTokenizer help = new StringTokenizer(s);
				int first = Integer.parseInt((String) help.nextElement());
				int second = Integer.parseInt((String) help.nextElement());
				int numberOfBlacked = Integer.parseInt((String) help
						.nextElement());
				Vector<Integer> blacked = new Vector<Integer>(numberOfBlacked);
				// blacked =
				for (int j = 0; j < numberOfBlacked; j++) {
					blacked.set(j, Integer.parseInt((String) help.nextElement()));
				}
				ans += " "+distance(first, second, blacked);
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

	// checks if the graph is directed.
	private boolean isDirected(double[][] g) {
		for (int i = 0; i < g.length; i++) {
			for (int j = i + 1; j < g.length; j++)
				if (g[i][j] != g[j][i])
					return false;
		}
		return true;
	}

	//
	private void makeFirstGraph() {
		graphFirst = graph.clone();
		for (int k = 0; k < graphFirst.length; k++)
			for (int i = 0; i < graphFirst.length; i++)
				for (int j = 0; j < graphFirst.length; j++)
					graphFirst[i][j] = Math.min(graphFirst[i][k]
							+ graphFirst[k][j], graphFirst[i][j]);
		for (int i = 0; i < graphFirst.length; i++)
			for (int j = 0; j < graphFirst.length; j++) {
				if (i != j) {
					if (graphFirst[i][j] == 0)// אין חיבור
						graphFirst[i][j] = Double.POSITIVE_INFINITY;
				}
			}
	}

	public void readGraph() {
		try {
			String name = theGraphFile;
			FileReader fr = null;
			BufferedReader br = null;
			fr = new FileReader(name);
			br = new BufferedReader(fr);
			int numberNodes = Integer.parseInt(br.readLine());
			graph = new double[numberNodes][numberNodes];
			long numberEdges = Integer.parseInt(br.readLine());
			// while ((str = br.readLine()) != null) {
			int one = 1;
			for (long i = 0; i < numberEdges; i = i + 1) {
				StringTokenizer help = new StringTokenizer(br.readLine());
				int first = Integer.parseInt((String) help.nextElement());
				int second = Integer.parseInt((String) help.nextElement());
				double weight = Double.parseDouble((String) help.nextElement());
				graph[first][second] = weight;
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
