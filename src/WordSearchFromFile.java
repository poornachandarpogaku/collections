import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class WordSearchFromFile
{

	static Map<String, LinkedList<String>> searchMap = new HashMap<>();

	public static void main(String[] args) throws IOException
	{
       File f = new File("C:\\JavaWorkSpace\\poorna.txt");
       
       if(f.exists())
       {
		FileReader fr = new FileReader("C:\\JavaWorkSpace\\poorna.txt");
	    BufferedReader br = new BufferedReader(fr);
	     
	    Scanner myReader = new Scanner(br);
	    
	    String input="";
	    
	    while(myReader.hasNextLine())
	    {
	     input = input +" "+ myReader.nextLine();
	    }
		String[] words = input.split(" "); // today
		
		for (int a = 0; a < words.length; a++) 
		{
			addWord(words[a]);
		}
		myReader.close();
		
		printList();
       }
       else
       {
    	   System.out.println("file not Exit");
       }
	}

	private static void addWord(String word) 
	{
		if(word.length()>1)
		{
		  String key = word.substring(0, 2);
	      if (!searchMap.containsKey(key))
		  {
			searchMap.put(key, new LinkedList<String>());
		  }
		  searchMap.get(key).add(word);
		}
		else if(word.length()==1)
		{
			String key = word.substring(0);
			 if (!searchMap.containsKey(key))
			 {
				 searchMap.put(key, new LinkedList<String>());
			  searchMap.get(key).add(word);
			 }
		}
		
	}

	private static void printList() 
	{
		for (String iKey : searchMap.keySet())
		{
			System.out.println(iKey +": "+searchMap.get(iKey).toString());
		}
	
		
	}
}
