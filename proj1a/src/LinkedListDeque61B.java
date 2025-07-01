import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private final Node sentinel;
    public int size = 0;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T x) {
        Node node = new Node(sentinel, x, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node node = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> output = new ArrayList<>();
        Node cur = sentinel.next;
        if (cur == null || cur.next == cur) return output;
        while (cur != sentinel) {
            output.add(cur.item);
            cur = cur.next;
        }

        return output;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) return null;
        T firstNodeValue = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return firstNodeValue;
    }

    @Override
    public T removeLast() {
        if (sentinel.next == sentinel) return null;
        T lastNodeValue = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return lastNodeValue;
    }

    @Override
    public T get(int index) {
        if (size < 1) return null;
        if (index < 0 || index > size - 1) return null;

        Node cur = sentinel;

        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.item;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
