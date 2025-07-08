import deque.ArrayDeque61B;

import deque.Deque61B;
import edu.princeton.cs.algs4.In;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

    @Test
    public void addFirstTest() {
         Deque61B<Integer> lld1 = new ArrayDeque61B<>();
         lld1.addFirst(5);
         lld1.addFirst(4);
         lld1.addFirst(3);
         lld1.addFirst(2);
         lld1.addFirst(1);

         List<Integer> output = new ArrayList<>();

         for (int i = 1; i <= 8; i++) {
             if (i <= 5) output.add(i);
             else output.add(null);
         }

         assertThat(lld1.toList()).isEqualTo(output);
    }

    @Test
    void addLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addLast(5);

        List<Integer> output = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            if (i <= 5) output.add(i);
            else output.add(null);
        }
        assertThat(lld1.toList()).isEqualTo(output);
    }

    @Test
    void firstElementIndexTest() {
        ArrayDeque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(5);
        lld1.addLast(6);
        lld1.addLast(7);
        assertThat(lld1.firstElementIndex).isEqualTo(5);
        lld1.addFirst(4);
        assertThat(lld1.firstElementIndex).isEqualTo(4);
        lld1.addLast(8);
        assertThat(lld1.firstElementIndex).isEqualTo(4);
        lld1.addFirst(0);
        assertThat(lld1.firstElementIndex).isEqualTo(3);
    }

    @Test
    void getTest() {
         Deque61B<Integer> lld1 = new ArrayDeque61B<>();
         assertThat(lld1.get(0)).isEqualTo(null);

         lld1.addFirst(1);
         lld1.addFirst(2);
         lld1.addLast(10);

         assertThat(lld1.get(0)).isEqualTo(2);

        Deque61B<Integer> lld2 = new ArrayDeque61B<>();

        lld2.addLast(10);
        assertThat(lld2.get(0)).isEqualTo(10);

        lld2.addLast(100);
        lld2.addLast(1000);
        assertThat(lld2.get(0)).isEqualTo(10);
        assertThat(lld2.get(2)).isEqualTo(1000);
    }

    @Test
    void sizeTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        assertThat(lld1.size()).isEqualTo(0);

        lld1.addFirst(1);
        assertThat(lld1.size()).isEqualTo(1);

        lld1.addLast(1);
        assertThat(lld1.size()).isEqualTo(2);

        lld1.removeFirst();
        assertThat(lld1.size()).isEqualTo(1);

        lld1.removeLast();
        assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    void isEmptyTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();

        lld1.addFirst(10);
        assertThat(lld1.isEmpty()).isFalse();

        lld1.removeFirst();
        assertThat(lld1.isEmpty()).isTrue();
    }

    @Test
    void removeFirstTest() {
         ArrayDeque61B<Integer> lld1 = new ArrayDeque61B<>();
         lld1.addFirst(1);
         assertThat(lld1.get(0)).isEqualTo(1);
         lld1.addLast(2);
         lld1.addFirst(3);
         lld1.removeFirst();
         assertThat(lld1.get(0)).isEqualTo(1);
         assertThat(lld1.size()).isEqualTo(2);
         lld1.removeFirst();
         assertThat(lld1.get(0)).isEqualTo(2);
         int val = lld1.removeFirst();
         assertThat(lld1.get(0)).isEqualTo(null);
         assertThat(val).isEqualTo(2);
    }

    @Test
    void removeLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(1);
        assertThat(lld1.get(0)).isEqualTo(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addLast(5);
        lld1.addLast(6);
        lld1.addLast(7);
        lld1.addLast(8);

        List<Integer> lld2 = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            lld2.add(i);
        }

        assertThat(lld1.get(7)).isEqualTo(8);
        assertThat(lld1.removeLast()).isEqualTo(8);
        lld1.addLast(9);
        assertThat(lld1.get(7)).isEqualTo(9);
        assertThat(lld1.removeLast()).isEqualTo(9);
        assertThat(lld1.get(7)).isNull();
    }
}
