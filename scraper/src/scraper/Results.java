package scraper;

import java.util.ArrayList;
import java.util.List;

public class Results {
	
	String name;
	int words;
	int chars;
	long time;
	List<String> sentenceList;
	
	public Results(String name){
		this.name = name;
		words = 0;
		chars = 0;
		time = 0;
		sentenceList = new ArrayList<String>();
	}
	
	public void printResults(Properties properties){
		
		System.out.println(name);
		
		if( properties.isWordFlag()){
		System.out.println("\tKeywords occur " + words + " times");
		if( properties.isCharFlag()){
			System.out.println("\tThere are " + chars + " characters on the page");
		}
		if( properties.isTimerFlag()){
			System.out.println("\tTime spended to scpaping is " + time + " ms");
		}
		if( properties.isSentenceFlag()){
			System.out.println("\tSentences contains keywords:");
			for( String s : sentenceList){
				System.out.println("\t\t" + s);
			}
		}
		System.out.println("");
	}
	}

}
