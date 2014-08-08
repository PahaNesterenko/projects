package scraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLEditorKit.Parser;

public class Scraper {

	Properties properties;
	List<Results> resList;
	Parser parser;

	public Scraper(Properties properties) {
		this.properties = properties;
		resList = new ArrayList<Results>();
		parser = new ScannerHTMLEditorKit().getParser();
	}

	public List<Results> scrape() throws IOException {

		for (URL url : properties.urlList) {

			Results results = scrape(url);
			resList.add(results);

		}

		if (resList.size() > 1) {
			Results total = new Results("TOTAL");
			for (Results res : resList) {
				total.chars += res.chars;
				total.words += res.words;
				total.time += res.time;
				total.sentenceList.addAll(res.sentenceList);
			}
			resList.add(total);
		}
		return resList;
	}

	public Results scrape(URL url) throws IOException {
		long start = System.currentTimeMillis();
		Results results = new Results(url.getHost());
		BufferedReader rdr = new BufferedReader(new InputStreamReader(
				url.openStream()));
		HTMLEditorKit.ParserCallback callback = new ScannerParserCallback(
				results, properties);
		parser.parse(rdr, callback, true);
		long finish = System.currentTimeMillis();
		results.time = finish - start;

		return results;
	}

	private static class ScannerParserCallback extends
			HTMLEditorKit.ParserCallback {
		Results results;
		Properties properties;

		public ScannerParserCallback(Results results, Properties properties) {
			this.results = results;
			this.properties = properties;
		}

		public void handleText(char[] data, int pos) {

			results.chars += data.length;

			if (properties.isWordFlag()) {
				String s = String.valueOf(data);
				String[] arr = s.split(" ");
				for (String word : arr) {
					if (properties.wordList.contains(word)) {
						results.words++;
						if (properties.isSentenceFlag()) {
							results.sentenceList.add(s);
						}
					}
				}
			}

		}

	}

	// we need this class only to get the default html parser
	// the returned parser creates a new parser on every parse call
	// so one parser is enough
	private static class ScannerHTMLEditorKit extends HTMLEditorKit {
		/**
			 * 
			 */
		private static final long serialVersionUID = 1L;

		@Override
		public Parser getParser() {
			return super.getParser();
		}
	}
}
