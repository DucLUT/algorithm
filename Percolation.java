import edu.princeton.cs.algs4.StdIn;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private boolean grid[][];
    private int gridsize;
    private int gridsquare;
    private WeightedQuickUnionUF wqfGrid;
    private WeightedQuickUnionUF wqfFull;
    private int virtualTop;
    private int virtualBottom;
    private int openSites;


    private int xyTo1d(int row, int col){
        return gridsize * (row - 1) + col;

    }
    private boolean isOnGrid(int row, int col){
        int x = row - 1;
        int y = col - 1;
        return (x >= 0 && y >= 0 && x < gridsize && y < gridsize);
    }
    private void validateSite(int row, int col){
        if (!isOnGrid(row,col)){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if (n<=0) throw new IllegalArgumentException("N must be > 0");
        gridsize = n;
        gridsquare = n*n;
        grid = new boolean[n][n];
        wqfGrid = new WeightedQuickUnionUF(gridsquare + 2); //included top and bottom virtual
        wqfFull = new WeightedQuickUnionUF(gridsquare + 1); //included top virtual
        virtualBottom = gridsquare + 1;
        virtualTop = n*n;
        openSites = 0;

        

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        validateSite(row, col);
        int x = row - 1;
        int y = col - 1;
        int onedIndex = xyTo1d(row, col) - 1;
        if (!isOpen(row, col)){
            return;
        }
        grid[x][y] = true;
        openSites++;
        if (x == 0){
            wqfGrid.union(virtualTop, onedIndex);
            wqfFull.union(virtualTop, onedIndex);
        }
        if (row == gridsquare){
            wqfFull.union(virtualBottom, onedIndex);
        }
        //below is checking the neigbor
        //on the right of the opening site
        if (isOnGrid(row, col + 1) && isOpen(row, col + 1)){
            wqfGrid.union(onedIndex, xyTo1d(row, col + 1) - 1);
            wqfFull.union(onedIndex, xyTo1d(row, col + 1) -1);
        }
        //left
        if (isOnGrid(row, col - 1) && isOpen(row, col - 1)){
            wqfGrid.union(onedIndex, xyTo1d(row, col -1) - 1);
            wqfFull.union(onedIndex, xyTo1d(row, col -1) - 1);
        }
        //on the head
        if (isOnGrid(row - 1, col) && isOpen(row - 1, col)){
            wqfGrid.union(onedIndex, xyTo1d(row - 1, col) - 1);
            wqfFull.union(onedIndex, xyTo1d(row - 1, col) - 1);
        }
        //below
        if (isOnGrid(row + 1, col) && isOpen(row + 1, col)){
            wqfGrid.union(onedIndex, xyTo1d(row + 1, col) - 1);
            wqfFull.union(onedIndex, xyTo1d(row + 1, col) - 1);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        validateSite(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        validateSite(row, col);
        return wqfFull.connected(virtualTop, xyTo1d(row, col) - 1);
    }


    // returns the number of open sites
    public int numberOfOpenSites(){
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wqfGrid.connected(virtualTop,virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        // Check if at least one argument is provided
        if (args.length < 1) {
            StdOut.println("Usage: java Percolation <grid size> [<row1> <col1> <row2> <col2> ...]");
            return; // Exit the program
        }
    
        int size = Integer.parseInt(args[0]);
    
        Percolation percolation = new Percolation(size);
        int argCount = args.length;
        for (int i = 1; argCount >= 2; i += 2) {
            // Ensure there are enough arguments left to parse
            if (i + 1 >= args.length) {
                StdOut.println("Insufficient arguments for row and column.");
                break; // Exit the loop
            }
            int row = Integer.parseInt(args[i]);
            int col = Integer.parseInt(args[i + 1]);
            StdOut.printf("Adding row: %d  col: %d %n", row, col);
            percolation.open(row, col);
            if (percolation.percolates()) {
                StdOut.printf("The System percolates \n");
            }
            argCount -= 2;
        }
        if (!percolation.percolates()) {
            StdOut.print("Does not percolate \n");
        }
    }
    
    private void runTests() {
        for (int row = 1; row <= gridsize; row++) {
            for (int col = 1; col <= gridsize; col++) {
                if (isOpen(row, col)) {
                    StdOut.printf("Row: %d Col: %d is Open %n", row, col);
                } else {
                    StdOut.printf("Row: %d Col: %d is not Open %n", row, col);
                }
                if (isFull(row, col)) {
                    StdOut.printf("Row: %d Col: %d is Full %n", row, col);
                } else {
                    StdOut.printf("Row: %d Col: %d is not Full %n", row, col);
                }

            }
        }

        StdOut.printf("Sites Open: %d %n", numberOfOpenSites());
        if (percolates()) {
            StdOut.printf("Percolates %n");
        } else {
            StdOut.printf("Does not Percolate %n");
        }
    }


    
}
