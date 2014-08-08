package scraper;


import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Properties {
	
	private boolean wordFlag;
	private boolean charFlag;
	private boolean sentenceFlag;
	private boolean timerFlag;
	
	List<URL> urlList;
	List<String> wordList;
	
	public Properties(){
		wordFlag = false;
		charFlag = false;
		sentenceFlag = false;
		timerFlag = false;
		
		urlList = new ArrayList<URL>();
		wordList = new ArrayList<String>();
	}
	
	
	public boolean isWordFlag() {
		return wordFlag;
	}
	public void setWordFlag(boolean wordFlag) {
		this.wordFlag = wordFlag;
	}
	public boolean isCharFlag() {
		return charFlag;
	}
	public void setCharFlag(boolean charFlag) {
		this.charFlag = charFlag;
	}
	public boolean isSentenceFlag() {
		return sentenceFlag;
	}
	public void setSentenceFlag(boolean sentenceFlag) {
		this.sentenceFlag = sentenceFlag;
	}
	public boolean isTimerFlag() {
		return timerFlag;
	}
	public void setTimerFlag(boolean timerFlag) {
		this.timerFlag = timerFlag;
	}

}
