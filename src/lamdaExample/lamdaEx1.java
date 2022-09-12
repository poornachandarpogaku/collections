package lamdaExample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class lamdaEx1 {

	public static void main(String[] args) {
      
		
		List<Integer> list = new ArrayList<>();
		
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		
		list.forEach(i -> System.out.println(i));  //lamda Expression
		
		
		LinkedList<String> list2 = new LinkedList<>();
		
		
		
		list2.add("hyderabad");
		list2.add("warangal");
		list2.add("6373671");
		
		
		list2.forEach(i -> System.out.println(i)); //lamda Expression
		
	
	
	}

}
