package Project;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SearchEngine {

	public static void main(String[] args) throws IOException, InterruptedException{
		
		String TextPath = "src/Project/Text/";
			
		
		Scanner sc = new Scanner(System.in);
		String search = null;
		
		Set<String> suggestedWords;
		String opt;
		
		File f = new File("src/Project/Text/");
		
		if(f.list().length==0) {
			System.out.println("Text Files do not exists.");
			System.out.println("Let's convert some html files into text files. \nPress y to convert");
			String reply = sc.next().toLowerCase();
			if(reply.equals("y")) {
				HtmlToText.main(null);
			}
			else {
				System.out.println("Good Bye");
			}
		}
		
		while(true) {
			System.out.println("Enter the search string-: ");
			search = sc.next();
			search = search.toLowerCase();
			
			suggestedWords = Distance.suggest(TextPath, search);
			for(String s:suggestedWords) {
				search = s;
				System.out.println(s);
			}
			if(suggestedWords.size() == 0)
			{
				break;
			}
			else {
				System.out.println("Did you mean ?(Y/N) ");
				opt = sc.next().toLowerCase();
				if(opt.equals("y"))
				{
					break;
				}
				else
				{
					continue;
				}
				
			}
		}
			
		// Brute Force is used to find occurance of words with Offset and Sorting
		
		BruteForce.Handle(TextPath,search);
		
		// This Code of block is used to fetch the URLs
		System.out.println();
		System.out.println("Do you want to check the associated links? (Y/N)");
		String reply = sc.next().toLowerCase();
		if(reply.equals("y"))
			RegularExpression.URLFinder(TextPath);
		else
			System.out.println("Good Bye");
		sc.close();
	}
}