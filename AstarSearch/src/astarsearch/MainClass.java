package astarsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MainClass {
	static Cell [][] board;
	static Coordinate dest;
	static int foundFlag=0;
	static Input input;
	public static void main(String [] args) {
		// TODO Auto-generated method stub
		
	    input=new Input();
		board=new Cell[input.board_size][input.board_size];
		placeElementsInBoard(input);
		dest=new Coordinate(input.destination_coordinate.x_coordinate,input.destination_coordinate.y_coordinate);
		displayBoard();
		AstarSearch(input);
		System.out.println("--------------");
		displayBoard();
	}
 public static void placeElementsInBoard(Input input) {
	Cell source_cell=new Cell("S",input.source_coordinate);
	Cell dest_cell=new Cell("D",input.destination_coordinate);
	board[input.source_coordinate.x_coordinate][input.source_coordinate.y_coordinate]=source_cell;
	board[input.destination_coordinate.x_coordinate][input.destination_coordinate.y_coordinate]=dest_cell;
	for(int i=0;i<input.blockade_coordinate.size();i++) {
		Coordinate xy=input.blockade_coordinate.get(i);
		Cell block_cell=new Cell("B",xy);
		block_cell.h_cost=Integer.MAX_VALUE;
		block_cell.f_cost=Integer.MAX_VALUE;
		board[input.blockade_coordinate.get(i).x_coordinate][input.blockade_coordinate.get(i).y_coordinate]=block_cell;			
		}
 }
	public static double calculateHeusticValue(Coordinate start,Coordinate dest) {
		double distance=(Math.pow((start.x_coordinate-dest.x_coordinate),2)+ 
		Math.pow((start.y_coordinate-dest.y_coordinate),2));
		return Math.sqrt(distance);
	 }
 
 public static void displayBoard() {
	 for(int i=0;i<input.board_size;i++) {
		 for(int j=0;j<input.board_size;j++) {
			 if(board[i][j]==null) {
				 Cell tempCell=new Cell();
				 board[i][j]=tempCell;
			 }
			 if(board[i][j].name.equals("B")) {
				 System.out.printf(board[i][j].name+"-"+"%.2f",1.000);
				 System.out.print("    ");
			 }else {
			 System.out.printf(board[i][j].name+"-"+"%.2f",board[i][j].f_cost);
			 System.out.print("    ");
		 }
	}
		 System.out.println();
	  }
 	}
 public static void AstarSearch(Input input) {
	 Cell start_cell=board[input.source_coordinate.x_coordinate][input.source_coordinate.y_coordinate];
	 PriorityQueue<Cell> openList=new PriorityQueue<Cell>(); 
	 openList.add(start_cell);
	 ArrayList<Cell> path=new ArrayList<Cell>();
		 
	 while(openList.isEmpty()==false) {
		
		 Cell temp=openList.poll();
		 openList.clear();
		 if(!temp.name.equals("S")) {
			 path.add(temp);
		 }
		 openList.addAll(findChildren(temp));
		 if(foundFlag==1) {
			 break;
		 }
	 }
		 for(int i=0;i<path.size();i++) {
			 Cell route=path.get(i);
			 route.name="P";
			 board[route.index.x_coordinate][route.index.y_coordinate]=route;
		 }
			 
 }
 
 public static boolean isValid(int row,int col) {
	 if(row<0||row>=board.length) {
		 //System.out.println(1);
		 return false;
		 
	 }if(col<0||col>=board.length) {
		 //System.out.println(2);
		 return false;
		 
	 }if(board[row][col].name.equals("B")) {
		//System.out.println(3);
		 return false;
	 }if(board[row][col].name.equals("V")){
		 //System.out.println(4);
		 return false;
	 }if(board[row][col].name.equals("S")) {	 
		 //System.out.println(5);
		 return false;
	 }if(board[row][col].name.equals("D")) {
		 foundFlag=1;
		 return false;
	 }
	 return true;
 }
 	public static Cell getChildren(Coordinate index,Cell cell,double gCost) {
		  Cell temp=new Cell("V",index);
		  temp.h_cost=calculateHeusticValue(index,dest);
		  temp.g_cost=cell.g_cost+gCost;
		  temp.setFCost();
		  
		  return temp;
		  
 }
  public static ArrayList<Cell> findChildren(Cell cell) {
	 int counter=0;
	  //North
	  ArrayList<Cell> children=new ArrayList<Cell>();
	  if(isValid(cell.index.x_coordinate-1,cell.index.y_coordinate)) {
		  Coordinate index=new Coordinate(cell.index.x_coordinate-1,cell.index.y_coordinate);
		  board[index.x_coordinate][index.y_coordinate]=getChildren(index,cell,1);
		  children.add(board[index.x_coordinate][index.y_coordinate]);
		  counter++;
	  }
	  //South
	  if(isValid(cell.index.x_coordinate+1,cell.index.y_coordinate)) {
		  Coordinate index=new Coordinate(cell.index.x_coordinate+1,cell.index.y_coordinate);
		  board[index.x_coordinate][index.y_coordinate]=getChildren(index,cell,1);
		  children.add(board[index.x_coordinate][index.y_coordinate]);
		  counter++;
	  }
	  //East
	  if(isValid(cell.index.x_coordinate,cell.index.y_coordinate+1)) {
		  Coordinate index=new Coordinate(cell.index.x_coordinate,cell.index.y_coordinate+1);
		  board[index.x_coordinate][index.y_coordinate]=getChildren(index,cell,1);
		  children.add(board[index.x_coordinate][index.y_coordinate]);
		  counter++;
	  }
	  //West
	  if(isValid(cell.index.x_coordinate,cell.index.y_coordinate-1)) {
		  Coordinate index=new Coordinate(cell.index.x_coordinate,cell.index.y_coordinate-1);
		  board[index.x_coordinate][index.y_coordinate]=getChildren(index,cell,1);
		  children.add(board[index.x_coordinate][index.y_coordinate]);
		  counter++;
	  }
	  //North-East
	  if(isValid(cell.index.x_coordinate-1,cell.index.y_coordinate+1)){
		  Coordinate index=new Coordinate(cell.index.x_coordinate-1,cell.index.y_coordinate+1);
		  board[index.x_coordinate][index.y_coordinate]=getChildren(index,cell,1.4);
		  children.add(board[index.x_coordinate][index.y_coordinate]);
		  counter++;
	  }
	  //North-West
	  if(isValid(cell.index.x_coordinate-1,cell.index.y_coordinate-1)){
		  Coordinate index=new Coordinate(cell.index.x_coordinate-1,cell.index.y_coordinate-1);
		  board[index.x_coordinate][index.y_coordinate]=getChildren(index,cell,1.4);
		  children.add(board[index.x_coordinate][index.y_coordinate]);  
		  counter++;
	  }
	  //South-East
	  if(isValid(cell.index.x_coordinate+1,cell.index.y_coordinate+1)){
		  Coordinate index=new Coordinate(cell.index.x_coordinate+1,cell.index.y_coordinate+1);
		  board[index.x_coordinate][index.y_coordinate]=getChildren(index,cell,1.4);
		  children.add(board[index.x_coordinate][index.y_coordinate]);
		  counter++;
	  }
	  //South-West
	  if(isValid(cell.index.x_coordinate+1,cell.index.y_coordinate-1)){
		  Coordinate index=new Coordinate(cell.index.x_coordinate+1,cell.index.y_coordinate-1);
		  board[index.x_coordinate][index.y_coordinate]=getChildren(index,cell,1.4);
		  children.add(board[index.x_coordinate][index.y_coordinate]);
		  counter++;  
	  }
	  
	  return children;
		  
 }
	  
}
