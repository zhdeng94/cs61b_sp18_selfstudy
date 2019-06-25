public class NBody {

    // This method read the input files and return the radius of the universe
    public static double readRadius(String fileDir) {
        In in = new In(fileDir);
        int numOfPlanet = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    // This method read the input file and return an array of planet
    public static Planet[] readPlanets(String fileDir) {
        In in = new In(fileDir);
        int numOfPlanet = in.readInt();
        double radius = in.readDouble();
        Planet[] pArray = new Planet[numOfPlanet];
        for (int i = 0; i < numOfPlanet; i += 1) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String pName = in.readString();
            pArray[i] = new Planet(xP, yP, xV, yV, m, pName);
        }
        return pArray;
    }

    // This is the main method
    public static void main(String[] args) {

        // collect all needed input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] pArray = readPlanets(filename);


        String background = "images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        int numOfPlanet = pArray.length;
        double time = 0;
        while (time <= T) {
            // create net force array
            double[] xForces = new double[numOfPlanet];
            double[] yForces = new double[numOfPlanet];

            // calculate the net force for each planet and update their positions
            for (int i = 0; i < numOfPlanet; i += 1) {
                xForces[i] = pArray[i].calcNetForceExertedByX(pArray);
                yForces[i] = pArray[i].calcNetForceExertedByY(pArray);
            }

            // update each planet's position
            for (int i = 0; i < numOfPlanet; i += 1) {
                pArray[i].update(dt, xForces[i], yForces[i]);
            }

            // drawing the background
            StdDraw.clear();
            StdDraw.picture(0, 0, background);

            // draw each of the planet in the pArray;
            for (Planet p : pArray) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }

        // print the final state of the universe
        StdOut.printf("%d\n", numOfPlanet);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < numOfPlanet; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          pArray[i].xxPos, pArray[i].yyPos, pArray[i].xxVel,
                          pArray[i].yyVel, pArray[i].mass, pArray[i].imgFileName);   
        }
    }
}
