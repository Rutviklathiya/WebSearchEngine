package Project;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlToText {
	public static void Conversion(String inpPath, String outPath) {		
		String s,file=""; 
		try {
			File location= new File(inpPath);
			TextFile obj= new TextFile();
			File[] listoffiles = location.listFiles();
			for(File f : listoffiles){
				obj.convertFile(f);
				Document docr = Jsoup.parse(f, "utf-8"); 
				s=docr.text();
				file=f.getName();
				file=file.split("\\.",2)[0];
				file=file+".txt";
				BufferedWriter writer = new BufferedWriter( new FileWriter(outPath+file));
				writer.write(s);
				writer.close();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}