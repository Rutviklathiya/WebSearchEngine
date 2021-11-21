package Project;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TextFile {
	
	public static void main(String[] args) {
		File fileLocation= new File("/home/rutvik/Downloads/Advanced Computing Concepts/ACC Final Project-Group 16/Text");
		TextFile obj= new TextFile();
		File[] listOfFiles = fileLocation.listFiles();
		for(File file : listOfFiles){
			obj.convertFile(file);
		}
	}
	
	public void convertFile(File file) {		
		String s,fileName=""; 
		try {
				Document doc = Jsoup.parse(file, "utf-8"); 
				s=doc.text();
				fileName=file.getName();
				fileName=fileName.split("\\.",2)[0];
				fileName=fileName+".txt";
				BufferedWriter writer = new BufferedWriter( new FileWriter("/home/rutvik/Downloads/Advanced Computing Concepts/project_files/"+fileName));
				writer.write(s);
				writer.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}