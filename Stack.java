import java.util.*;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Stack<Item> implements Iterable<Item>{
    private Node first;
    private int N;
    private class Node{
        Item item;
        Node next;
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
    public Item peek(){
        Item item = first.item;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }
        // Inner class to define iterator
    private class LinkedListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args){
        Stack<String> s = new Stack<String>();
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!item.equals("-")){
                s.push(item);
            }else if (!s.isEmpty()){
                StdOut.print(s.pop() + " ");
            }
        }
    }
}