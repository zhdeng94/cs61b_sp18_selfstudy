/** This is the definition of the planet class */

public class Planet {
    // instance variables

    public double xxPos; // planet current x position
    public double yyPos; // planet current y position
    public double xxVel; // planet current velocity along the x direction
    public double yyVel; // planet current velocity along the y direction
    public double mass; // mass of the planet
    public String imgFileName; // name of the image file that depicts the planet
                               // for example "jupiter.gif"
    public static final double G = 6.67e-11; // gravitational constant


    // the first constructor
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    // the second constructor
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    // method to calculate the distance to the input planet
    public double calcDistance(Planet p) {
        double distance, dxSquare, dySquare;
        dxSquare = (xxPos - p.xxPos) * (xxPos - p.xxPos);
        dySquare = (yyPos - p.yyPos) * (yyPos - p.yyPos);
        distance = Math.sqrt(dxSquare + dySquare);
        return distance;
    }

    // method to calculate the force exerted by the input planet
    public double calcForceExertedBy(Planet p) {
        double force, distance;
        distance = calcDistance(p);
        force = G * mass * p.mass / distance / distance;
        return force;
    }

    // method to calculate the force along the x direction
    public double calcForceExertedByX(Planet p) {
        double force, forceX, dx, distance;
        force = calcForceExertedBy(p);
        distance = calcDistance(p);
        dx = p.xxPos - xxPos;
        forceX = force * dx / distance;
        return forceX;
    }

    // method to calculate the force along the y direction
    public double calcForceExertedByY(Planet p) {
        double force, forceY, dy, distance;
        force = calcForceExertedBy(p);
        distance = calcDistance(p);
        dy = p.yyPos - yyPos;
        forceY = force * dy / distance;
        return forceY;
    }

    // method to calculate the net force along the x direction by
    // an array of planet
    public double calcNetForceExertedByX(Planet[] pArray) {
        double netForceX = 0;
        for (Planet p : pArray) {
            if (this.equals(p)) {
                continue;
            } else {
                netForceX += calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    // method to calculate the net force along the y direction by
    // an array of planet
    public double calcNetForceExertedByY(Planet[] pArray) {
        double netForceY = 0;
        for (Planet p : pArray) {
            if (this.equals(p)) {
                continue;
            } else {
                netForceY += calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    // method to update the position and velocity of the planet
    public void update(double dt, double fX, double fY) {
        double aX, aY;
        aX = fX / mass;
        aY = fY / mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    // method to draw the planet on the universe at its current position
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
