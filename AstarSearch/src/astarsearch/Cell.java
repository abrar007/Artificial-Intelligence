package astarsearch;

public class Cell implements Comparable<Cell>{
	double f_cost=0;
	double g_cost=0;
	double h_cost=0;
	Coordinate index;
	String name="E";
	
	
	public Cell(String name,Coordinate coordinate){
		index=coordinate;
		this.g_cost=g_cost;
		this.h_cost=h_cost;
		this.name=name;
		
	}
	public void setFCost() {
		f_cost=g_cost+h_cost;
	}
	public Cell() {
		
	}
	@Override
	public int compareTo(Cell cell) {
		// TODO Auto-generated method stub
		if(this.f_cost>cell.f_cost) {
			return 1;
		} else if (this.f_cost < cell.f_cost) {
            return -1;
        } else {
            return 0;
        }

	}
	

}

class Coordinate{
	int x_coordinate=0;
	int y_coordinate=0;
	
	public Coordinate(int x_coordinate,int y_coordinate) {
		this.x_coordinate=x_coordinate;
		this.y_coordinate=y_coordinate;
	}
	
}
