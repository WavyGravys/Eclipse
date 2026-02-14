package speicher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import _main.Mietvertrag;

public class SpeicherManager {
	private String path;
	private File saveFile;
	private FileReader reader;
	private FileWriter writer;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	
	public ArrayList<Mietvertrag> vertraege;
	
	public SpeicherManager(String name) {
		path = "saves\\" + name + "-speicher.txt";
		try {
			// tries creating "saves" directory
			new File("saves").mkdir();
			// tries creating save_name.txt file
			saveFile = new File(path);
			saveFile.createNewFile();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String[] readSaveFile() {
		try {
	        String[] data = readData();
	        
	        if (data.length == 0) {
	        	throw new IOException("no data was read.");
	        }
	        return data;
	        
	    }  catch (IOException e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	
	private String[] readData() throws IOException {
		int lineCount = (int) Files.lines(Paths.get(path)).count();
		String[] data = new String[lineCount]; 
		
		openReader();
		
		int i = 0;
	    	String line;
	        while ((line = bufferedReader.readLine()) != null) {
	        	data[i++] = line;
	        }
		
		closeReader();
		
		return data;
	}
	
	private void openReader() throws IOException {
		reader = new FileReader(path);
		bufferedReader = new BufferedReader(reader);
	}
	
	private void closeReader() throws IOException {
		bufferedReader.close();
        reader.close();
	}
	
	
	public void writeSaveFile(String[] data) {
		try {
			if (data.length == 0) {
				throw new Exception("no data was saved.");
			}
			
			writeData(data);
	    
		} catch (Exception e) {
	    	e.printStackTrace();	
	    }
	}
	
	private void writeData(String[] data) throws IOException{
		openWriter();
		
		for (int i = 0; i < data.length - 1; i++) {
        	bufferedWriter.write(data[i]);
        	bufferedWriter.newLine();
        }
        bufferedWriter.write(data[data.length - 1]);
        
        closeWriter();
	}
	
	private void openWriter() throws IOException {
		writer = new FileWriter(path, false);
		bufferedWriter = new BufferedWriter(writer);
	}
	
	private void closeWriter() throws IOException {
		bufferedWriter.close();
		writer.close();
	}
	
	
	public boolean isSaveFileEmpty() {
		return saveFile.length() == 0;
	}
}

