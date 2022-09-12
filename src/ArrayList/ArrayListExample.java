package ArrayList;

import java.util.ArrayList;

public class ArrayListExample
{

	public static void main(String[] args)
	{
		
		ArrayList list = new ArrayList();
		
		String str = "hyderabad";
		list.add(str);
		list.add("warangal");
		list.add(200);
		
	System.out.println(list); // we can print direct
		
		for (Object o : list)
		{
			System.out.println(o);  // we can print using loop also.
		}

	}

}
