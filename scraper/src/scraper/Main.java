package scraper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Main {

	static Properties properties = new Properties();

	public static void main(String[] args) throws IOException {
		
		if ((isArgsParsed(args) == false) || args[0].equals("-help")) {
			printHelp();
			return; 
		}
		Scraper scraper = new Scraper(properties);
		List<Results> resList = scraper.scrape();
		
		printResults(resList);
		
		
	}

	public static boolean isArgsParsed(String[] args) throws IOException {

		if (args.length < 2 || args.length > 6) {
			System.out.println("Wrong number of arguments");
			return false;
		}

		parseUrl(args[0]);
		parseWords(args[1]);
		
		if( parseKeys(args) == false){
			System.out.println("Wrong command");
			return false;
		}
		
		return true;
	}

	public static void parseUrl(String arg) throws IOException {

		File file = new File(arg);
		if (file.isFile()) {
			BufferedReader rdr = new BufferedReader(new FileReader(file));
			try {
				while (true) {
					String name = rdr.readLine();
					if (name == null) {
						break;
					}
					URL url = new URL(name);
					properties.urlList.add(url);
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new IOException();
			} finally {
				rdr.close();
			}

		} else {
			URL url = new URL(arg);
			properties.urlList.add(url);
		}

	}
	
	public static void parseWords(String arg){
		String[] arr = arg.split(",");
		for( String s : arr){
			properties.wordList.add(s);
		}
	}
	
	public static boolean parseKeys( String[] args){
		
		for( int i = 2 ; i < args.length ; ++i){
			String arg = args[i];
			if( arg.equals("-v") ){
				properties.setTimerFlag(true);
			}
			else if( arg.equals("-w")){
				properties.setWordFlag(true);
			}
			else if ( arg.equals("-c" )){
				properties.setCharFlag(true);
			}
			else if (arg.equals( "-e" )){
				properties.setSentenceFlag(true);
			}
			else{
				return false;
			}
		}
		return true;
		
	}

	public static void printHelp() {
		System.out.println("Program for scraping web rresourses");
		System.out
				.println("Example: java –jar scraper.jar http://www.cnn.com Greece,default –v –w –c –e ");
		System.out
				.println("command line parameters: \n"
						+ "\t- web resources URL or path plain text file containing a list of URLs\n"
						+ "\t- word (or list of words with “,” delimiter\n"
						+ "\t- output verbosity flag,  if on then the output should contains information about time spend on data scraping and data processing (-v)\n\n"
						+ "data commands:\n"
						+ "\t- count number of provided word(s) occurrence on webpage(s). (-w)\n"
						+ "\t- count number of characters of each web page (-c)\n"
						+ "\t- extract sentences’ which contain given words (-e)\n");

	}
	
	public static void printResults(List<Results> resList){
		
		for( Results res : resList){
			res.printResults(properties);
		}
		
	}

}
