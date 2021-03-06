mport java.util.*;
import java.lang.*;
import java.io.*;
 
 
public class Game {
 
    Board sudoku;
 
    public class Cell{
        private int row = 0;
        private int column = 0;
 
        public Cell(int row, int column) {
            this.row = row;
            this.column = column;
        }
        public int getRow() {
            return row;
        }
        public int getColumn() {
            return column;
        }
    }
 
    public class Region{
        private Cell[] matrix;
        private int num_cells;
        public Region(int num_cells) {
            this.matrix = new Cell[num_cells];
            this.num_cells = num_cells;
        }
        public Cell[] getCells() {
            return matrix;
        }
        public void setCell(int pos, Cell element){
            matrix[pos] = element;
        }
 
    }
 
    public class Board{
        private int[][] board_values;
        private Region[] board_regions;
        private int num_rows;
        private int num_columns;
        private int num_regions;
 
        public Board(int num_rows,int num_columns, int num_regions){
            this.board_values = new int[num_rows][num_columns];
            this.board_regions = new Region[num_regions];
            this.num_rows = num_rows;
            this.num_columns = num_columns;
            this.num_regions = num_regions;
        }
 
        public int[][] getValues(){
            return board_values;
        }
        public int getValue(int row, int column) {
            return board_values[row][column];
        }
        public Region getRegion(int index) {
            return board_regions[index];
        }
        public Region[] getRegions(){
            return board_regions;
        }
        public void setValue(int row, int column, int value){
            board_values[row][column] = value;
        }
        public void setRegion(int index, Region initial_region) {
            board_regions[index] = initial_region;
        }
        public void setValues(int[][] values) {
            board_values = values;
        }
 
    }
    private boolean safe(int[][]board_values, int column , int row,Region[] board_regions,int n) {
        int count =0;
        if(column + 1 <= sudoku.board_values[0].length - 1) {
            if(sudoku.board_values[row][column + 1] == n ) {
                return false;
            }
        }
        if(row + 1 <= sudoku.board_values.length - 1) {
            if(sudoku.board_values[row + 1][column] == n ) {
                return false;
            }
        }
        if(column + 1 <= sudoku.board_values[0].length - 1 && row + 1 <= sudoku.board_values.length - 1) {
            if(sudoku.board_values[row + 1][column + 1] == n) {
                return false;
            }
        }
        if(column -1 >= 0 && row - 1 >= 0) {
            if (sudoku.board_values[row - 1 ][column -1] == n) {
                return false;
            }
        }
        if(row - 1 >= 0 && column + 1 <= sudoku.board_values[0].length - 1) {
            if (sudoku.board_values[row - 1][column + 1] == n) {
                return false;
            }
        }
        if(row + 1 <= sudoku.board_values.length - 1 && column -1 >= 0) {
            if (sudoku.board_values[row + 1][column - 1] == n) {
                return false;
            }
        }
        if(column -1 >= 0) {
            if (sudoku.board_values[row][column - 1] == n) {
                return false;
            }
        }
        if(row - 1 >= 0) {
 
            if (sudoku.board_values[row - 1][column] == n) {
                return false;
            }
        }/*else {
            for (int i = 0 ;i < board_regions.length;i++) {
                Cell value[] = board_regions[i].getCells();
                for(int j = 0;j<board_regions[i].num_cells;j++) {
                    if(value[j].getColumn() == column && value[j].getRow() == row){
                        if (n > value.length) {
                            return false;
                        }
                        for (int k = 0; k < value.length; k++) {
                            if (k != j) {
                                if (board_values[value[k].getRow()][value[k].getColumn()] == n) {
                                    return false;
                                }
                            }
                        }
 
                    }
 
 
                }
            }
        }*/
        return true;
    }
    private boolean regioncheck(int[][]board_values,Region[] board_regions,int row , int column, int n){
        for (int i = 0 ;i < board_regions.length;i++) {
            Cell value[] = board_regions[i].getCells();
            for(int j = 0;j<board_regions[i].num_cells;j++) {
                if(value[j].getColumn() == column && value[j].getRow() == row){
                    if (n > value.length) {
                        return false;
                    }
                    for (int k = 0; k < value.length; k++) {
                        if (k != j) {
                            if (board_values[value[k].getRow()][value[k].getColumn()] == n) {
                                return false;
                            }
                        }
                    }
 
                }
 
 
            }
        }
        return true;
 
    }
 
 
 
 
    private boolean solveSudo(int[][] board_values,Region[] board_regions) {
        int row = -1;
        int column = -1;
        boolean isempty = false;
        for(int i = 0; i < sudoku.board_values.length ; i++) {
            for (int j = 0; j < sudoku.board_values[i].length; j++) {
                if (board_values[i][j] == -1) {
                    row = i;
                    column = j;
                    isempty = true;
                    break;
                }
            }
            if (isempty) {
                break;
            }
        }
        if(!isempty) {
            return true;
        }
 
 
        for (int n = 1; n < 9; n ++) {
            if (safe(board_values,column,row,board_regions, n)) {
                if(regioncheck(board_values,board_regions, row , column,  n)) {
 
                    board_values[row][column] = n;
 
                if(solveSudo(board_values,board_regions)) {
                    return true;
                }
 
                }
                board_values[row][column] = -1;
            }
        }
        return false;
 
    }
    public int[][] solver() {
        //To Do => Please start coding your solution here
        if(solveSudo(sudoku.board_values,sudoku.board_regions) == false) {
            System.out.println("cant be solved");
 
        }
        return sudoku.getValues();
 
    }
 
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int columns = sc.nextInt();
        int[][] board = new int[rows][columns];
        //Reading the board
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                String value = sc.next();
                if (value.equals("-")) {
                    board[i][j] = -1;
                }else {
                    try {
                        board[i][j] = Integer.valueOf(value);
                    }catch(Exception e) {
                        System.out.println("Ups, something went wrong");
                    }
                }
            }
        }
        int regions = sc.nextInt();
        Game game = new Game();
        game.sudoku = game.new Board(rows, columns, regions);
        game.sudoku.setValues(board);
        for (int i=0; i< regions;i++) {
            int num_cells = sc.nextInt();
            Game.Region new_region = game.new Region(num_cells);
            for (int j=0; j< num_cells; j++) {
                String cell = sc.next();
                String value1 = cell.substring(cell.indexOf("(") + 1, cell.indexOf(","));
                String value2 = cell.substring(cell.indexOf(",") + 1, cell.indexOf(")"));
                Game.Cell new_cell = game.new Cell(Integer.valueOf(value1)-1,Integer.valueOf(value2)-1);
                new_region.setCell(j, new_cell);
            }
            game.sudoku.setRegion(i, new_region);
        }
        int[][] answer = game.solver();
        for (int i=0; i<answer.length;i++) {
            for (int j=0; j<answer[0].length; j++) {
                System.out.print(answer[i][j]);
                if (j<answer[0].length -1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
 
 
 
}
 
 
 
