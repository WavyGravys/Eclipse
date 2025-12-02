package file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Saver {
	private String path;
	private FileReader reader;
	private FileWriter writer;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	
	public Saver(String name) {
		path = getPath(name);
		try {
			new File("saves").mkdir();
			File saveFile = new File(path);
			saveFile.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private String getPath(String name) {
		return "saves\\" + name + "_save.txt";
	}
	
	public boolean saveFileEmpty() {
		File file = new File(this.path);
		return file.length() == 0;
	}
	
	public String[] readSaveFile() {
		try {
			reader = new FileReader(path);
			bufferedReader = new BufferedReader(reader);
			
			int lines = (int) Files.lines(Paths.get(path)).count();
	        String[] data = new String[lines];
	        
	        int i = 0;
	        String line;
	        while ((line = bufferedReader.readLine()) != null) {
	            data[i++] = line;
	        }
	        
	        bufferedReader.close();
	        reader.close();
	        
	        if (data.length == 0) {
	        	return null;
	        }
	        return data;
	    }  catch (Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}

	public void writeSaveFile(String[] data) {
		try {
			writer = new FileWriter(path, false);
			bufferedWriter = new BufferedWriter(writer);
			
			if (data.length == 0) {
				return;
			}
			
			for (int i = 0; i < data.length - 1; i++) {
	        	bufferedWriter.write(data[i]);
	        	bufferedWriter.newLine();
	        }
	        bufferedWriter.write(data[data.length - 1]);
	        
	        bufferedWriter.close();
			writer.close();
	    } catch (Exception e) {
	    	e.printStackTrace();	
	    }
	}
}
