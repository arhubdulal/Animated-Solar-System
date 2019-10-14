
import java.util.Random;

public class NBodyExtreme{

	public static double readRadius(String a)
	{
		In in = new In(a);
		int first = in.readInt();
		double second = in.readDouble();
		return second;
	}

	public static BodyExtreme[] readBodies(String a)
	{
		In in = new In(a);
		int first = in.readInt();
		double second = in.readDouble();
		int count = 0;
		BodyExtreme[] planets = new BodyExtreme[first];
		while (count < first)
		{
			planets[count] = new BodyExtreme(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
			count++;
		}

		return planets; 
	}

	public static void main(String[] args)
	{
		double t = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		BodyExtreme[] planets = readBodies(filename);
		double radius = readRadius(filename);
		AsteroidFieldExtreme asteroid = new AsteroidFieldExtreme(radius);
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (BodyExtreme a: planets)
		{
			a.draw();
		}

		AsteroidFieldExtreme[] asteroids = asteroid.field(20, radius);
		for (AsteroidFieldExtreme a: asteroids) {
			a.draw();
		}

		StdDraw.enableDoubleBuffering();
		double time = 0;
		while (time < t)
		{	
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			double[] xForcesasteroid = new double[asteroids.length];
			double[] yForcesasteroid = new double[asteroids.length];
			int index = 0;
			for (BodyExtreme a : planets)
			{
				xForces[index] = a.calcNetForceExertedByX(planets);
				yForces[index] = a.calcNetForceExertedByY(planets);
				index++;
			}
			index = 0;
			for (AsteroidFieldExtreme a : asteroids)
			{
				xForcesasteroid[index] = a.calcNetForceExertedByX(planets);
				yForcesasteroid[index] = a.calcNetForceExertedByY(planets);
				index++;
			}

			int i = 0;
			
			while (i < planets.length)
			{
				planets[i].update(dt, xForces[i], yForces[i]);
				i++;
			}

			i = 0;
			while (i < asteroids.length)
			{
				asteroids[i].update(dt, xForcesasteroid[i], yForcesasteroid[i]);
				i++;
			}

			for (AsteroidFieldExtreme a: asteroids)
			{
				for(BodyExtreme b: planets)
				{
					if (a.imgFileName != "Gone.png")
					{

						CircleExtreme one = new CircleExtreme(a.xxPos,a.yyPos,0.7 * Math.pow(10,10));
						CircleExtreme two = new CircleExtreme(b.xxPos,b.yyPos,0.7 * Math.pow(10,10));
						if(one.getDistance(one.x,one.y,two.x,two.y) < one.radius + two.radius)
						{
							Random r = new Random();
							a.remove();
							b.xxVel = b.xxVel + r.nextInt(300) * (r.nextBoolean() ? -1 : 1);
							b.yyVel = b.yyVel + r.nextInt(300) * (r.nextBoolean() ? -1 : 1);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.text(0, Math.pow(10,11), "KABOOM");
							StdDraw.picture(b.xxPos,b.yyPos,"images/explosion.png");
							StdDraw.show(50); 
						}
					}
				}
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");

			int index1 = 0;
			while (index1 < planets.length)
			{
				planets[index1].draw();
				index1++;
			}

			int index2 = 0;
			while (index2 < asteroids.length)
			{
				asteroids[index2].draw();
				index2++;
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