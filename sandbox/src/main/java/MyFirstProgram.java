public class MyFirstProgram{

	public static void main(String [] args){
		Point a = new Point(4, 2);
		Point b = new Point(5, 8);

		System.out.println("Distance between a point (" + a.x+ ", " + a.y + ")" +
				" and point b (" + b.x+ ", " + b.y + ") " + "equals "+ Point.distance(a, b));
	}

}