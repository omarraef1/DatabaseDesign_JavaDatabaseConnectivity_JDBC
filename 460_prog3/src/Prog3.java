/**
 * 
 * Omar R.G. Mohamed || CSC460 || Prog3 || Dr. Lester I. McCann
 * 
 * 
 * There is no need to call this program followed by a username and a password because
 * They are already hard coded
 * 
 * You do however need too call this ->
 * 
 * 					export CLASSPATH=/opt/oracle/product/10.2.0/client/jdbc/lib/ojdbc14.jar:${CLASSPATH}
 * 
 * Before you run the program.
 * 
 * This Program runs a Menu of 6 options:
 * a) Number of precincts in each county in 2012.
 * b) Top five counties by number of precincts (for a given year).
 * c) Number of precinct names containing words CHURCH, HALL, SCHOOL, or COLLEGE (for a given year).
 * d) Show counties that did not lose precincts in all three pairs of subsequent years
 *    V.S counties that did not gain precincts in all three pairs of subsequent years.
 * quit) Exit Program.
 * menu) View Menu.
 * 
 * On each option,
 * A connection is established through JDBC to my Oracle account and 
 * Retrieves query results as requested,
 * Then closes the connection and asks the user for another option until the user terminates the program.
 * 
 * Just make sure to call the program from command-line through lectura
 * e.g. java Prog3 
 * 
 */
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.sql.*;


public class Prog3 {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println();
		System.out.println("|| Query Menu || Enter a, b, c, d, or quit or menu (Case sensitive)");
		System.out.println();
		System.out.println("a) Number of precincts in each county in 2012.");
		System.out.println("b) Top five municipalities by number of precincts (for a given year).");
		System.out.println("c) Number of precinct names containing words CHURCH, HALL, SCHOOL, or COLLEGE (for a given year).");
		System.out.println("d) Show counties that did not lose precincts in all three pairs of subsequent years\n"
				+ "   V.S counties that did not gain precincts in all three pairs of subsequent years.");
		System.out.println("quit) Exit Program.");
		System.out.println("menu) View Menu.");
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		String usrInp = "";
		
