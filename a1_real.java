mport java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
 
public class a1_real {
	
	public static int silence(int[] positions) {
		Hashtable <Integer,Integer> ht = new Hashtable<>();
		int Min = positions.length;
		for(int i = 0;i< positions.length;i++) {
			if(ht.containsKey(positions[i])) {
				int diff = i - ht.get(positions[i]);
				if(diff < Min) {
					Min = diff;
				}
			}
			ht.put(positions[i], i);
		}
		return Min;
 
	}
 
	public static void main(String[] args) {
		try {
			String path = args[0];
      		File myFile = new File(path);
      		Scanner sc = new Scanner(myFile);
      		int num_students = sc.nextInt();
      		int[] positions = new int[num_students];
      		for (int student = 0; student<num_students; student++){
				positions[student] =sc.nextInt();
      		}
      		sc.close();
      		int answer = silence(positions);
      		System.out.println(answer);
    	} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	}
  	}		
}
 
