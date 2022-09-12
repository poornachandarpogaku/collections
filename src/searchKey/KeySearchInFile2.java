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

public class KeySearchInFile2 {
	static Map<String, LinkedList<String>> searchMap = new HashMap<>();

	public static void main(String[] args) throws Exception {
		File fileChecking = new File("C:\\JavaWorkSpace\\data.txt");

		if (fileChecking.exists()) {
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

			// user input stored in checkword varialbe
			String checkWord = scan1.next().toLowerCase();// ex : java&jvm
			scan1.close();
			String[] words1 = new String[1];
			StringBuilder sb = new StringBuilder();

			if (checkWord.contains("&")) {
				words1 = checkWord.split("&");
				for (int w = 0; w < words1.length; w++) {
					Pattern pattern = Pattern.compile(words1[w]); // that
					String checkKey = words1[w].substring(0, 2);
					if (searchMap.containsKey(checkKey))
						;
					{
						LinkedList<String> sc = searchMap.getOrDefault(checkKey, null);
						boolean b = false;
						if (sc != null) {
							if (searchMap.containsKey(checkKey)) {
								for (int i = 0; i < sc.size(); i++) {
									Matcher matcher = pattern.matcher(sc.get(i));
									b = matcher.matches();
									if (b == true) {
										sb.append(sc.get(i));
										sb.append("&");
										break;
									}
								}
							}
						}
					}
				}
			} else if (checkWord.contains("|")) {
				words1 = checkWord.split("[|]");
				for (int w = 0; w < words1.length; w++) {
					Pattern pattern = Pattern.compile(words1[w]); // that
					String checkKey = words1[w].substring(0, 2);
					if (searchMap.containsKey(checkKey))
						;
					{
						LinkedList<String> sc = searchMap.getOrDefault(checkKey, null);
						boolean b = false;
						if (sc != null) {
							if (searchMap.containsKey(checkKey)) {

								for (int i = 0; i < 1; i++) {
									Matcher matcher = pattern.matcher(sc.get(i));
									b = matcher.matches();
									if (b == true) {
										sb.append(sc.get(i));
										sb.append("|");
										break;
									}
								}

							}
						}
					}
				}

			} else {
				Pattern pattern = Pattern.compile(checkWord); // that
				String checkKey = checkWord.substring(0, 2);
				if (searchMap.containsKey(checkKey))
					; // true
				{
					LinkedList<String> sc = searchMap.getOrDefault(checkKey, null);
					boolean b = false;
					if (sc != null) {
						if (searchMap.containsKey(checkKey)) {
							for (int i = 0; i < sc.size(); i++) {
								Matcher matcher = pattern.matcher(sc.get(i));
								b = matcher.matches();
								if (b == true) {
									sb.append(sc.get(i));
									break;
								}
							}
						}
					}
				}
			}

			if (sb.length() > 1) {
				if (checkWord.contains("&")) {
					sb.deleteCharAt(sb.length() - 1);
					Pattern pattern1 = Pattern.compile("^(.+)&(.+)$");
					Matcher matcher = pattern1.matcher(sb.toString());
					Boolean b1 = matcher.matches();
					System.out.println(b1);
				}

				else if (checkWord.contains("|")) {
					// sb.deleteCharAt(sb.length() - 1);
					Pattern pattern1 = Pattern.compile("^(.*)([|])(.*)$");
					Matcher matcher = pattern1.matcher(sb.toString());
					Boolean b1 = matcher.matches();
					System.out.println(b1);
				} else {

					Pattern pattern1 = Pattern.compile(checkWord);
					Matcher matcher = pattern1.matcher(sb.toString());
					Boolean b1 = matcher.matches();
					System.out.println(b1);
				}
			} else {
				Pattern pattern1 = Pattern.compile(checkWord);
				Matcher matcher = pattern1.matcher(sb.toString());
				Boolean b1 = matcher.matches();
				System.out.println(b1);
			}
		}

		else
			System.out.println("file doesn't Exit");
	}

	// java&apple
	private static boolean processExpression(String inputStr) throws Exception {

		String expAnd = "[a-z&A-Z]";
		Pattern andPattern = Pattern.compile(expAnd);
		Matcher andMatcher = andPattern.matcher(inputStr);

		String expOr = "[a-z|A-Z]";
		Pattern orPattern = Pattern.compile(expOr);
		Matcher orMatcher = orPattern.matcher(inputStr);

		String[] words = null;
		if (andMatcher.find()) {
			words = inputStr.split("&");
			wordExists(words, "&");
		}
		if (orMatcher.find()) {
			words = inputStr.split("|");
			wordExists(words, "|");

		}
		return false;

	}

	private static boolean wordExists(String[] words, String operator) throws Exception {
		if (words == null | words.length <= 0) {
			throw new Exception("Invalid words");
		}

		boolean found = true;
		for (int i = 0; i < words.length; i++) {
			String checkKey = words[i].substring(0, 2);
			if (searchMap.containsKey(checkKey)) {
				if (operator.equals("&")) {
					found = found & searchMap.get(checkKey).contains(words[i]);
				}
				if (operator.equals("|")) {
					found = found | searchMap.get(checkKey).contains(words[i]);
				}
			}
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
