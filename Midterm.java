import java.util.*;
import java.lang.*;
import java.io.*;
 
public class Midterm {
    private static int[][] dp_table;
    private static int[] penalization;
 
 
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int chairs;
        try {
            chairs = Integer.valueOf(reader.readLine());
            penalization = new int[chairs];
            for (int i=0; i< chairs; i++) {
                penalization[i] = Integer.valueOf(reader.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int answer = lost_marks(penalization);
        System.out.println(answer);
    }
    private static int wrong = 50000000;//initialize a large number when the case is outofbound
    private static int findway(int chair, int jump,int[]penalization){
        if( chair < 0 || chair >= penalization.length ) {//return the wrong value when the case is out of bound
            return wrong;
        }
        if(chair == penalization.length - 1 ){//basecase of our recurrence
            return penalization[chair];
        }
        if(dp_table[chair][jump] != 0) {
            return dp_table[chair][jump];
        }
        /*int addition = Math.min(findway(chair - jump, jump), findway(chair + jump + 1, jump + 1));*/
        int finalresult = dp_table[chair][jump] = penalization[chair] + Math.min(findway(chair - jump, jump,penalization), findway(chair + jump + 1, jump + 1,penalization));;
        //our final recurence
        return finalresult;
 
 
    }
 
    public static int lost_marks(int[] penalization) {
        //To Do => Please start coding your solution here
        dp_table = new int[1000][1000];
        /*for(int i = 0; i < penalization.length;i++ ){
            for(int j = 0; j< penalization.length ;j++){
                dp_table[i][j] = 0;
            }
        }*/
        int result = findway(1,1,penalization);
 
 
        return result; // placeholder
    }
 
}
 
 
 
