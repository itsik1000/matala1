package matala1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTests {
	public static void writeTests(String nameTo) {
		if (!nameTo.endsWith(".txt")) {
			nameTo = nameTo + ".txt";
		}
		try {
			String name = nameTo;
			FileWriter fw = null;
			BufferedWriter bw = null;
			fw = new FileWriter(name);
			bw = new BufferedWriter(fw);
			int numberChecks = 50;
			bw.write(numberChecks + "\n");
			int numberOfNodes = 1000000;
			for (int i = 0; i < numberChecks; i++) {
				int first = (int)(Math.random()*numberOfNodes);
				int last = (int)(Math.random()*numberOfNodes);
				int numOfBlocked = (int)(Math.random()*1000);
				String text = first + " " + last + " " + numOfBlocked;
				for(int j = 0; j<numOfBlocked; j++)
					text += " " + ((int)(Math.random()*numberOfNodes));
				text = text + "\n";
				bw.write(text);
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		for(int i=80; i<100; i++)
			writeTests("test"+ i);
	}
}
