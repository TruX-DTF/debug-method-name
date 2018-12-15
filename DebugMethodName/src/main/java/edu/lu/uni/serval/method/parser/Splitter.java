package edu.lu.uni.serval.method.parser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class Splitter {
	/**
	 * Default splitter url
	 */
	public static final String DEFAULT_SPLITTER_URL = "http://splitit.cs.loyola.edu/cgi/splitit.cgi";

	/**
	 * URL connection to splitter
	 */
	private HttpURLConnection conn;

	/**
	 * Splitter URL
	 */
	private String url;

	/**
	 * Default constructor that sets the splitter url to a default value.
	 */
	public Splitter() {
		url = DEFAULT_SPLITTER_URL;
	}

	/**
	 * Splits the given string using conservative split.
	 *
	 * @param s - the string to split
	 * @return the split string
	 */
	public String conservativeSplit(String s, String lang) throws IOException {
		return split(s, lang, -1);
	}

	/**
	 * Splits the given string using GenTest.
	 *
	 * @param s - the string to split (must be <= 64 characters long)
	 * @param lang - the programming language is "all", "c", "cpp", or "java"
	 * @param n - the number of splits in the range 1 - 99 and where n < 1 is conservative split.
	 * @return the split string or null if the string could not be split.
	 */
	public String split(String s, String lang, int n) throws IOException {
		// Make sure identifer is <= 64 characters
		if (s.length() > 64)
			return null;

		// Make sure language is a valid option
		if (!lang.equalsIgnoreCase("all") && !lang.equalsIgnoreCase("c") && !lang.equalsIgnoreCase("cpp")
				&& !lang.equalsIgnoreCase("java"))
			lang = "all";

		// Make sure n is <= 99
		if (n > 99)
			n = 99;

		s = s.trim().replaceAll("[ ]", "+"); // Put '+' in for spaces

		Random random = new Random();
		int rand = random.nextInt(Integer.MAX_VALUE);

		try {
			URL urlWithQuery = new URL(url + "?id=" + s + "&lang=" + lang + "&n=" + n + "&rand=" + rand);
			conn = (HttpURLConnection) urlWithQuery.openConnection();
		} catch (IOException e) {
			throw new IOException("Cannot connect to the splitter.");
		}

		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);

		// Read the output from the splitter.
		Scanner scan = null;
		try {
			scan = new Scanner(conn.getInputStream());
		} catch (IOException e) {
			throw new IOException("Cannot retreive splitter output.");
		}
		StringBuilder result = new StringBuilder();
		while (scan.hasNextLine()) {
			result.append(scan.nextLine()).append("\n");
		}
		scan.close();

		// If the result is blank, make it null.
		if (result.length() == 0) {
			return null;
		}

		return result.toString();
	}

	/**
	 * Splits the given string using GenTest.
	 *
	 * @param s - the string to split (must be <= 64 characters long)
	 * @param lang - the programming language is "all", "c", "cpp", or "java"
	 * @param n - the number of splits in the range 1 - 99 and where n < 1 is conservative split.
	 * @return the split string
	 */
	public String split(String s, String lang, String n) throws IOException {
		return split(s, lang, Integer.parseInt(n));
	}

}
