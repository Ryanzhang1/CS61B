package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private boolean[][] flagOpen;
    private WeightedQuickUnionUF sites;
    private WeightedQuickUnionUF sites2;
    private int top;
    private int bottom;
    private int numOpen;

    /*
    Trans (x,y) to 1 dimension
    */
    private int xyTo1D(int x, int y) {
        return x * N + y;
    }

    /*
    judge (x,y) in range
    */
    private void validRange(int x, int y) {
        if (x >= N || y >= N || x < 0 || y < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    /*
    Union (x,y) and(x1,y1) when (x1,y1) in range;
     */
    private void unionHelper(int x, int y, int x1, int y1) {
        if (x1 >= N || y1 >= N || x1 < 0 || y1 < 0) {
            return;
        }
        if (flagOpen[x1][y1]) {
            sites.union(xyTo1D(x, y), xyTo1D(x1, y1));
            sites2.union(xyTo1D(x, y), xyTo1D(x1, y1));
        }
    }

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        numOpen = 0;
        top = N * N;
        bottom = N * N + 1;
        sites = new WeightedQuickUnionUF(N * N + 2);
        sites2 = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            sites.union(xyTo1D(0, i), top);
        }
        for (int i = 0; i < N; i++) {
            sites.union(xyTo1D(N - 1, i), bottom);
        }
        for (int i = 0; i < N; i++) {
            sites2.union(xyTo1D(0, i), top);
        }
        flagOpen = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                flagOpen[i][j] = false;
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validRange(row, col);
        if (isOpen(row, col)) {
            return;
        }
        flagOpen[row][col] = true;
        numOpen++;
        unionHelper(row, col, row, col + 1);
        unionHelper(row, col, row + 1, col);
        unionHelper(row, col, row, col - 1);
        unionHelper(row, col, row - 1, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validRange(row, col);
        return flagOpen[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validRange(row, col);
        if (!isOpen(row, col)) {
            return false;
        }
        return sites2.connected(xyTo1D(row, col), top);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return sites.connected(top, bottom);
    }

    public static void main(String[] args) {
    }
}
