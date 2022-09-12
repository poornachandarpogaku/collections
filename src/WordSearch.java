
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class WordSearch {

	static Map<String, LinkedList<String>> searchMap = new HashMap<>();

	public static void main(String[] args) {

		String input = "aaa bbb bbc bbm aa sss mmm ssss yyyy zzz za";
		String[] words = input.split(" "); // today
		for (int a = 0; a < words.length; a++) 
		{
			addWord(words[a]);
		}
		printList();
	}

	private static void addWord(String word) {
		// apple
		String key = word.substring(0, 2);
		if (!searchMap.containsKey(key)) {
			searchMap.put(key, new LinkedList<String>());
		}
		// apple
		searchMap.get(key).add(word);
	}

	private static void printList() {
		for (String iKey : searchMap.keySet()) {
			System.out.println(iKey +": "+searchMap.get(iKey).toString());
		}
	}

}
