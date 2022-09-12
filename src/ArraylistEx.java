import java.util.ArrayList;
import java.util.List;

public class ArraylistEx {

	public static void main(String[] args) {
    ArrayList<String> words = new ArrayList<String>();
       
        words.add("today");
        words.add("is");
       words.add("monday");
       
    	List<String> output= words.subList(0, 1); 
    	 System.out.println(output);
     
       System.out.println(words);
	}

}
