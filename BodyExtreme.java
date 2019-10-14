

public class BodyExtreme {


	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public BodyExtreme(double xP, double yP, double xV, double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public BodyExtreme(BodyExtreme b)
	{
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	public BodyExtreme()
	{
		xxPos = 0;
		yyPos = 0;
		xxVel = 0;
		yyVel = 0;
		mass = 0;
		imgFileName = null;
	}

	public double calcDistance(BodyExtreme a)
	{
		return Math.sqrt(Math.pow((a.xxPos - xxPos),2) + Math.pow((a.yyPos - yyPos),2));
	} 

	public double calcForceExertedBy(BodyExtreme a)
	{
		return ((6.67 * Math.pow(10,-11) * mass * a.mass)/(Math.pow(calcDistance(a),2)));
	}

	public double calcForceExertedByX(BodyExtreme a)
	{
		return ((calcForceExertedBy(a) * (a.xxPos - xxPos))/calcDistance(a));
	}

	public double calcForceExertedByY(BodyExtreme a)
	{
		return ((calcForceExertedBy(a) * (a.yyPos - yyPos))/calcDistance(a));
	}

	public double calcNetForceExertedByX(BodyExtreme[] a)
	{
		double total = 0;
		int index = 0;
		while (index < a.length) {
			if (this.equals(a[index])) {
				index++;
			}
			else {
				total = total + calcForceExertedByX(a[index]);
				index++;
			}
			
		}
		return total;
	}

	public double calcNetForceExertedByY(BodyExtreme[] a)
	{
		double total = 0;
		int index = 0;
		while (index < a.length) {
			if (this.equals(a[index])) {
				index++;
			}
			else {
				total = total + calcForceExertedByY(a[index]);
				index++;
			}
			
		}
		return total;
	}

	public void update(double dt, double fX, double fY) {

		double accelx = fX/mass;
		double accely = fY/mass;
		xxVel = xxVel + dt * accelx;
		yyVel = yyVel + dt * accely;
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}
	public void draw()
	{
		StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
	}

}