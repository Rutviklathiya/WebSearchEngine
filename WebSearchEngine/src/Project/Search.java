package Project;
import java.io.IOException;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Search {

	public static void main(String[] args) throws IOException, InterruptedException{
		
		String TextPath = "/home/rutvik/Downloads/Advanced Computing Concepts/ACC Final Project-Group 16/Text";
				
		
		Scanner sc = new Scanner(System.in);
		String search;
		
		Set<String> suggestedWords;
		String opt;
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
		System.out.println("Do you want to check the associated links? (Y/N)");
		String reply = sc.next().toLowerCase();
		if(reply.equals("y"))
			RegularExpression.URLFinder(TextPath);
		else
			System.out.println("Good Bye");
		sc.close();
	}
}