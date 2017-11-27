package wbb.atm01.views;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSV {

	private ArrayList<String> contents;
	private ArrayList<String[]> csvArray;
	private String userFile;
		
	public CSV(ArrayList<String> fileContents) {
		this.contents = fileContents;
		readCSV();
	}
	
	public CSV(String userFile) {
		this.userFile = userFile;
		try {
			readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readFile() throws IOException{
		FileReader fr = new FileReader(this.userFile);
		BufferedReader textReader = new BufferedReader(fr);
		
		ArrayList<String> fileContents = new ArrayList<String>();
		String line = textReader.readLine();
		fileContents.add(line);
		while( (line = textReader.readLine()) != null ) {
			fileContents.add(line);
		}
		
		textReader.close();
		this.contents = fileContents;
		readCSV();
	}

	public void readCSV() {
		ArrayList<String[]> csvArray = new ArrayList<String[]>();
		
		ArrayList<String> lines = this.contents;
		for(int line = 0; line < lines.size(); line++ ){
			String[] columns = this.readLine(lines.get(line));
			csvArray.add(columns);
		}
		
		this.csvArray = csvArray;
	}
	
	public String[] readLine(String line) {
		String[] csvLine = line.split(",");
		
		return csvLine;
	}
	
	public ArrayList<String[]> getCSV() {
		return this.csvArray;
	}
	
	public String[] getLine(int line) {
		return this.csvArray.get(line);
	}
	
	public String getColumn(int line, int column) {
		return this.csvArray.get(line)[column];
	}

}
