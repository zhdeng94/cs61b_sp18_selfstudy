package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;

public class Percolation {

    private int[] siteArray; // Array to track if a site is open (value 1) or block (value 0)
    private int numOfOpenSite;
    // disjoint set for determining if the grid percolates
    private WeightedQuickUnionUF ufForPercolate;
    // disjoint set for determining if a particular site is full or block
    private WeightedQuickUnionUF ufForFull;
    private int size;

    /**
     * Create an N x N grid, with all sites initially blocked
     * @param N: size of the grid
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("The size of the grid must be greater than 0!");
        }
        size = N;
        siteArray = new int[N * N];
        numOfOpenSite = 0;
        ufForPercolate = new WeightedQuickUnionUF(N * N);
        ufForFull = new WeightedQuickUnionUF(N * N);

        for (int i = 0; i < siteArray.length; i += 1) {
            siteArray[i] = 0;
        }

        for (int i = 1; i < size; i += 1) {
            ufForPercolate.union(xyTo1D(0, 0), xyTo1D(0, i));
            ufForPercolate.union(xyTo1D(size - 1, 0), xyTo1D(size - 1, i));
            ufForFull.union(xyTo1D(0, 0), xyTo1D(0, i));
        }
    }

    /**
     * Open the site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        int idx = xyTo1D(row, col);

        if (siteArray[idx] == 0) {
            siteArray[idx] = 1;
            numOfOpenSite += 1;

            // union neighboring open sites
            if (row > 0) {
                int up = xyTo1D(row - 1, col);
                if (siteArray[up] == 1) {
                    ufForPercolate.union(up, idx);
                    ufForFull.union(up, idx);
                }
            }

            if (row < size - 1) {
                int down = xyTo1D(row + 1, col);
                if (siteArray[down] == 1) {
                    ufForPercolate.union(down, idx);
                    ufForFull.union(down, idx);
                }
            }

            if (col > 0) {
                int left = xyTo1D(row, col - 1);
                if (siteArray[left] == 1) {
                    ufForPercolate.union(left, idx);
                    ufForFull.union(left, idx);
                }
            }

            if (col < size - 1) {
                int right = xyTo1D(row, col + 1);
                if (siteArray[right] == 1) {
                    ufForPercolate.union(right, idx);
                    ufForFull.union(right, idx);
                }
            }
        }
    }


    /**
     * Return true if the site at (row, col) is open.
     */
    public boolean isOpen(int row, int col) {
        int idx = xyTo1D(row, col);
        return siteArray[idx] == 1;
    }

    /**
     * Return true if the site at (row, col) is full.
     */
    public boolean isFull(int row, int col) {
        int idx = xyTo1D(row, col);
        return siteArray[idx] == 1 && ufForFull.connected(idx, xyTo1D(0, 0));
    }

    /**
     * Return the number of open sites in the grid.
     */
    public int numberOfOpenSites() {
        return numOfOpenSite;
    }

    /**
     * Return true if the grid percolates.
     */
    public boolean percolates() {
        return ufForPercolate.connected(xyTo1D(0, 0), xyTo1D(size - 1, 0));
    }

    /**
     * Convert the (row, col) coordinate of a site to the 1D index in the siteArray
     */
    private int xyTo1D(int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size) {
            throw new IndexOutOfBoundsException("The index of the row and col must be in the " +
                                                "range of 0 and " + String.valueOf(size - 1));
        }
        return x * size + y;
    }

    /**
     * For unit testing purpose.
     */
    @Test
    public static void main(String[] args) {
        Percolation test = new Percolation(5);
        assertFalse(test.isOpen(0, 1));
        assertFalse(test.isFull(0, 3));
        assertFalse(test.percolates());
        assertEquals(test.numberOfOpenSites(), 0);

        test.open(2, 2);
        test.open(0,2);
        assertTrue(test.isOpen(2,2));
        assertTrue(test.isFull(0, 2));
        assertFalse(test.isFull(0,1));
        assertEquals(test.numberOfOpenSites(), 2);

        test.open(1, 2);
        test.open(3,2);
        test.open(4, 2);
        test.open(4, 4);
        test.open(0, 4);

        assertEquals(test.numberOfOpenSites(), 7);
        assertTrue(test.isFull(0, 4));
        assertTrue(test.isFull(0, 2));
        assertTrue(test.isFull(1, 2));
        assertTrue(test.isFull(2, 2));
        assertTrue(test.isFull(3, 2));
        assertTrue(test.isFull(4, 2));
        assertFalse(test.isFull(4,4));
    }

}
