/**
 * 
 * Omar R.G. Mohamed || CSC460 || readFilesAndInsertData || Dr. Lester I. McCann
 * 
 * 
 * This Program reads 4 csv files for polling places in Montana from lectura,
 * Then it populates the tables created earlier with the script createTables.sql in Oracle.
 * 
 * This program is run in lectura (When it needs to run).
 * 
 * There is NO need for this program to be run because
 * The it has already ran once and the tables have already been populated.
 * 
 * Only run the Prog3 file
 * 
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.sql.*;

public class readFilesAndInsertData {
	
public static void main(String[] args) throws FileNotFoundException {
		// export CLASSPATH=/opt/oracle/product/10.2.0/client/jdbc/lib/ojdbc14.jar:${CLASSPATH}
	
		ArrayList<String[]> places12 = readFilesYieldMaxLengths("/home/cs460/fall20/mtpollingplaces2012.csv");
		ArrayList<String[]> places14 = readFilesYieldMaxLengths("/home/cs460/fall20/mtpollingplaces2014.csv");
		ArrayList<String[]> places16 = readFilesYieldMaxLengths("/home/cs460/fall20/mtpollingplaces2016.csv");
		ArrayList<String[]> places18 = readFilesYieldMaxLengths("/home/cs460/fall20/mtpollingplaces2018.csv");
		
		
		
		final String oracleURL =   // Magic lectura -> aloe access spell
                "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";

		
		String username = null,    // Oracle DBMS username
		       password = null;    // Oracle DBMS password
		
		username = "omarraef1";
		password = "a9572";
		
		try {
		
		        Class.forName("oracle.jdbc.OracleDriver");
		
		} catch (ClassNotFoundException e) {
		
		        System.err.println("*** ClassNotFoundException:  "
		            + "Error loading Oracle JDBC driver.  \n"
		            + "\tPerhaps the driver is not on the Classpath?");
		        System.exit(-1);
		
		}
		
		
		    // make and return a database connection to the user's
		    // Oracle database
		
		Connection dbconn = null;
		
		try {
		        dbconn = DriverManager.getConnection
		                       (oracleURL,username,password);
		
		} catch (SQLException e) {
		
		        System.err.println("*** SQLException:  "
		            + "Could not open JDBC connection.");
		        System.err.println("\tMessage:   " + e.getMessage());
		        System.err.println("\tSQLState:  " + e.getSQLState());
		        System.err.println("\tErrorCode: " + e.getErrorCode());
		        System.exit(-1);
		
		}
		
		
		    // Send the query to the DBMS, and get and display the results
		
		//Statement stmt = null;
		//ResultSet answer = null;
		
		
		
		//final String query =       // our test query
		  //              "SELECT sno, status FROM mccann.s";
		
		try {
			for (int i = 0; i < places12.size();i++) {
				Statement stmt = null;
				ResultSet answer = null;
				
				final String query =       // our test query
		                "INSERT INTO polling12 values (\'"+places12.get(i)[0]+"\',\'"
		                + places12.get(i)[1] + "\',\'"
		                + places12.get(i)[2].replace("&", "\'||chr(38)||\'") + "\',\'"
		                + places12.get(i)[3] + "\',\'"
		                + places12.get(i)[4].replace("\'", "\'\'").replace("&", "\'||chr(38)||\'") + "\',\'"
		                + places12.get(i)[5] + "\',\'"
		                + places12.get(i)[6].replace("\'", "\'\'").replace("&", "\'||chr(38)||\'") + "\',\'"
		                + places12.get(i)[7].replace("\"", "").replace("\'", "\'\'").replace("&", "\'||chr(38)||\'") + "\')";
	
				stmt = dbconn.createStatement();
				answer = stmt.executeQuery(query);
				
				stmt.close();
			}
			
			for (int i = 0; i < places14.size();i++) {
				Statement stmt = null;
				ResultSet answer = null;
				
				final String query =       // our test query
		                "INSERT INTO polling14 values (\'"+places14.get(i)[0]+"\',\'"
		                + places14.get(i)[1] + "\',\'"
		                + places14.get(i)[2].replace("&", "\'||chr(38)||\'") + "\',\'"
		                + places14.get(i)[3] + "\',\'"
		                + places14.get(i)[4].replace("\'", "\'\'").replace("&", "\'||chr(38)||\'") + "\',\'"
		                + places14.get(i)[5] + "\',\'"
		                + places14.get(i)[6].replace("\'", "\'\'").replace("&", "\'||chr(38)||\'") + "\',\'"
		                + places14.get(i)[7].replace("\"", "").replace("\'", "\'\'").replace("&", "\'||chr(38)||\'") + "\')";
				
				stmt = dbconn.createStatement();
				answer = stmt.executeQuery(query);
				
				stmt.close();
			}
			
			for (int i = 0; i < places16.size();i++) {
				Statement stmt = null;
				ResultSet answer = null;
			
				final String query =       // our test query
		                "INSERT INTO polling16 values (\'"+places16.get(i)[0]+"\',\'"
		                + places16.get(i)[1] + "\',\'"
		                + places16.get(i)[2].replace("&", "\'||chr(38)||\'") + "\',\'"
		                + places16.get(i)[3] + "\',\'"
		                + places16.get(i)[4].replace("\'", "\'\'").replace("&", "\'||chr(38)||\'") + "\',\'"
		                + places16.get(i)[5] + "\',\'"
		                + places16.get(i)[6].replace("\'", "\'\'").replace("&", "\'||chr(38)||\'") + "\',\'"
		                + places16.get(i)[7].replace("\"", "").replace("\'", "\'\'").replace("&", "\'||chr(38)||\'") + "\')";
	
				stmt = dbconn.createStatement();
				answer = stmt.executeQuery(query);
				
				stmt.close();
			}
			
			for (int i = 0; i < places18.size();i++) {
				Statement stmt = null;
				ResultSet answer = null;
				
				final String query =       // our test query
		                "INSERT INTO polling18 values (\'"+places18.get(i)[0]+"\',\'"
		                + places18.get(i)[1] + "\',\'"
		                + places18.get(i)[2].replace("&", "\'||chr(38)||\'") + "\',\'"
		                + places18.get(i)[3] + "\',\'"
		                + places18.get(i)[4].replace("\'", "\'\'").replace("&", "\'||chr(38)||\'") + "\',\'"
		                + places18.get(i)[5] + "\',\'"
		                + places18.get(i)[6].replace("\'", "\'\'").replace("&", "\'||chr(38)||\'") + "\',\'"
		                + places18.get(i)[7].replace("\"", "").replace("\'", "\'\'").replace("&", "\'||chr(38)||\'") + "\')";
	
				stmt = dbconn.createStatement();
				answer = stmt.executeQuery(query);
				
				stmt.close();
			}
		
		
		        // Shut down the connection to the DBMS.
		
		    //stmt.close();  
		    dbconn.close();
		
		} catch (SQLException e) {
		
		        System.err.println("*** SQLException:  "
		            + "Could not fetch query results.");
		        System.err.println("\tMessage:   " + e.getMessage());
		        System.err.println("\tSQLState:  " + e.getSQLState());
		        System.err.println("\tErrorCode: " + e.getErrorCode());
		        System.exit(-1);
		
		}
		
		
		
	}
	
	public static ArrayList<String[]> readFilesYieldMaxLengths(String places) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(places));
		
		ArrayList<String[]> inFileContent1 = new ArrayList<>();
		
		String line = "";
		
		try {
			while ((line = br.readLine()) != null) {
				inFileContent1.add(line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1));
				//System.out.println(line);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ArrayList<String[]> fixedInFileContent1 = new ArrayList<>();
		
		for (int i = 1; i<inFileContent1.size(); i++) {
			fixedInFileContent1.add(inFileContent1.get(i));
		}
		
		int maxState = 0;
		int maxJuris = 0;
		int maxCountyName = 0;
		int maxMunic = 0;
		int maxPrecinctName = 0;
		int maxPrecinctID = 0;
		int maxName = 0;
		int maxAddress = 0;
		
		for (int i = 0; i < fixedInFileContent1.size(); i++) {
			maxState = Math.max(fixedInFileContent1.get(i)[0].length(), maxState);
			maxJuris = Math.max(fixedInFileContent1.get(i)[1].length(), maxJuris);
			maxCountyName = Math.max(fixedInFileContent1.get(i)[2].length(), maxCountyName);
			maxMunic = Math.max(fixedInFileContent1.get(i)[3].length(), maxMunic);
			maxPrecinctName = Math.max(fixedInFileContent1.get(i)[4].length(), maxPrecinctName);
			maxPrecinctID = Math.max(fixedInFileContent1.get(i)[5].length(), maxPrecinctID);
			maxName = Math.max(fixedInFileContent1.get(i)[6].length(), maxName);
			maxAddress = Math.max(fixedInFileContent1.get(i)[7].length(), maxAddress);
		}
		
		return fixedInFileContent1;
	}

}
