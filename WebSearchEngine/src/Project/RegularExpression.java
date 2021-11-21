package Project;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;



public class RegularExpression {

	ArrayList<String> URLs= new ArrayList<>();
	
	public static void URLFinder(String TextPath) {
		String data="",line="";
		RegularExpression obj=new RegularExpression();
		File location = new File(TextPath);
		File[] listofFiles=location.listFiles();
		for(File f: listofFiles) {
			try {
				BufferedReader br = new BufferedReader( new FileReader(f));
				while((line=br.readLine()) != null) {
	 			   data=data+line;
				}
				obj.findUrls(data);
				br.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		System.out.println("Links in documents :: ");
		for(String k: obj.URLs) {
			System.out.println(k);	
		}
	}

	
	
	public ArrayList<String> findUrls(String data) {
		String checkword[];
		boolean flag;
		checkword=data.split(" ");
		for(String k: checkword) {			

			flag=Pattern.matches("^(http|https)[\\S]*", k);
			if(flag==true) {
				if(!URLs.contains(k)) {
					URLs.add(k); 
				}
			}
		}  
		return URLs;
	}
}
