package Project;
import java.io.*;
import java.util.ArrayList;

public class BruteForce {
	
	public static void Handle(String TextPath, String search) throws IOException {
		String text;
        String data="";
        int off_set = 0;	
		File fl= new File(TextPath);
		TextFile obj= new TextFile();
		File[] list = fl.listFiles();
		
		ArrayList<String> filelist = new ArrayList<String>();
		ArrayList<Integer> occurrence= new ArrayList<Integer>();
		for(File f : list)
		{
			BufferedReader br = new BufferedReader(new FileReader(f));   
			   while((text = br.readLine()) != null) 
				     data=data+text;
			   
				   off_set = search_1(search, data, f.getName());
		       	filelist.add(f.getName());
		       	occurrence.add(off_set);
			 br.close();
		}
		
		/** Sorting highest occurrence of word in file **/
		
		for(int i=1;i<6;i++){
			System.out.println("File " + filelist.get(filelist.size() - i) +" has occurrence of " + search + " :: "+ occurrence.get(occurrence.size() - i));
		}		
	}
	
	// return off_set of first match or N if no match
    public static int search_1(String pat, String text, String fiename) {
       int M = pat.length();
       int N = text.length();
       ArrayList<Integer>off_set=new ArrayList<Integer>();

       for (int i = 0; i <= N - M; i++) {
           int j;
           for (j = 0; j < M; j++) {
               if (text.charAt(i+j) != pat.charAt(j))
                   break;
           }
           if (j == M)
           {            // found at off_set i
        	   off_set.add(i);
           }
       }
       System.out.println(" Number of occurrence is : " + off_set.size());
       return off_set.size();                             
   }
}