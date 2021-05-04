import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class draft {


public static void main(String[] args) throws FileNotFoundException {
	ArrayList<String[]> places14 = readFilesYieldMaxLengths("src/mtpollingplaces2012.csv");
	
	for(int i = 0; i<places14.size();i++) {
		String query =       // our test query
                "INSERT INTO polling12 values (\'"+places14.get(i)[0]+"\',\'"
                + places14.get(i)[1] + "\',\'"
                + places14.get(i)[2].replace("&", "\'\'||chr(38)||\'\'") + "\',\'"
                + places14.get(i)[3] + "\',\'"
                + places14.get(i)[4].replace("\'", "\'\'").replace("&", "\'\'||chr(38)||\'\'") + "\',\'"
                + places14.get(i)[5] + "\',\'"
                + places14.get(i)[6].replace("\'", "\'\'").replace("&", "\'\'||chr(38)||\'\'") + "\',\'"
                + places14.get(i)[7].replace("\"", "").replace("\'", "\'\'").replace("&", "\'\'||chr(38)||\'\'") + "\')";
		System.out.println(query);
	}
	//String x = "\"asfaffga \"";
	//System.out.println(x.replace("\"", ""));
	System.out.println("\'");
	
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	
	String x = "5";
	System.out.println(Integer.valueOf(x));
	if(Integer.valueOf(x) == 5) {
		System.out.println(true);
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
