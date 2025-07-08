package deque;

import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] array;
    public int nextFirst; // TODO: change to default after testing
    public int nextLast; // TODO: change to default after testing
    int size;
    public int firstElementIndex;
    public int lastElementIndex;

    public ArrayDeque61B() {
        array = (T[]) new Object[8];;
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        if (array[nextFirst] != null) {
            // TODO: resizeArray();
        }
        array[nextFirst] = x;
        firstElementIndex = nextFirst;
        nextFirst = Math.floorMod(nextFirst - 1, array.length);
        size++;
    }

    @Override
    public void addLast(T x) {
        if (array[nextLast] != null) {
            // TODO: resizeArray();
        }

        if (array[firstElementIndex] == null) firstElementIndex = nextLast;

        array[nextLast] = x;
        lastElementIndex = nextLast;
        nextLast = Math.floorMod(nextLast + 1, array.length);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int cur = firstElementIndex;
        for (int i = 0; i < array.length; i++) {
            int curIndex = Math.floorMod(cur, array.length);
            returnList.add(array[curIndex]);
            cur++;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
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
        int getIndex = Math.floorMod(firstElementIndex + index, array.length);
        if (index < 0 || index >= array.length || array[getIndex] == null) return null;
        return array[getIndex];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
