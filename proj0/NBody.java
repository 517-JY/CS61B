public class NBody {
	/**
	 * Reads the given file and return a double corresponding to the radius of the universe
	   in that file.
	 */
	public static double readRadius(String filepath) {
		In in = new In(filepath);

		int number = in.readInt();
		double radius = in.readDouble();

		return radius;
	}

	/**
	 * Reads the given file and return an array of planets corresponding to the planets
	   in that file.
	 */
	public static Planet[] readPlanets(String filepath) {
		Planet[] planets = new Planet[5];
		int i = 0;

		In in = new In(filepath);

		int number = in.readInt();
		double radius = in.readDouble();

		while (i < planets.length) {
			double xxPOS = in.readDouble();
			double yyPOS = in.readDouble();
			double xxVEL = in.readDouble();
			double yyVEL = in.readDouble();
			double curmass = in.readDouble();
			String planetname = in.readString();

			Planet p = new Planet(xxPOS, yyPOS, xxVEL, yyVEL, curmass, planetname);
			planets[i] = p; 
			i += 1;
		}

		return planets;
	}

	/**
	 * Creates the main method.
	 */

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		/* Read in the planets and the universe radius from the file described  by filename. */
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		/* set the scale so that it matches the radius of the universe */
		StdDraw.setXscale(-radius, radius);
		StdDraw.setYscale(-radius, radius);

		// speed up performance, defer displaying
		StdDraw.enableDoubleBuffering();

		// main animation loop
		/* creates simulation using discretizing time */
		for (double ti = 0.0; ti <= T; ti = ti + dt) {
			/* create an xForces array and yForces array */
			double[] xForces = new double[5];
			double[] yForces = new double[5];

			/* calculates the nex x and y forces for each planet, storing these in xForces
			   and yForces arrays respectively. */
			for (int i = 0; i < 5; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			/* updates each planet's position */
			for (int i = 0; i < 5; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			/* set the background with "starfield.jpg" */
			StdDraw.clear();
			StdDraw.picture(0.0, 0.0, "images/starfield.jpg");

			/* draw each one of the planets in the planets array. */
			for (int i = 0; i < planets.length; i++) {
				planets[i].draw();
			}

			/* Shows the drawing to the screen */
			StdDraw.show();
			StdDraw.pause(1);
		}

		/* prints out the final state of the universe in the same format as the input. */
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
					planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}
}