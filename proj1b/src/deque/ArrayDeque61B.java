package deque;

import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] array;
    private int nextFirst;
    public int nextLast;
    private int size;
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
        if (size == array.length) {
            resizeUp(2);
        }
        array[nextFirst] = x;
        firstElementIndex = nextFirst;
        nextFirst = Math.floorMod(nextFirst - 1, array.length);
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == array.length) {
            resizeUp(2);
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
            if (array[curIndex] == null) break;
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
        if (array[firstElementIndex] == null) return null;
        T element = array[firstElementIndex];
        array[firstElementIndex] = null;
        nextFirst = firstElementIndex;
        firstElementIndex = Math.floorMod(nextFirst + 1, array.length);
        size--;
        if(toResizeDown()) resizeDown();
        return element;
    }

    @Override
    public T removeLast() {
        if (array[lastElementIndex] == null) return null;
        T element = array[lastElementIndex];
        array[lastElementIndex] = null;
        nextLast = lastElementIndex;
        lastElementIndex = Math.floorMod(nextLast - 1, array.length);
        size--;
        if(toResizeDown()) resizeDown();
        return element;
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

    public void resizeUp(double by) {
        T[] outputArray = (T[]) new Object[(int) (array.length * by)];
        for (int i = 0; i < array.length; i++) {
            int newArrayIndex = Math.floorMod(i + firstElementIndex, outputArray.length);
            int curArrayIndex = Math.floorMod(i + firstElementIndex, array.length);
            outputArray[newArrayIndex] = array[curArrayIndex];
            lastElementIndex = newArrayIndex;
            nextLast = Math.floorMod(newArrayIndex + 1, outputArray.length);
        }
        array = outputArray;
    }

    public void resizeDown() {
        T[] outputArray = (T[]) new Object[(int) (array.length * 0.5)];
        int curIndex = firstElementIndex;
        int lastIndex = -1;
        for (int i = 0; i < outputArray.length; i++) {
            if (array[curIndex] == null) break;
            if (array[curIndex] != null) outputArray[i] = array[curIndex];
            curIndex++;
            if (outputArray[i] != null) lastIndex++;
        }
        nextFirst = Math.floorMod(-1, outputArray.length);
        nextLast = Math.floorMod(lastIndex + 1, outputArray.length);

        firstElementIndex = Math.floorMod(nextFirst + 1, outputArray.length);
        lastElementIndex = Math.floorMod(nextLast - 1, outputArray.length);
        array = outputArray;
    }

    private boolean toResizeDown() {
        if (array.length >= 16) {
            double ratio = (double) size / array.length;
            if (ratio < 0.25) return true;
        }
        return false;
    }
}
