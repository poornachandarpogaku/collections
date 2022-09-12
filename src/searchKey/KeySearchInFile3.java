package searchKey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeySearchInFile3 {
	static Map<String, LinkedList<String>> searchMap = new HashMap<>();

	public static void main(String[] args) throws Exception {
		File fileChecking = new File("C:\\JavaWorkSpace\\data.txt");

		if (fileChecking.exists())
		{
			// to read the data from file
			FileReader myReader = new FileReader("C:\\JavaWorkSpace\\data.txt");
			BufferedReader br = new BufferedReader(myReader);
			Scanner scan = new Scanner(br);
			String input = "";
			while (scan.hasNextLine()) {
				input = input + " " + scan.nextLine();
			}
			String[] words = input.split(" "); // today
			for (int a = 0; a < words.length; a++) {
				addWord(words[a].toLowerCase());
			}
			myReader.close();
			scan.close();
			printList();

			// user input
			Scanner scan1 = new Scanner(System.in);
			System.out.println("enter a word:");
			processExpression(scan1.nextLine());
	
			

			scan1.close();

		}

		else
			System.out.println("file doesn't Exit");
	}

	// java&apple
	private static boolean processExpression(String inputStr) throws Exception {

		String expAnd = "[a-z&a-z]+";
		Pattern andPattern = Pattern.compile(expAnd);
		Matcher andMatcher = andPattern.matcher(inputStr);

		String expOr = "[a-z|a-z]+";
		Pattern orPattern = Pattern.compile(expOr);
		Matcher orMatcher = orPattern.matcher(inputStr);

		String[] words;
		if (andMatcher.find()) {
			words = inputStr.split("&");
			System.out.println(wordExists(words, "&"));
		}
		else if (orMatcher.find()) {
			words = inputStr.split("[|]");
			System.out.println( wordExists(words, "[|]"));

		}
		return false;

	}

	private static boolean wordExists(String[] words, String operator) throws Exception {
		
		if (words == null || words.length <= 0) {
			throw new Exception("Invalid words");
		}

		boolean found = true;
		for (int i = 0; i < words.length; i++) 
		{
			String checkKey = words[i].substring(0,2);
			if (searchMap.containsKey(checkKey)) 
			{
				if (operator.equals("&"))
				{
					found = found & searchMap.get(checkKey).contains(words[i]);
					 //System.out.println("AND " + found);
				}
				if (operator.equals("|")) 
				{
					found = searchMap.get(checkKey).contains(words[i]);
					if (found) {
					//System.out.println("OR " + found);
					 return found;
					}

				}
			}
			return false;
		}

		return found;
	}

	private static void addWord(String word) {
		if (word.length() > 1) {
			String key = word.substring(0, 2).toLowerCase();
			if (!searchMap.containsKey(key))
				searchMap.put(key, new LinkedList<String>());
			searchMap.get(key).add(word);
		} else if (word.length() == 1) {
			String key = word.substring(0).toLowerCase();
			if (searchMap.containsKey(key)) {
				searchMap.put(key, new LinkedList<String>());
				searchMap.get(key).add(word);
			}
		}
	}

	private static void printList() {
		for (String iKey : searchMap.keySet()) {
			System.out.println(iKey + ": " + searchMap.get(iKey).toString());
		}
	}
}
