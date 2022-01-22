import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
 
public class Honors {
 
    public static int min_moves(int[][] board) {
       class grid {
            private int row;
            private int col;
            private grid(int row, int col)
            {
                this.row = row;
                this.col = col;
            }
        }
        //create a grid class to store our row and col
 
        class gridinque{
            private grid cell;
            private int d;
            //private  int color;
            private   gridinque(grid cell,int d){
                this.cell = cell;
                this.d = d;
                //this.color = color;
            }
        }
        // create a class store the grid and the distance of it from the first grid
        class color{
           int white = 0;
           int black = 1;
            private color(int white ,int black){
                this.white = 0;
                this.black = 1;
            }
        }
 
        color gridcolor = new color(0,1);
        int num_rows = board.length;
        int num_columns = board[0].length;
        int[][] distance = new int[num_rows][num_columns ];
        distance[0][0] = 0;
        grid start = new grid(0, 0);// store the first grid
        grid end = new grid(num_rows - 1, num_columns - 1 );//store the last grid
        Queue<gridinque> bfs = new LinkedList<>();
        gridinque source = new gridinque(start,0);//store the first grid and its distance
        bfs.add(source);//add the first in queue
        int color[][] = new int[num_rows][num_columns];
        color[0][0] = gridcolor.black;
        while(!bfs.isEmpty()){
            gridinque top = bfs.peek();
            grid current = top.cell;
            if (current.row == end.row && current.col == end.col ){
                return top.d;
            }
            bfs.remove();
            int var = board[current.row][current.col];
            int[] rowchange = {-var, 0, 0,var };
            int[] colchange = {0, -var, var, 0};
            int i = 0;
            //boolean issafe = row >= 0 && col >= 0 && row < board.length && col < board[0].length && color[row][col]!= black;
            while( i < 4){
                int row = current.row + rowchange[i];
                int col = current.col + colchange[i];
                i++;
                boolean isSafe = row >= 0 && col >= 0 && row < board.length && col < board[0].length && color[row][col]!= gridcolor.black;
                //gridinque near = new gridinque(new grid(row,col), top.d + 1,white);
                if(isSafe){
                    gridinque near = new gridinque(new grid(row,col), top.d + 1);
                    bfs.add(near);
                    color[row][col] = gridcolor.black;
                }
 
            }
        }
        return -1; // placeholder
    }
 
    public void test(String filename) throws FileNotFoundException{
        Scanner sc = new Scanner(new File(filename));
        int num_rows = sc.nextInt();
        int num_columns = sc.nextInt();
        int [][]board = new int[num_rows][num_columns];
        for (int i=0; i<num_rows; i++) {
            char line[] = sc.next().toCharArray();
            for (int j=0; j<num_columns; j++) {
                board[i][j] = (int)(line[j]-'0');
            }
 
        }
        sc.close();
        int answer = min_moves(board);
        System.out.println(answer);
    }
 
    public static void main(String[] args) throws FileNotFoundException{
        Honors honors = new Honors();
        honors.test(args[0]); // run 'javac Honors.java' to compile, then run 'java Honors testfilename'
 
        // or you can test like this
        // int [][]board = {1,2,3}; // not actual example
        // int answer = min_moves(board);
        // System.out.println(answer);
    }
 
}
 
