import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import java.util.*;

public class RandomGrid {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java RandomGrid <N>");
            return;
        }

        int N = Integer.parseInt(args[0]);
        Connection[] connections = generate(N);

        UnionFind uf = new UnionFind(N * N);

        StdDraw.setScale(-1, N);
        StdDraw.setPenRadius(0.005);

        for (Connection connection : connections) {
            int p = connection.p.x * N + connection.p.y;
            int q = connection.q.x * N + connection.q.y;

            if (!uf.connected(p, q)) {
                uf.union(p, q);
                drawConnection(connection);
                StdDraw.pause(100); // Increased delay for better visualization
            }
        }
    }

    public static Connection[] generate(int N) {
        List<Connection> connections = new ArrayList<>();

        // Generate all connections in an N-by-N grid
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i < N - 1) {
                    connections.add(new Connection(new Point(i, j), new Point(i + 1, j)));
                }
                if (j < N - 1) {
                    connections.add(new Connection(new Point(i, j), new Point(i, j + 1)));
                }
            }
        }

        // Shuffle the connections using a Random object seeded with StdRandom.getSeed()
        Collections.shuffle(connections, new Random(StdRandom.getSeed()));

        // Randomly orient the connections
        for (Connection connection : connections) {
            if (StdRandom.bernoulli(0.5)) {
                Point temp = connection.p;
                connection.p = connection.q;
                connection.q = temp;
            }
        }

        return connections.toArray(new Connection[0]);
    }

    private static void drawConnection(Connection connection) {
        StdDraw.line(connection.p.x, connection.p.y, connection.q.x, connection.q.y);
    }

    static class Connection {
        Point p, q;

        Connection(Point p, Point q) {
            this.p = p;
            this.q = q;
        }

        @Override
        public String toString() {
            return p + " " + q;
        }
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
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