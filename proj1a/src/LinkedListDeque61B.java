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
    }

    @Override
    public void addLast(T x) {
        Node node = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
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
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
