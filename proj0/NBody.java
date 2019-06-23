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
}
