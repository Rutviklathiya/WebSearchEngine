package Project;
import java.io.File;
import java.io.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebPageSearch {
	private static final int MAX_DEPTH = 3;
	private static final int LIMIT = 350;
	private HashSet<String> pagelinks;
	
	public WebPageSearch() {
		pagelinks = new HashSet<>();
	}
	
	public int getNumOfPages() {
		// TODO Auto-generated method stub
		return pagelinks.size();
	}

	public void getPageLinks(String URL, int depth) {
		if ((!pagelinks.contains(URL) && URL.contains("https://priyanshuvlogs.com/") && (depth < MAX_DEPTH))) {
			System.out.println(">> Depth: " + depth + " [" + URL + "]");
			try {
				pagelinks.add(URL);
				Document doc = Jsoup.connect(URL).get();
				Elements links = doc.select("a[href]");
				depth++;
				for (Element page : links) {
					if (pagelinks.size() > LIMIT) {
						break;
					}
					if (page.hasAttr("abs:href"))
						getPageLinks(page.attr("abs:href"), depth);
				}
			} catch (IOException e) {
				System.err.println("For '" + URL + "': " + e.getMessage());
			}
		}
	}

	public void Linkdownload() {
	pagelinks.forEach(k -> {
			Document doc;
			try {
				doc = Jsoup.connect(k).get();
			    
				File file = new File("src/Project/HTMLfiles/"+doc.title()+".html");
		        file.getParentFile().mkdir();
		        PrintWriter out = new PrintWriter(file);
		        
				try {
					String tmp = doc.html();
					out.write(tmp);
				} catch (Exception e) {
					// TODO: for handle exception
					System.out.println("Exception occured because"+ e);
				}
				out.close();
			} catch (IOException e) {
				System.err.println();
			}
		});
	}

	public static void main(String[] args) throws IOException 
	{
		WebPageSearch search = new WebPageSearch();
		search.getPageLinks("https://priyanshuvlogs.com/", 0);
		System.out.println("Downloading web Pages..");
		search.Linkdownload();
		System.out.println("Downloading complete..");
	}
}
