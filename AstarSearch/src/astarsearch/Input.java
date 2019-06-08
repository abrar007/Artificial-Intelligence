package astarsearch;

import java.util.ArrayList;
import java.util.Scanner;

public class Input {
	Scanner scan=new Scanner(System.in);
	Coordinate source_coordinate;
	Coordinate destination_coordinate;
	int board_size;
	int number_of_obstacle=0;
	ArrayList<Coordinate> blockade_coordinate=new ArrayList<Coordinate>();
	public Input() {
		System.out.println("Enter Board Size");
		board_size=scan.nextInt();
		
		System.out.println("Enter Number of Obstacle in the Board");
		number_of_obstacle=scan.nextInt();
		System.out.println("Enter Obstacle Coordinate");
		for(int i=0;i<number_of_obstacle;i++) {
			Coordinate blockade=new Coordinate(scan.nextInt(),scan.nextInt());
			blockade_coordinate.add(blockade);
		}
		System.out.println("Enter Source Coordinate");
		source_coordinate=new Coordinate(scan.nextInt(),scan.nextInt());
		System.out.println("Enter Destination Coordinate");
		destination_coordinate=new Coordinate(scan.nextInt(),scan.nextInt());
	}
	

}
