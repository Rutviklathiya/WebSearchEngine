package Project;

import java.io.BufferedReader;

import java.io.*;
import java.util.*;

public class Distance {
	
	public static Set<String> suggest(String TextPath, String search) throws IOException
	{
		ArrayList<String> tokens = new ArrayList<String>(); 
		tokens = findTokens(TextPath);
		ArrayList<String> suggestions= new ArrayList<String>();
		for(int i=0;i<tokens.size();i++)
		{
			int dr = editDistance(tokens.get(i), search);
			if(dr == 0) {
				break;
			}
			else if(dr==1) {				
				if(!(tokens.get(i).contains(".") || tokens.get(i).contains(",") || tokens.get(i).contains(" ")))
				{
					suggestions.add(tokens.get(i));
				}
			}
			
		}
		Set<String> suggestedWords = new HashSet<>(suggestions);
		return suggestedWords;
	}
	
	public static ArrayList<String> findTokens(String TextPath) throws IOException
	{
		String text = "";
		ArrayList<String> tokens = new ArrayList<String>();
		File fol= new File(TextPath);
		File[] listoffiles = fol.listFiles();
		String data = "";
		for(File f : listoffiles){
			//System.out.print(f.getName());
			BufferedReader br = new BufferedReader(new FileReader(f));   
			while((text = br.readLine()) != null) 
		   		   data=data+text;
		   	  StringTokenizer st = new StringTokenizer(data);  
		        while (st.hasMoreTokens())   
		      	  tokens.add(st.nextToken().toLowerCase());
			 br.close();
			 
		}
		return tokens;
	}

	public static int editDistance(String word1, String word2) {
		int length1 = word1.length();
		int length2 = word2.length();
	 
		// length1+1, length2+1, because finally return d[length1][length2]
		int[][] d = new int[length1 + 1][length2 + 1];
	 
		for (int i = 0; i <= length1; i++) {
			d[i][0] = i;
		}
	 
		for (int j = 0; j <= length2; j++) {
			d[0][j] = j;
		}
	 
		//iterate though, and check last char
		for (int i = 0; i < length1; i++) {
			char char1 = word1.charAt(i);
			for (int j = 0; j < length2; j++) {
				char char2 = word2.charAt(j);
	 
				//if last two chars equal
				if (char1 == char2) {
					//update d value for +1 length
					d[i + 1][j + 1] = d[i][j];
				} else {
					int replace = d[i][j] + 1;
					int insert = d[i][j + 1] + 1;
					int delete = d[i + 1][j] + 1;
	 
					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					d[i + 1][j + 1] = min;
				}
			}
		}
	 
		return d[length1][length2];
	}
	
	public static void main(String[] args) throws IOException {
		// two arbitrary strings
	}
}