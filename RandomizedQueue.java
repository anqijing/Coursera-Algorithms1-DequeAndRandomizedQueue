import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private final Node head = new Node();
    private final Node tail = new Node();

    private class Node {
        Item item;
        Node next;
    }

    public RandomizedQueue() {
        size = 0;
        head.next = tail;
        tail.next = null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("No more item.");
        int n = StdRandom.uniform(size);
        Node node = head;
        for (int i = 0; i < n; i++) {
            node = node.next;
        }
        Item item = node.next.item;
        node.next = node.next.next;
        size--;
        return item;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Null argument.");
        Node newnode = new Node();
        newnode.item = item;
        newnode.next = head.next;
        head.next = newnode;
        size++;
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("The queue is empty.");
        int n = StdRandom.uniform(size);
        Node s = head.next;
        for (int i = 0; i < n; i++) {
            s = s.next;
        }
        return s.item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head.next;

        public boolean hasNext() {
            return current.next != null;
        }

        public Item next() {
            if (current == null)
                throw new NoSuchElementException("No more items to return.");
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
