package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double mean;
    private double stdDev;
    private double confidenceLow;
    private double confidenceHigh;


    /**
     * Perform T independent percolation experiments, and calculate the
     * mean, stdDev, confidenceLow, and confidenceHigh of the percolation threshold.
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Grid size N and number of experiments " +
                                               "T must be positive integers!");
        }

        double[] threshold = new double[T];

        for (int i = 0; i < T; i += 1) {
            Percolation expGrid = pf.make(N);

            while (!expGrid.percolates()) {
                // Choose a site uniformly at random among all blocked sites.
                int row, col;
                do {
                    row = StdRandom.uniform(N);
                    col = StdRandom.uniform(N);
                } while (expGrid.isOpen(row, col));

                // Open the site
                expGrid.open(row, col);
            }

            threshold[i] = (double) expGrid.numberOfOpenSites() / N / N;
        }

        mean = StdStats.mean(threshold);
        stdDev = StdStats.stddev(threshold);
        confidenceHigh = mean + 1.96 * stdDev / Math.sqrt((double) T);
        confidenceLow = mean - 1.96 * stdDev / Math.sqrt((double) T);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stdDev;
    }

    public double confidenceLow() {
        return confidenceLow;
    }

    public double confidenceHigh() {
        return confidenceHigh;
    }
}
