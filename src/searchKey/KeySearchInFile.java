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

//Java is an object-oriented programming language that produces software for multiple platforms 

public class KeySearchInFile
{
	static Map<String,LinkedList<String>> searchMap = new HashMap<>();
	
   public static void main(String[] args) throws Exception
   {
	   File fileChecking = new File("C:\\JavaWorkSpace\\data.txt");
	   
	if(fileChecking.exists())
	{
	    FileReader myReader = new FileReader("C:\\JavaWorkSpace\\data.txt");  
	    BufferedReader br = new BufferedReader(myReader);
	    Scanner scan = new Scanner(br);
	    String input="";
	    while(scan.hasNextLine())
	    {
	     input = input +" "+ scan.nextLine();
	    }
		String[] words = input.split(" "); // today
		for (int a = 0; a < words.length; a++) 
		{
			addWord(words[a].toLowerCase());
		}
		myReader.close();
		scan.close();
	    printList();
	    
	    
	    
	    
		Scanner scan1 = new Scanner(System.in);
		System.out.println("enter a word:");
		String checkWord = scan1.next(); //that
		scan1.close();
		
		Pattern pattern = Pattern.compile(checkWord); //that
		String checkKey = checkWord.substring(0,2);//th
		 
		
		if(searchMap.containsKey(checkKey));  //true
		{
			LinkedList<String> sc = searchMap.getOrDefault(checkKey,null);
			boolean b =false;
			if(sc!=null)
			{
	     	if(searchMap.containsKey(checkKey))
	     	{
	     		for(int i=0;i<sc.size();i++)
	     		{
	            Matcher matcher = pattern.matcher(sc.get(i));
      	         b = matcher.matches();
	            if(b==true)
	            System.out.println(b);
	     		
	     		}
	     	}
	     	else 
	     	 System.out.println(b);
		    }
			else
			{
				System.out.println(b);
			}
		}
	}
	else 
     System.out.println("file doesn't Exit");

	
	
	
  }
   private static void addWord(String word) 
	{
		if(word.length()>1)
		{
		  String key = word.substring(0, 2).toLowerCase();
	      if (!searchMap.containsKey(key))
			searchMap.put(key, new LinkedList<String>());
		  searchMap.get(key).add(word);
		}
		else if(word.length()==1)
		{
			String key = word.substring(0).toLowerCase();
			 if (searchMap.containsKey(key))
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
