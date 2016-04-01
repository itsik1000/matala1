package matala1final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class Graph_algo {
	
	
	
	
	private static EdgeWeightedDigraph buildTheGraph(String dir){
		File file = new File(dir);	
		FileReader fr;
		BufferedReader in;
		
		try {
			fr = new FileReader(file);
			in = new BufferedReader(fr);
			
			EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
			in.close();
			fr.close();
			
			return G;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void ex1(String dirGraph, String dirPaths, String dirRes){
		EdgeWeightedDigraph G = buildTheGraph(dirGraph);                                  
		
		File fileGraph, fileRes;
		FileReader fileReaderGraph;
		BufferedReader reader;
		FileWriter  fileWriterGraph;
		BufferedWriter writer;
		

		try {
			fileGraph = new File(dirPaths);
			fileReaderGraph = new FileReader(fileGraph);
			reader = new BufferedReader(fileReaderGraph);
			
			fileRes = new File(dirRes);
			fileWriterGraph = new FileWriter(fileRes);
			writer = new BufferedWriter(fileWriterGraph);
			
			// Get the first line
			String str = reader.readLine();
			int numOfpaths = Integer.parseInt(str);
			writer.write(str);
			writer.newLine();
			
			//Use the Dijkstra algorithm to find pathLength. 
			for (int i = 0; i < numOfpaths; i++) {
			
				String strLine = reader.readLine();                         
				StringTokenizer line = new StringTokenizer(strLine, " ");  
				
				int src =  Integer.parseInt(line.nextToken());
				int dest =  Integer.parseInt(line.nextToken());
				
				Vector<Integer> blackList = new Vector<Integer>();
				while(line.hasMoreTokens()){	
					blackList.add(Integer.parseInt(line.nextToken()));
				}
				
				//Writing the first the line from file
				writer.write(strLine+" ");
				//Using DijkstraSP algorithm to calculate the distance
				
				DijkstraSP dijkstra = new DijkstraSP(G, src,blackList);
				if(dijkstra.hasPathTo(dest)){
					Double pathLength = dijkstra.distTo(dest);
					writer.write(pathLength.toString());
				}
				else writer.write("inf");
				writer.newLine();
			}
						
			
			writer.close();
			fileWriterGraph.close();
			reader.close();
			fileReaderGraph.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		String src = "C:\\Users\\aviad\\Desktop\\tests\\mediumEWD.txt";
		String test = "C:\\Users\\aviad\\Desktop\\tests\\test3.txt";
		String dest = "C:\\Users\\aviad\\Desktop\\tests\\dest.txt";
		System.out.println("start");
		long start = System.currentTimeMillis();
		ex1(src , test, dest);
		long end = System.currentTimeMillis();
		System.out.println("end");
		System.out.println("run time: "+((end-start)/1000.0)+" sec");
		
	}

}
