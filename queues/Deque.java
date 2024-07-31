import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item");
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        n++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item");
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("No element found");
        }
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("No element found");
        }
        Item item = last.item;
        last = last.prev;
        n--;
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        // Test addFirst and addLast
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(0);
        deque.addLast(3);

        // Test iterator
        System.out.println("Deque contents:");
        for (int item : deque) {
            System.out.print(item + " ");
        }
        System.out.println();

        // Test exception for null argument
        try {
            deque.addFirst(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException for addFirst(null)");
        }

        try {
            deque.addLast(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException for addLast(null)");
        }

        // Test iterator exceptions
        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            System.out.println("Caught NoSuchElementException for iterator.next()");
        }

        try {
            iterator.remove();
        } catch (UnsupportedOperationException e) {
            System.out.println("Caught UnsupportedOperationException for iterator.remove()");
        }
    }
}