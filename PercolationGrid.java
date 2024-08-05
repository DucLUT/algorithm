import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import java.util.*;

public class PercolationGrid {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java PercolationGrid <N>");
            return;
        }

        int N = Integer.parseInt(args[0]);
        boolean[][] grid = new boolean[N][N];
        UnionFind uf = new UnionFind(N * N + 2); // Include two virtual nodes

        int topVirtualNode = N * N;
        int bottomVirtualNode = N * N + 1;

        // Connect top row to top virtual node
        for (int i = 0; i < N; i++) {
            uf.union(topVirtualNode, i);
        }

        // Connect bottom row to bottom virtual node
        for (int i = 0; i < N; i++) {
            uf.union(bottomVirtualNode, (N - 1) * N + i);
        }

        StdDraw.setScale(0, N);
        StdDraw.setPenRadius(0.005);

        while (!uf.connected(topVirtualNode, bottomVirtualNode)) {
            if (StdDraw.isMousePressed()) {
                int col = (int) StdDraw.mouseX();
                int row = N - 1 - (int) StdDraw.mouseY();

                if (row >= 0 && row < N && col >= 0 && col < N && !grid[row][col]) {
                    grid[row][col] = true;
                    drawGrid(grid, N);

                    int current = row * N + col;
                    if (row > 0 && grid[row - 1][col]) uf.union(current, (row - 1) * N + col);
                    if (row < N - 1 && grid[row + 1][col]) uf.union(current, (row + 1) * N + col);
                    if (col > 0 && grid[row][col - 1]) uf.union(current, row * N + col - 1);
                    if (col < N - 1 && grid[row][col + 1]) uf.union(current, row * N + col + 1);

                    StdDraw.pause(100); // Pause for visualization
                }
            }
        }

        System.out.println("Percolation achieved!");
    }

    private static void drawGrid(boolean[][] grid, int N) {
        StdDraw.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j]) {
                    StdDraw.filledSquare(j + 0.5, N - i - 0.5, 0.45);
                } else {
                    StdDraw.square(j + 0.5, N - i - 0.5, 0.45);
                }
            }
        }
    }

    static class UnionFind {
        private int[] parent;
        private int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]]; // Path compression
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            // Weighted union
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
    }
}