		while(true) {
			usrInp = sc.nextLine();
			
			if(usrInp.equals("a")) {
				
				final String oracleURL =   // Magic lectura -> aloe access spell
		                "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";
				
				String username = null,    // Oracle DBMS username
				       password = null;    // Oracle DBMS password

				username = "omarraef1";
				password = "a9572";
				
	//			if (args.length == 2) {    // get username/password from cmd line args
	//			    //username = args[0];
	//			    //password = args[1];
	//				username = "omarraef1";
	//				password = "a9572";
	//			} else {
	//			    System.out.println("\nUsage:  java JDBC <username> <password>\n"
	//			                     + "    where <username> is your Oracle DBMS"
	//			                     + " username,\n    and <password> is your Oracle"
	//			                     + " password (not your system password).\n");
	//			    System.exit(-1);
	//			}
				
				
				    // load the (Oracle) JDBC driver by initializing its base
				    // class, 'oracle.jdbc.OracleDriver'.
				
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
				
				try {
						Statement stmt = null;
						ResultSet answer = null;
						
						final String query = "select countyname, count(*) as preccount from polling12 group by countyname order by countyname asc";
			
						stmt = dbconn.createStatement();
						answer = stmt.executeQuery(query);
						
						//stmt.close();
					
				
				    if (answer != null) {
				
				    //    System.out.println("\nThe results of the query [" + query 
				     //                    + "] are:\n");
				
				            // Get the data about the query result to learn
				            // the attribute names and use them as column headers
				
				        ResultSetMetaData answermetadata = answer.getMetaData();
				
				        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
				            System.out.print(answermetadata.getColumnName(i) + "\t");
				        }
				        System.out.println();
				
				            // Use next() to advance cursor through the result
				            // tuples and print their attribute values
				
				        while (answer.next()) {
				            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
				            System.out.format("%-20s %5s\n", answer.getString("countyname"), answer.getString("preccount"));
				        }
				    }
				    System.out.println();
				
				        // Shut down the connection to the DBMS.
				
				    stmt.close();  
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
			else if(usrInp.equals("b")) {
				System.out.println();
				System.out.println("Enter Year (2012, 2014, 2016, or 2018) (Case Sensitive):");
				usrInp = sc.nextLine();
				System.out.println();
				
				
				
				final String oracleURL =   // Magic lectura -> aloe access spell
		                "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";
				
				String username = null,    // Oracle DBMS username
				       password = null;    // Oracle DBMS password
				

				username = "omarraef1";
				password = "a9572";
	//			if (args.length == 2) {    // get username/password from cmd line args
	//			    //username = args[0];
	//			    //password = args[1];
	//				username = "omarraef1";
	//				password = "a9572";
	//			} else {
	//			    System.out.println("\nUsage:  java JDBC <username> <password>\n"
	//			                     + "    where <username> is your Oracle DBMS"
	//			                     + " username,\n    and <password> is your Oracle"
	///			                     + " password (not your system password).\n");
		//		    System.exit(-1);
		//		}
				
				
				    // load the (Oracle) JDBC driver by initializing its base
				    // class, 'oracle.jdbc.OracleDriver'.
				
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
				
				try {
						Statement stmt = null;
						ResultSet answer = null;
						
						String query = "";
						int flag = 0;
						
						if(usrInp.equals("2012")) {
							query = "select * from ( select countyname, count(*) as preccount from "
									+ "polling12"
									+ " group by countyname order by preccount desc ) where rownum < 6";
						}
						else if(usrInp.equals("2014")) {
							query = "select * from ( select countyname, count(*) as preccount from "
									+ "polling14"
									+ " group by countyname order by preccount desc ) where rownum < 6";
						}
						else if(usrInp.equals("2016")) {
							query = "select * from ( select countyname, count(*) as preccount from "
									+ "polling16"
									+ " group by countyname order by preccount desc ) where rownum < 6";
						}
						else if(usrInp.equals("2018")) {
							query = "select * from ( select countyname, count(*) as preccount from "
									+ "polling18"
									+ " group by countyname order by preccount desc ) where rownum < 6";
						}
						else {
							System.out.println("User Entered Illegal Year Number. Rerun Query.");
							flag = 1;
						}
						
						//final String query = "select * from ( select countyname, count(*) as preccount from "
						//		+ "polling12"
						//		+ " group by countyname order by preccount desc ) where rownum < 6";
			
						
						if(flag==0) {
							stmt = dbconn.createStatement();
							answer = stmt.executeQuery(query);
							
							//stmt.close();
						
					
						    if (answer != null) {
						
						   //     System.out.println("\nThe results of the query [" + query 
						    //                     + "] are:\n");
						
						            // Get the data about the query result to learn
						            // the attribute names and use them as column headers
						
						        ResultSetMetaData answermetadata = answer.getMetaData();
						
						        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						            System.out.print(answermetadata.getColumnName(i) + "\t");
						        }
						        System.out.println();
						
						            // Use next() to advance cursor through the result
						            // tuples and print their attribute values
						
						        while (answer.next()) {
						            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
						            System.out.format("%-20s %5s\n", answer.getString("countyname"), answer.getString("preccount"));
						        }
						    }
						    System.out.println();
						
						        // Shut down the connection to the DBMS.
						
						    stmt.close();  
						}
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
			else if(usrInp.equals("c")) {
				System.out.println();
				System.out.println("Enter Year (2012, 2014, 2016, or 2018) (Case Sensitive):");
				usrInp = sc.nextLine();
				System.out.println();
				
				
				
				final String oracleURL =   // Magic lectura -> aloe access spell
		                "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";
				
				String username = null,    // Oracle DBMS username
				       password = null;    // Oracle DBMS password

				username = "omarraef1";
				password = "a9572";
				
		//		if (args.length == 2) {    // get username/password from cmd line args
		//		    //username = args[0];
		//		    //password = args[1];
		//			username = "omarraef1";
		//			password = "a9572";
		//		} else {
		//		    System.out.println("\nUsage:  java JDBC <username> <password>\n"
		//		                     + "    where <username> is your Oracle DBMS"
		///		                     + " username,\n    and <password> is your Oracle"
			//	                     + " password (not your system password).\n");
			//	    System.exit(-1);
			//	}
				
				
				    // load the (Oracle) JDBC driver by initializing its base
				    // class, 'oracle.jdbc.OracleDriver'.
				
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
				
				try {
						Statement stmt = null;
						ResultSet answer = null;
						
						String query = "";
						
						if(usrInp.equals("2012")) {
							query = "select count(precname) as CHURCH from polling12 where upper(precname) like \'%CHURCH%\'";
							
							stmt = dbconn.createStatement();
							answer = stmt.executeQuery(query);
							
							//stmt.close();
						
					
					    if (answer != null) {
					
					     //   System.out.println("\nThe results of the query [" + query 
					       //                  + "] are:\n");
					
					            // Get the data about the query result to learn
					            // the attribute names and use them as column headers
					
					        ResultSetMetaData answermetadata = answer.getMetaData();
					
					        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
					            System.out.print(answermetadata.getColumnName(i) + "\t");
					        }
					        System.out.println();
					
					            // Use next() to advance cursor through the result
					            // tuples and print their attribute values
					
					        while (answer.next()) {
					            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
					            System.out.println(answer.getString("church"));
					        }
					    }
					    System.out.println();
					
					        // Shut down the connection to the DBMS.
					
					    stmt.close();
					    
					    
					    
					    
					    query = "select count(precname) as COLLEGE from polling12 where upper(precname) like \'%COLLEGE%\'";
						
						stmt = dbconn.createStatement();
						answer = stmt.executeQuery(query);
						
						//stmt.close();
					
				
					    if (answer != null) {
					
					     //   System.out.println("\nThe results of the query [" + query 
					       //                  + "] are:\n");
					
					            // Get the data about the query result to learn
					            // the attribute names and use them as column headers
					
					        ResultSetMetaData answermetadata = answer.getMetaData();
					
					        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
					            System.out.print(answermetadata.getColumnName(i) + "\t");
					        }
					        System.out.println();
					
					            // Use next() to advance cursor through the result
					            // tuples and print their attribute values
					
					        while (answer.next()) {
					            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
					            System.out.println(answer.getString("college"));
					        }
					    }
					    System.out.println();
					
					        // Shut down the connection to the DBMS.
					
					    stmt.close();
				    
				    
					    query = "select count(precname) as HALL from polling12 where upper(precname) like \'%HALL%\'";
						
						stmt = dbconn.createStatement();
						answer = stmt.executeQuery(query);
						
						//stmt.close();
					
				
					    if (answer != null) {
					
					     //   System.out.println("\nThe results of the query [" + query 
					       //                  + "] are:\n");
					
					            // Get the data about the query result to learn
					            // the attribute names and use them as column headers
					
					        ResultSetMetaData answermetadata = answer.getMetaData();
					
					        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
					            System.out.print(answermetadata.getColumnName(i) + "\t");
					        }
					        System.out.println();
					
					            // Use next() to advance cursor through the result
					            // tuples and print their attribute values
					
					        while (answer.next()) {
					            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
					            System.out.println(answer.getString("hall"));
					        }
					    }
					    System.out.println();
					
					        // Shut down the connection to the DBMS.
					
					    stmt.close();
				    
					    
					    query = "select count(precname) as SCHOOL from polling12 where upper(precname) like \'%SCHOOL%\'";
						
						stmt = dbconn.createStatement();
						answer = stmt.executeQuery(query);
						
						//stmt.close();
					
				
					    if (answer != null) {
					
					     //   System.out.println("\nThe results of the query [" + query 
					       //                  + "] are:\n");
					
					            // Get the data about the query result to learn
					            // the attribute names and use them as column headers
					
					        ResultSetMetaData answermetadata = answer.getMetaData();
					
					        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
					            System.out.print(answermetadata.getColumnName(i) + "\t");
					        }
					        System.out.println();
					
					            // Use next() to advance cursor through the result
					            // tuples and print their attribute values
					
					        while (answer.next()) {
					            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
					            System.out.println(answer.getString("school"));
					        }
					    }
					    System.out.println();
					
					        // Shut down the connection to the DBMS.
					
					    stmt.close();
					    
					    
							
						}
						else if(usrInp.equals("2014")) {
							query = "select count(precname) as CHURCH from polling14 where upper(precname) like \'%CHURCH%\'";
							
							stmt = dbconn.createStatement();
							answer = stmt.executeQuery(query);
							
							//stmt.close();
						
					
						    if (answer != null) {
						
						     //   System.out.println("\nThe results of the query [" + query 
						       //                  + "] are:\n");
						
						            // Get the data about the query result to learn
						            // the attribute names and use them as column headers
						
						        ResultSetMetaData answermetadata = answer.getMetaData();
						
						        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						            System.out.print(answermetadata.getColumnName(i) + "\t");
						        }
						        System.out.println();
						
						            // Use next() to advance cursor through the result
						            // tuples and print their attribute values
						
						        while (answer.next()) {
						            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
						            System.out.println(answer.getString("church"));
						        }
						    }
						    System.out.println();
						
						        // Shut down the connection to the DBMS.
						
						    stmt.close();
						    
						    
						    
						    
						    query = "select count(precname) as COLLEGE from polling14 where upper(precname) like \'%COLLEGE%\'";
							
							stmt = dbconn.createStatement();
							answer = stmt.executeQuery(query);
							
							//stmt.close();
						
					
						    if (answer != null) {
						
						     //   System.out.println("\nThe results of the query [" + query 
						       //                  + "] are:\n");
						
						            // Get the data about the query result to learn
						            // the attribute names and use them as column headers
						
						        ResultSetMetaData answermetadata = answer.getMetaData();
						
						        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						            System.out.print(answermetadata.getColumnName(i) + "\t");
						        }
						        System.out.println();
						
						            // Use next() to advance cursor through the result
						            // tuples and print their attribute values
						
						        while (answer.next()) {
						            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
						            System.out.println(answer.getString("college"));
						        }
						    }
						    System.out.println();
						
