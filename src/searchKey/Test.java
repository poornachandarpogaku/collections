package searchKey;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Test
{

	public static void main(String[] args) 
	{
   
		
		String str = "poorna|chandar";
		
		String[] arr = new String[2];
		
		arr = str.split("[|]");
		
		
		for (String i :  arr)
		{
		 System.out.println(i);
		}
		
		
	}

}
