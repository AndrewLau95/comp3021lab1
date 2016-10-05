package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note {

	private String content;
	private static final long serialVersionUID = 1L;

	
	public TextNote(String title){
		super(title);
	}
	
	
	public TextNote(String title, String content){
		super(title);
		this.content = content;
	}
	
	public TextNote(File f){
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	public String getContent(){
		return content;
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		String Line;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try{
			fis = new FileInputStream(absolutePath);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			while ((Line = br.readLine()) != null){
				result = result + Line;
			}
			br.close();
		} catch (FileNotFoundException fnfE){
			fnfE.getStackTrace();
		} catch (IOException ioE){
			ioE.getStackTrace();
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder){
		BufferedWriter bw = null;
		try{
		File file = new File(pathFolder + this.getTitle().replaceAll("\\s+", "_") + ".txt");
			if(!file.exists()){
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			bw.write(this.content);
			bw.close();
			
			
		} catch (IOException ioE){
			ioE.printStackTrace();
		}
	}
}
