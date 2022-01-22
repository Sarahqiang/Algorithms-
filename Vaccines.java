import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
 
public class Vaccines {
 
    public class Country{
        private int ID;
        private int vaccine_threshold;
        private int vaccine_to_receive;
        private ArrayList<Integer> allies_ID;
        private ArrayList<Integer> allies_vaccine;
        public Country() {
            this.allies_ID = new ArrayList<Integer>();
            this.allies_vaccine = new ArrayList<Integer>();
            this.vaccine_threshold = 0;
            this.vaccine_to_receive = 0;
        }
        public int get_ID() {
            return this.ID;
        }
        public int get_vaccine_threshold() {
            return this.vaccine_threshold;
        }
        public ArrayList<Integer> get_all_allies_ID() {
            return allies_ID;
        }
        public ArrayList<Integer> get_all_allies_vaccine() {
            return allies_vaccine;
        }
        public int get_allies_ID(int index) {
            return allies_ID.get(index);
        }
        public int get_allies_vaccine(int index) {
            return allies_vaccine.get(index);
        }
        public int get_num_allies() {
            return allies_ID.size();
        }
        public int get_vaccines_to_receive() {
            return vaccine_to_receive;
        }
        public void set_allies_ID(int value) {
            allies_ID.add(value);
        }
        public void set_allies_vaccine(int value) {
            allies_vaccine.add(value);
        }
        public void set_ID(int value) {
            this.ID = value;
        }
        public void set_vaccine_threshold(int value) {
            this.vaccine_threshold = value;
        }
        public void set_vaccines_to_receive(int value) {
            this.vaccine_to_receive = value;
        }
 
    }
 
    public int vaccines(Country[] graph){
        //Computing the exceed in vaccines per country.
        //int grey = 0;
        //int white = 1;
        //int [] color = new int[graph.length];
        //color [0] = grey;
        Queue<Country> bfs = new LinkedList<>();
        //graph[0].set_allies_vaccine(0);
        bfs.add(graph[0]);
        int[]searched = new int[graph.length];
        searched[0] = 1;
        int count = 1;
        while(!bfs.isEmpty()){
            Country top = bfs.peek();
            bfs.remove();
            for (int i  = 0; i < top.allies_ID.size();i++){
                int ally = top.allies_ID.get(i);
                graph[ally - 1].vaccine_to_receive -= top.get_allies_vaccine(i);
                if (graph[ally - 1].vaccine_to_receive < graph[ally - 1].vaccine_threshold&& searched[ally - 1] != 1){
                    bfs.add(graph[ally -1]);
                    searched[ally - 1]=1;
                    count ++;
                }
            }
        }
        int safecountry = graph.length - count;
        return  safecountry;
    }
 
    public void test(String filename) throws FileNotFoundException {
        Vaccines hern = new Vaccines();
        Scanner sc = new Scanner(new File(filename));
        int num_countries = sc.nextInt();
        Country[] graph = new Country[num_countries];
        for (int i=0; i<num_countries; i++) {
            graph[i] = hern.new Country();
        }
        for (int i=0; i<num_countries; i++) {
            if (!sc.hasNext()) {
                sc.close();
                sc = new Scanner(new File(filename + ".2"));
            }
            int amount_vaccine = sc.nextInt();
            graph[i].set_ID(i+1);
            graph[i].set_vaccine_threshold(amount_vaccine);
            int other_countries = sc.nextInt();
            for (int j =0; j<other_countries; j++) {
                int neighbor = sc.nextInt();
                int vaccine = sc.nextInt();
                graph[neighbor -1].set_allies_ID(i+1);
                graph[neighbor -1].set_allies_vaccine(vaccine);
                graph[i].set_vaccines_to_receive(graph[i].get_vaccines_to_receive() + vaccine);
            }
        }
        sc.close();
        int answer = vaccines(graph);
        System.out.println(answer);
    }
 
    public static void main(String[] args) throws FileNotFoundException{
        Vaccines vaccines = new Vaccines();
        vaccines.test(args[0]); // run 'javac Vaccines.java' to compile, then run 'java Vaccines testfilename'
    }
 
}
 
 
