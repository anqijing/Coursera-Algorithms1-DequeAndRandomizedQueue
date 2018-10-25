import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private final Node first = new Node();
    private final Node last = new Node();
    private int size;

    private class Node {
        Item item;
        Node previous;
        Node next;
    }

    public Deque() {
        size = 0;
        first.next = last;
        first.previous = null;
        last.previous = first;
        last.next = null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("The item has no value.");
        Node newfirst = new Node();
        Node oldfirst = first.next;
        newfirst.item = item;
        newfirst.next = oldfirst;
        newfirst.previous = first;
        oldfirst.previous = newfirst;
        first.next = newfirst;
        size++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("The item has no value.");
        Node newlast = new Node();
        newlast.item = item;
        newlast.next = last;
        last.previous.next = newlast;
        newlast.previous = last.previous;
        last.previous = newlast;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("The size of Deque is zero.");
        Node oldfirst = first.next;
        Item item = oldfirst.item;
        first.next = oldfirst.next;
        first.next.previous = first;
        oldfirst = null;
        size--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("The size of Deque is zero.");
        Node oldlast = last.previous;
        Item item = oldlast.item;
        last.previous = oldlast.previous;
        last.previous.next = last;
        oldlast = null;
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first.next;

        public boolean hasNext() {
            return current.next != null;
        }

        public Item next() {
            if (current.next == null)
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


