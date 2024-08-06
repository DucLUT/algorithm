import java.util.LinkedList;
public class WeightedQuickUnionLL {

    private LinkedList<Node> nodes;
    private class Node {
        int id;
        Node parent;
        int size;
        Node (int id){
            this.id = id;
            this.parent = this;
            this.size = 1;
        }
    }
    public WeightedQuickUnionLL(){
        nodes = new LinkedList<>();
    }
    public int newSite(){
        Node newNode = new Node(nodes.size());
        nodes.add(newNode);
        return newNode.id;
    }
    public int find(int p){
        validate(p);
        Node node = nodes.get(p);
        while (node.id != node.parent.id){
            node.parent = node.parent.parent;
            node = node.parent;
        }
        return node.id;

    }
    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        Node rootNodeP = nodes.get(rootP);
        Node rootNodeQ = nodes.get(rootQ);
        if (rootNodeP.size < rootNodeQ.size){
            rootNodeP.parent = rootNodeQ;
            rootNodeQ.size += rootNodeP.size;
        }else{
            rootNodeQ.parent = rootNodeP;
            rootNodeP.size += rootNodeQ.size;


        }

    }
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    private void validate(int p){
        if (p < 0 || p >= nodes.size()){
            throw new IllegalArgumentException(p + " is not valid index");
        }
    }

    public static void main(String[] args) {
        WeightedQuickUnionLL uf = new WeightedQuickUnionLL();
        int site1 = uf.newSite();
        int site2 = uf.newSite();
        int site3 = uf.newSite();

        System.out.println("Site 1: " + site1);
        System.out.println("Site 2: " + site2);
        System.out.println("Site 3: " + site3);

        uf.union(site1, site2);
        System.out.println("Connected site1 and site2: " + uf.connected(site1, site2));

        uf.union(site2, site3);
        System.out.println("Connected site2 and site3: " + uf.connected(site2, site3));
        System.out.println("Connected site1 and site3: " + uf.connected(site1, site3));
    }


}
