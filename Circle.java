public class Circle{
	double x;
	double y;
	double radius;

	public Circle(double x, double y, double radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public double getDistance(double x1, double y1, double x2, double y2)
	{
		double xDistance = x2 - x1;
		double yDistance = y2 - y1;

		return Math.sqrt(Math.pow(xDistance,2) + Math.pow(yDistance,2));
	}

}