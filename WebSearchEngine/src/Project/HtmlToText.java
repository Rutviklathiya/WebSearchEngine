package Project;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

class convert{
	public void convertFile(File file) {		
		String s,fileName=""; 
		try {
				Document doc = Jsoup.parse(file, "utf-8"); 
				s=doc.text();
				fileName=file.getName();
				fileName=fileName.split("\\.",2)[0];
				fileName=fileName+".txt";
				BufferedWriter writer = new BufferedWriter( new FileWriter("src/Project/Text/"+fileName));
				writer.write(s);
				writer.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class HtmlToText{
	public static void main(String[] args) throws IOException {
		
		File fileLocation= new File("src/Project/HTMLfiles/");
		
		if(fileLocation.list().length==0) {
			System.out.println("Oops! Html content is missing. Let's import some data.");
			WebPageSearch.main(null); 
		}
		convert c = new convert();
		File[] listOfFiles = fileLocation.listFiles();
		for(File file : listOfFiles){
			c.convertFile(file);
		}
	}
	
	
}