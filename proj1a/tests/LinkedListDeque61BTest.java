import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    /** This test checks whether toList returns an empty list when no nodes exist other than sentinel. */
    public void returnEmptyList() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.toList()).isEmpty();
    }

    @Test
    /** This test checks if toList returns an empty list after removing all its nodes. */
    public void returnEmptyListAfterRemovingNodes() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst(0);
        lld1.addLast(999);
        lld1.removeFirst();
        lld1.removeLast();
        assertThat(lld1.toList()).isEmpty();
    }

    @Test
    /** This test checks if a deque is empty, meaning that only the sentinel node exists in it */
    public void checkIsEmpty() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.isEmpty()).isTrue();

        lld1.addFirst(5);
        assertThat(lld1.isEmpty()).isFalse();

        lld1.removeLast();
        assertThat(lld1.isEmpty()).isTrue();
    }

    @Test
    /** This test checks the size of a deque after adding and removing nodes */
    public void sizeTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.size()).isEqualTo(0); // Test that size of new list is 0

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
    /** This test checks the output of get method */
    public void getTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.get(0)).isEqualTo(null);

        for (int i = 0; i < 200; i++) {
            lld1.addLast(i + 1);
        }
        assertThat(lld1.get(0)).isEqualTo(1);
        assertThat(lld1.get(199)).isEqualTo(200);
        assertThat(lld1.get(100)).isEqualTo(101);
        assertThat(lld1.get(-1)).isEqualTo(null);
        assertThat(lld1.get(28723)).isEqualTo(null);
    }

    @Test
    /** This test validates removeFirst functionality */
    public void removeFirstTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        List<Integer> checkList = new ArrayList<>();

        assertThat(lld1.removeFirst()).isEqualTo(null);

        for (int i = 0; i < 3; i++) {
            lld1.addLast(i);
            checkList.add(i);
        }

        assertThat(lld1.removeFirst()).isEqualTo(0);
        checkList.removeFirst();
        assertThat(lld1.toList()).isEqualTo(checkList);

        assertThat(lld1.removeFirst()).isEqualTo(1);
        checkList.removeFirst();
        assertThat(lld1.toList()).isEqualTo(checkList);

        assertThat(lld1.removeFirst()).isEqualTo(2);
        checkList.removeFirst();
        assertThat(lld1.toList()).isEqualTo(checkList);

        assertThat(lld1.removeFirst()).isEqualTo(null);
    }

    @Test
    /** This test validates removeLast functionality */
    public void removeLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        List<Integer> checkList = new ArrayList<>();

        assertThat(lld1.removeLast()).isEqualTo(null);

        for (int i = 0; i < 5; i++) {
            lld1.addLast(i);
            checkList.add(i);
        }

        lld1.removeLast();
        assertThat(lld1.get(lld1.size() - 1)).isEqualTo(3);
        lld1.removeLast();
    }
}