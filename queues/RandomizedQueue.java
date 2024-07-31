import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;

    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item");
        }
        if (size == queue.length) {
            resize(2 * queue.length);
        }
        queue[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int index = StdRandom.uniform(size);
        Item item = queue[index];
        queue[index] = queue[--size];
        queue[size] = null;
        if (size > 0 && size == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int index = StdRandom.uniform(size);
        return queue[index];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current = 0;
        private final Item[] shuffledQueue;

        public RandomizedQueueIterator() {
            shuffledQueue = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                shuffledQueue[i] = queue[i];
            }
            for (int i = 0; i < size; i++) {
                int randomIndex = StdRandom.uniform(size);
                Item temp = shuffledQueue[i];
                shuffledQueue[i] = shuffledQueue[randomIndex];
                shuffledQueue[randomIndex] = temp;
            }
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return shuffledQueue[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}