						        // Shut down the connection to the DBMS.
						
						    stmt.close();
					    
					    
						    query = "select count(precname) as HALL from polling14 where upper(precname) like \'%HALL%\'";
							
							stmt = dbconn.createStatement();
							answer = stmt.executeQuery(query);
							
							//stmt.close();
						
					
						    if (answer != null) {
						
						     //   System.out.println("\nThe results of the query [" + query 
						       //                  + "] are:\n");
						
						            // Get the data about the query result to learn
						            // the attribute names and use them as column headers
						
						        ResultSetMetaData answermetadata = answer.getMetaData();
						
						        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						            System.out.print(answermetadata.getColumnName(i) + "\t");
						        }
						        System.out.println();
						
						            // Use next() to advance cursor through the result
						            // tuples and print their attribute values
						
						        while (answer.next()) {
						            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
						            System.out.println(answer.getString("hall"));
						        }
						    }
						    System.out.println();
						
						        // Shut down the connection to the DBMS.
						
						    stmt.close();
					    
						    
						    query = "select count(precname) as SCHOOL from polling14 where upper(precname) like \'%SCHOOL%\'";
							
							stmt = dbconn.createStatement();
							answer = stmt.executeQuery(query);
							
							//stmt.close();
						
					
						    if (answer != null) {
						
						     //   System.out.println("\nThe results of the query [" + query 
						       //                  + "] are:\n");
						
						            // Get the data about the query result to learn
						            // the attribute names and use them as column headers
						
						        ResultSetMetaData answermetadata = answer.getMetaData();
						
						        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						            System.out.print(answermetadata.getColumnName(i) + "\t");
						        }
						        System.out.println();
						
						            // Use next() to advance cursor through the result
						            // tuples and print their attribute values
						
						        while (answer.next()) {
						            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
						            System.out.println(answer.getString("school"));
						        }
						    }
						    System.out.println();
						
						        // Shut down the connection to the DBMS.
						
						    stmt.close();
						    
					    
							
							
							
						}
						else if(usrInp.equals("2016")) {
							query = "select count(precname) as CHURCH from polling16 where upper(precname) like \'%CHURCH%\'";
							
							stmt = dbconn.createStatement();
							answer = stmt.executeQuery(query);
							
							//stmt.close();
						
					
						    if (answer != null) {
						
						     //   System.out.println("\nThe results of the query [" + query 
						       //                  + "] are:\n");
						
						            // Get the data about the query result to learn
						            // the attribute names and use them as column headers
						
						        ResultSetMetaData answermetadata = answer.getMetaData();
						
						        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						            System.out.print(answermetadata.getColumnName(i) + "\t");
						        }
						        System.out.println();
						
						            // Use next() to advance cursor through the result
						            // tuples and print their attribute values
						
						        while (answer.next()) {
						            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
						            System.out.println(answer.getString("church"));
						        }
						    }
						    System.out.println();
						
						        // Shut down the connection to the DBMS.
						
						    stmt.close();
						    
						    
						    
						    
						    query = "select count(precname) as COLLEGE from polling16 where upper(precname) like \'%COLLEGE%\'";
							
							stmt = dbconn.createStatement();
							answer = stmt.executeQuery(query);
							
							//stmt.close();
						
					
						    if (answer != null) {
						
						     //   System.out.println("\nThe results of the query [" + query 
						       //                  + "] are:\n");
						
						            // Get the data about the query result to learn
						            // the attribute names and use them as column headers
						
						        ResultSetMetaData answermetadata = answer.getMetaData();
						
						        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						            System.out.print(answermetadata.getColumnName(i) + "\t");
						        }
						        System.out.println();
						
						            // Use next() to advance cursor through the result
						            // tuples and print their attribute values
						
						        while (answer.next()) {
						            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
						            System.out.println(answer.getString("college"));
						        }
						    }
						    System.out.println();
						
						        // Shut down the connection to the DBMS.
						
						    stmt.close();
					    
					    
						    query = "select count(precname) as HALL from polling16 where upper(precname) like \'%HALL%\'";
							
							stmt = dbconn.createStatement();
							answer = stmt.executeQuery(query);
							
							//stmt.close();
						
					
						    if (answer != null) {
						
						     //   System.out.println("\nThe results of the query [" + query 
						       //                  + "] are:\n");
						
						            // Get the data about the query result to learn
						            // the attribute names and use them as column headers
						
						        ResultSetMetaData answermetadata = answer.getMetaData();
						
						        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						            System.out.print(answermetadata.getColumnName(i) + "\t");
						        }
						        System.out.println();
						
						            // Use next() to advance cursor through the result
						            // tuples and print their attribute values
						
						        while (answer.next()) {
						            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
						            System.out.println(answer.getString("hall"));
						        }
						    }
						    System.out.println();
						
						        // Shut down the connection to the DBMS.
						
						    stmt.close();
					    
						    
						    query = "select count(precname) as SCHOOL from polling16 where upper(precname) like \'%SCHOOL%\'";
							
							stmt = dbconn.createStatement();
							answer = stmt.executeQuery(query);
							
							//stmt.close();
						
					
						    if (answer != null) {
						
						     //   System.out.println("\nThe results of the query [" + query 
						       //                  + "] are:\n");
						
						            // Get the data about the query result to learn
						            // the attribute names and use them as column headers
						
						        ResultSetMetaData answermetadata = answer.getMetaData();
						
						        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						            System.out.print(answermetadata.getColumnName(i) + "\t");
						        }
						        System.out.println();
						
						            // Use next() to advance cursor through the result
						            // tuples and print their attribute values
						
						        while (answer.next()) {
						            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
						            System.out.println(answer.getString("school"));
						        }
						    }
						    System.out.println();
						
						        // Shut down the connection to the DBMS.
						
						    stmt.close();
							
						}
						else if(usrInp.equals("2018")) {
							query = "select count(precname) as CHURCH from polling18 where upper(precname) like \'%CHURCH%\'";
							
							stmt = dbconn.createStatement();
							answer = stmt.executeQuery(query);
							
							//stmt.close();
						
					
						    if (answer != null) {
						
						     //   System.out.println("\nThe results of the query [" + query 
						       //                  + "] are:\n");
						
						            // Get the data about the query result to learn
						            // the attribute names and use them as column headers
						
						        ResultSetMetaData answermetadata = answer.getMetaData();
						
						        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						            System.out.print(answermetadata.getColumnName(i) + "\t");
						        }
						        System.out.println();
						
						            // Use next() to advance cursor through the result
						            // tuples and print their attribute values
						
						        while (answer.next()) {
						            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
						            System.out.println(answer.getString("church"));
						        }
						    }
						    System.out.println();
						
						        // Shut down the connection to the DBMS.
						
						    stmt.close();
						    
						    
						    
						    
						    query = "select count(precname) as COLLEGE from polling18 where upper(precname) like \'%COLLEGE%\'";
							
							stmt = dbconn.createStatement();
							answer = stmt.executeQuery(query);
							
							//stmt.close();
						
					
						    if (answer != null) {
						
						     //   System.out.println("\nThe results of the query [" + query 
						       //                  + "] are:\n");
						
						            // Get the data about the query result to learn
						            // the attribute names and use them as column headers
						
						        ResultSetMetaData answermetadata = answer.getMetaData();
						
						        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						            System.out.print(answermetadata.getColumnName(i) + "\t");
						        }
						        System.out.println();
						
						            // Use next() to advance cursor through the result
						            // tuples and print their attribute values
						
						        while (answer.next()) {
						            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
						            System.out.println(answer.getString("college"));
						        }
						    }
						    System.out.println();
						
						        // Shut down the connection to the DBMS.
						
						    stmt.close();
					    
					    
						    query = "select count(precname) as HALL from polling18 where upper(precname) like \'%HALL%\'";
							
							stmt = dbconn.createStatement();
							answer = stmt.executeQuery(query);
							
							//stmt.close();
						
					
						    if (answer != null) {
						
						     //   System.out.println("\nThe results of the query [" + query 
						       //                  + "] are:\n");
						
						            // Get the data about the query result to learn
						            // the attribute names and use them as column headers
						
						        ResultSetMetaData answermetadata = answer.getMetaData();
						
						        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						            System.out.print(answermetadata.getColumnName(i) + "\t");
						        }
						        System.out.println();
						
						            // Use next() to advance cursor through the result
						            // tuples and print their attribute values
						
						        while (answer.next()) {
						            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
						            System.out.println(answer.getString("hall"));
						        }
						    }
						    System.out.println();
						
						        // Shut down the connection to the DBMS.
						
						    stmt.close();
					    
						    
						    query = "select count(precname) as SCHOOL from polling18 where upper(precname) like \'%SCHOOL%\'";
							
							stmt = dbconn.createStatement();
							answer = stmt.executeQuery(query);
							
							//stmt.close();
						
					
						    if (answer != null) {
						
						     //   System.out.println("\nThe results of the query [" + query 
						       //                  + "] are:\n");
						
						            // Get the data about the query result to learn
						            // the attribute names and use them as column headers
						
						        ResultSetMetaData answermetadata = answer.getMetaData();
						
						        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
						            System.out.print(answermetadata.getColumnName(i) + "\t");
						        }
						        System.out.println();
						
						            // Use next() to advance cursor through the result
						            // tuples and print their attribute values
						
						        while (answer.next()) {
						            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
						            System.out.println(answer.getString("school"));
						        }
						    }
						    System.out.println();
						
						        // Shut down the connection to the DBMS.
						
						    stmt.close();
						}
						else {
							System.out.println("User Entered Illegal Year Number. Rerun Query.");
						}
						
						//final String query = "select * from ( select countyname, count(*) as preccount from "
						//		+ "polling12"
						//		+ " group by countyname order by preccount desc ) where rownum < 6";
			
						
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
			else if(usrInp.equals("d")) {
				
				
				
				
				
				
				
				
				final String oracleURL =   // Magic lectura -> aloe access spell
		                "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";
				
				String username = null,    // Oracle DBMS username
				       password = null;    // Oracle DBMS password

				username = "omarraef1";
				password = "a9572";
				
		//		if (args.length == 2) {    // get username/password from cmd line args
		//		    //username = args[0];
		//		    //password = args[1];
		//			username = "omarraef1";
		//			password = "a9572";
		//		} else {
		//		    System.out.println("\nUsage:  java JDBC <username> <password>\n"
		//		                     + "    where <username> is your Oracle DBMS"
		//		                     + " username,\n    and <password> is your Oracle"
		//		                     + " password (not your system password).\n");
		//		    System.exit(-1);
		//		}
				
				
				    // load the (Oracle) JDBC driver by initializing its base
				    // class, 'oracle.jdbc.OracleDriver'.
				
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
				
				try {
					Statement stmt = null;
					ResultSet answer = null;
					
					ArrayList<String[]> queryList = new ArrayList<>();
					ArrayList<String[]> queryList2 = new ArrayList<>();
					ArrayList<String[]> queryList3 = new ArrayList<>();
					ArrayList<String[]> queryList4 = new ArrayList<>();
					
					final String query = "select countyname, count(*) as preccount from polling12 group by countyname order by countyname asc";

					final String query2 = "select countyname, count(*) as preccount from polling14 group by countyname order by countyname asc";
					
					final String query3 = "select countyname, count(*) as preccount from polling16 group by countyname order by countyname asc";

					final String query4 = "select countyname, count(*) as preccount from polling18 group by countyname order by countyname asc";
					
					stmt = dbconn.createStatement();
					answer = stmt.executeQuery(query);
					
				    
				    if (answer != null) {
				
				            // Get the data about the query result to learn
				            // the attribute names and use them as column headers
				
				        ResultSetMetaData answermetadata = answer.getMetaData();
				
				        //System.out.println();
				
				            // Use next() to advance cursor through the result
				            // tuples and print their attribute values
				
				        while (answer.next()) {
				            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
				         //   System.out.format("%-20s %5s\n", answer.getString("countyname"), answer.getString("preccount"));
				        	String[] tmp = {answer.getString("countyname"), answer.getString("preccount")};
				        	queryList.add(tmp);
				        }
				    }
				    //System.out.println();
				
				        // Shut down the connection to the DBMS.

				    stmt.close();
				    
				    
				    stmt = dbconn.createStatement();
					answer = stmt.executeQuery(query2);
					
				    
				    if (answer != null) {
				
				            // Get the data about the query result to learn
				            // the attribute names and use them as column headers
				
				        ResultSetMetaData answermetadata = answer.getMetaData();
				
				        //System.out.println();
				
				            // Use next() to advance cursor through the result
				            // tuples and print their attribute values
				
				        while (answer.next()) {
				            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
				         //   System.out.format("%-20s %5s\n", answer.getString("countyname"), answer.getString("preccount"));
				        	String[] tmp = {answer.getString("countyname"), answer.getString("preccount")};
				        	queryList2.add(tmp);
				        }
				    }
				    //System.out.println();
				
				        // Shut down the connection to the DBMS.

				    stmt.close();
				    

				    stmt = dbconn.createStatement();
					answer = stmt.executeQuery(query3);
					
				    
				    if (answer != null) {
				
				            // Get the data about the query result to learn
				            // the attribute names and use them as column headers
				
				        ResultSetMetaData answermetadata = answer.getMetaData();
				
				        //System.out.println();
				
				            // Use next() to advance cursor through the result
				            // tuples and print their attribute values
				
				        while (answer.next()) {
				            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
				         //   System.out.format("%-20s %5s\n", answer.getString("countyname"), answer.getString("preccount"));
				        	String[] tmp = {answer.getString("countyname"), answer.getString("preccount")};
				        	queryList3.add(tmp);
				        }
				    }
				    //System.out.println();
				
				        // Shut down the connection to the DBMS.

				    stmt.close();
				    
				    

				    stmt = dbconn.createStatement();
					answer = stmt.executeQuery(query4);
					
				    
				    if (answer != null) {
				
				            // Get the data about the query result to learn
				            // the attribute names and use them as column headers
				
				        ResultSetMetaData answermetadata = answer.getMetaData();
				
				        //System.out.println();
				
				            // Use next() to advance cursor through the result
				            // tuples and print their attribute values
				
				        while (answer.next()) {
				            //System.out.println(answer.getString("countyname") + "\t" + answer.getString("preccount"));
				         //   System.out.format("%-20s %5s\n", answer.getString("countyname"), answer.getString("preccount"));
				        	String[] tmp = {answer.getString("countyname"), answer.getString("preccount")};
				        	queryList4.add(tmp);
				        }
				    }
				    //System.out.println();
				
				        // Shut down the connection to the DBMS.

				    stmt.close();
				    
				    dbconn.close();
				    
				    
				    ArrayList<String> noLoss = new ArrayList<>();
				    ArrayList<String> noGain = new ArrayList<>();
				    
				    // logic: did not lose
				    for (int i = 0; i < queryList.size(); i++) {
				    	if ((Integer.valueOf(queryList.get(i)[1]) == Integer.valueOf(queryList2.get(i)[1])) || 
				    			(Integer.valueOf(queryList.get(i)[1]) < Integer.valueOf(queryList2.get(i)[1]))) {
				    		if ((Integer.valueOf(queryList2.get(i)[1]) == Integer.valueOf(queryList3.get(i)[1])) || 
					    			(Integer.valueOf(queryList2.get(i)[1]) < Integer.valueOf(queryList3.get(i)[1]))) {
				    			if ((Integer.valueOf(queryList3.get(i)[1]) == Integer.valueOf(queryList4.get(i)[1])) || 
						    			(Integer.valueOf(queryList3.get(i)[1]) < Integer.valueOf(queryList4.get(i)[1]))) {
						    		noLoss.add(queryList.get(i)[0]);
						    	}
					    	}
				    	}
				    	
				    }
				    for (int i = 0; i < queryList.size(); i++) {
				    	if ((Integer.valueOf(queryList.get(i)[1]) == Integer.valueOf(queryList2.get(i)[1])) || 
				    			(Integer.valueOf(queryList.get(i)[1]) > Integer.valueOf(queryList2.get(i)[1]))) {
				    		if ((Integer.valueOf(queryList2.get(i)[1]) == Integer.valueOf(queryList3.get(i)[1])) || 
					    			(Integer.valueOf(queryList2.get(i)[1]) > Integer.valueOf(queryList3.get(i)[1]))) {
				    			if ((Integer.valueOf(queryList3.get(i)[1]) == Integer.valueOf(queryList4.get(i)[1])) || 
						    			(Integer.valueOf(queryList3.get(i)[1]) > Integer.valueOf(queryList4.get(i)[1]))) {
						    		noGain.add(queryList.get(i)[0]);
						    	}
					    	}
				    	}
				    	
				    }
				    
				    
				    
				    System.out.println("Counties that did not LOSE precincts in all three pairs of years:");
				    System.out.println();
				    // did not lose list
				    for (int i = 0; i<noLoss.size();i++) {
				    	System.out.println(noLoss.get(i).toString());
				    }
				    System.out.println();

				    System.out.println("Counties that did not GAIN precincts in all three pairs of years:");
				    System.out.println();
				    // did not gain list
				    for (int i = 0; i<noGain.size();i++) {
				    	System.out.println(noGain.get(i).toString());
				    }
				    System.out.println();
				
				} catch (SQLException e) {
				
				        System.err.println("*** SQLException:  "
				            + "Could not fetch query results.");
				        System.err.println("\tMessage:   " + e.getMessage());
				        System.err.println("\tSQLState:  " + e.getSQLState());
				        System.err.println("\tErrorCode: " + e.getErrorCode());
				        System.exit(-1);
				}
				
				
				
				
				
				
			}
			else if(usrInp.equals("quit")) {
				break;
			}
			else if(usrInp.equals("menu")) {
				System.out.println();
				System.out.println("|| Query Menu || Enter a, b, c, d, or quit or menu (Case sensitive)");
				System.out.println();
				System.out.println("a) Number of precincts in each county in 2012.");
				System.out.println("b) Top five municipalities by number of precincts (for a given year).");
				System.out.println("c) Number of precinct names containing words CHURCH, HALL, SCHOOL, or COLLEGE (for a given year).");
				System.out.println("d) Show counties that did not lose precincts in all three pairs of subsequent years\n"
						+ "   V.S counties that did not gain precincts in all three pairs of subsequent years.");
				System.out.println("quit) Exit Program.");
				System.out.println("menu) View Menu.");
				System.out.println();
			}
			System.out.println();
			System.out.println("Enter Next Query (a, b, c, d, or quit or menu): ");
		}
		System.out.println();
		System.out.println("System quit.");
		// close scanner
		sc.close();
		
	}

}
