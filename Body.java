public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Body(double xP, double yP, double xV, double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b)
	{
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	public double calcDistance(Body a)
	{
		return Math.sqrt(Math.pow((a.xxPos - xxPos),2) + Math.pow((a.yyPos - yyPos),2));
	} 

	public double calcForceExertedBy(Body a)
	{
		return ((6.67 * Math.pow(10,-11) * mass * a.mass)/(Math.pow(calcDistance(a),2)));
	}

	public double calcForceExertedByX(Body a)
	{
		return ((calcForceExertedBy(a) * (a.xxPos - xxPos))/calcDistance(a));
	}

	public double calcForceExertedByY(Body a)
	{
		return ((calcForceExertedBy(a) * (a.yyPos - yyPos))/calcDistance(a));
	}

	public double calcNetForceExertedByX(Body[] a)
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

	public double calcNetForceExertedByY(Body[] a)
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