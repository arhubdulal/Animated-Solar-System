public class NBody{

	public static double readRadius(String a)
	{
		In in = new In(a);
		int first = in.readInt();
		double second = in.readDouble();
		return second;
	}

	public static Body[] readBodies(String a)
	{
		In in = new In(a);
		int first = in.readInt();
		double second = in.readDouble();
		int count = 0;
		Body[] planets = new Body[first];
		while (count < first)
		{
			planets[count] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
			count++;
		}

		return planets; 
	}

	public static void main(String[] args)
	{
		double t = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Body[] planets = readBodies(filename);
		double radius = readRadius(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (Body a: planets)
		{
			a.draw();
		}

		StdDraw.enableDoubleBuffering();
		double time = 0;
		while (time < t)
		{
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			int index = 0;
			for (Body a : planets)
			{
				xForces[index] = a.calcNetForceExertedByX(planets);
				yForces[index] = a.calcNetForceExertedByY(planets);
				index++;
			}
			int i = 0;
			while (i < planets.length)
			{
				planets[i].update(dt, xForces[i], yForces[i]);
				i++;
			}
			
			StdDraw.picture(0, 0, "images/starfield.jpg");

			int index1 = 0;
			while (index1 < planets.length)
			{
				planets[index1].draw();
				index1++;
			}
			
			StdDraw.show();
			StdDraw.pause(10);
			time = time + dt;

		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++)
		{
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
    	}


	}
}