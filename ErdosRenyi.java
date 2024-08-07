import java.util.Random;

public class ErdosRenyi {

    public static int count(int N) {
        WeightedQuickUnionLL uf = new WeightedQuickUnionLL();
        for (int i = 0; i < N; i++) {
            uf.newSite();
        }
        Random random = new Random();
        int connections = 0;
        int pairsGenerated = 0;

        while (true) {
            int p = random.nextInt(N);
            int q = random.nextInt(N);
            while (q == p) {
                q = random.nextInt(N);
            }
            pairsGenerated++;

            if (!uf.connected(p, q)) {
                uf.union(p, q);
                connections++;
            }
            if (uf.component() == 1) {
                break;
            }
        }
        System.out.println("Pairs generated: " + pairsGenerated);
        return connections;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java ErdosRenyi <N>");
            System.exit(1);
        }

        int N = Integer.parseInt(args[0]);
        int connections = count(N);
        double theoreticalPairs = 0.5 * N * Math.log(N);
        System.out.println("Number of connections generated: " + connections);
        System.out.println("Theoretical number of pairs: " + theoreticalPairs);
    }
}