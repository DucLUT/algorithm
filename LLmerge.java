

public class LLmerge<Item extends Comparable<Item>> implements Iterable<Item>{
    private Node first;
    private int N;
    private class Node {
        Item item;
        Node next;
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public void push(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }
    @Override
    public Iterator<Item> iterator(){
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<Item> {
        private Node current = first;
        @Override
        public boolean hasNext(){
            return current != null;
        }
        @Override
        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    private Node split(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow.next;
        slow.next = null;
        return mid;
    }
    
    private Node merge(Node left, Node right) {
        Node dummy = new Node();
        Node current = dummy;
    
        while (left != null && right != null) {
            if (less(left.item, right.item)) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }
    
        if (left != null) {
            current.next = left;
        } else {
            current.next = right;
        }
    
        return dummy.next;
    }
    
    private Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head; 
        }
        Node mid = split(head); 
        Node left = mergeSort(head); 
        Node right = mergeSort(mid);
        return merge(left, right); 
    }
    public void sort(){
        first = mergeSort(first);
    }
    public void printList() {
        Node current = first;
        while (current != null) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        LLmerge<Integer> list = new LLmerge<>();
        list.push(3);
        list.push(1);
        list.push(4);
        list.push(2);
        list.push(5);
    
        System.out.println("Original list:");
        list.printList();
    
        list.sort();
    
        System.out.println("Sorted list:");
        list.printList();
    }
    
}