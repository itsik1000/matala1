package matala1;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;
import matala1.Graph;
public class testFiles {

	private static boolean equalFiles(String expectedFileName,
	        String resultFileName) {
	    boolean equal;
	    BufferedReader bExp;
	    BufferedReader bRes;
	    String expLine ;
	    String resLine ;

	    equal = false;
	    bExp = null ;
	    bRes = null ;

	    try {
	        bExp = new BufferedReader(new FileReader(expectedFileName));
	        bRes = new BufferedReader(new FileReader(resultFileName));

	        if ((bExp != null) && (bRes != null)) {
	            expLine = bExp.readLine() ;
	            resLine = bRes.readLine() ;

	            equal = ((expLine == null) && (resLine == null)) || ((expLine != null) && expLine.equals(resLine)) ;

	            while(equal && expLine != null)
	            {
	                expLine = bExp.readLine() ;
	                resLine = bRes.readLine() ; 
	                equal = expLine.equals(resLine) ;
	            }
	        }
	    } catch (Exception e) {

	    } finally {
	        try {
	            if (bExp != null) {
	                bExp.close();
	            }
	            if (bRes != null) {
	                bRes.close();
	            }
	        } catch (Exception e) {
	        }

	    }

	    return equal;

	}
	
	@Test
	public void test() {
	
		Graph.readGraph("C:\\Users\\aviad\\Desktop\\tinyEWD.txt");
		Graph.waze("C:\\Users\\aviad\\Desktop\\test1.txt", "C:\\Users\\aviad\\Desktop\\ansTiny.txt");
		
		String expected = "C:\\Users\\aviad\\Desktop\\expectedTiny.txt";
		String output = "C:\\Users\\aviad\\Desktop\\ansTiny.txt";
		assertTrue(equalFiles(expected, output));
	
	}

}
