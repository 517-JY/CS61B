public class Planet {
	/** Its current x position */
	public double xxPos;

	/** Its current y position */
	public double yyPos;

	/** Its current velocity in the x direction */
	public double xxVel;

	/** Its current velocity in the y direction */
	public double yyVel;

	/** Its mass*/
	public double mass;

	/** The name of the file that corresponds to the image that decpicts the 
	    planet (for example, jupiter.gif)*/
	public String imgFileName;

	private static final double G = 6.67e-11;

	
    /** First Constructor */
	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

  /** Second Constructor */
	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/** 
	* Returns a double equal to the distance between the supplied planet and
	  the planet that is doing the calculation.
	  e.g. samh.calcDistance(rocinante)
	* @param p Given planet
	*/
	public double calcDistance(Planet p) {
		return Math.sqrt(Math.pow((p.xxPos - this.xxPos), 2) + Math.pow((p.yyPos - this.yyPos), 2));
	}

	/** 
	* Returns a double describing the force exerted on this planet by the given planet.
	* @param p Given planet
	*/
	public double calcForceExertedBy(Planet p) {
		return G * this.mass * p.mass / Math.pow(calcDistance(p), 2);
	}

	/**
	* Returns a double describing the force exerted in the X directions
	* @param p Given planet
	*/
	public double calcForceExertedByX(Planet p) {
		return calcForceExertedBy(p) * (p.xxPos - this.xxPos) / calcDistance(p);
	}

	/**
	* Returns a double describing the force exerted in the Y directions
	* @param p Given planet
	*/
	public double calcForceExertedByY(Planet p) {
		return calcForceExertedBy(p) * (p.yyPos - this.yyPos) / calcDistance(p);	
	}

	/**
	* Returns a double calculating the net X force exerted by all planets in the given array
	  upon the current Planet 
 	* @param ps A given planet array
	*/
	public double calcNetForceExertedByX(Planet[] ps) {
		int i = 0;
		double result = 0.0;

		while (i < ps.length) {
			if (this.equals(ps[i])) {
				i += 1;
				continue;
			}
			result += calcForceExertedByX(ps[i]);
			i += 1;			
		}

		return result;
	}

	/**
	* Returns a double calculating the net Y force exerted by all planets in the given array
	  upon the current Planet 
 	* @param ps A given planet array
	*/

	public double calcNetForceExertedByY(Planet[] ps) {
		int i = 0;
		double result = 0.0;

		while (i < ps.length) {
			if (this.equals(ps[i])) {
				i += 1;
				continue;
			}
			result += calcForceExertedByY(ps[i]);
			i += 1;			
		}

		return result;
	}

	/**
	 * Uses steps below to update the planet's position and velocity
	 * 1. Calculates the accerleration using the provided x- and y- forces.
	 * 2. Calculates the new velocity by using the acceleration and current velocity.
	      Accerleration describes the change in velocity per unit time,
	      new velocity is (xxVel + dt * ax, yyVel + dt * ay)
	 * 3. Calculates the new position by using the velocity computed in step 2 and the
	      current position.
	      new position is (xxPos + dt * xxVel, yyPos + dt * yyVel)
	 */
	public void update(double dt, double fX, double fY) {

		double xxAcc = fX / mass;
		double yyAcc = fY / mass;

		xxVel = xxVel + dt * xxAcc;
		yyVel = yyVel + dt * yyAcc;

		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}

	/**
	 * Draw the planet at its appropriate position
	 */
	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}

}