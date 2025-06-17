import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        int sum = 0;
        for (int item : L) {
            sum += item;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> output = new ArrayList<>();
        for (int item : L) {
            if (item % 2 == 0)  output.add(item);
        }
        return output;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> output = new ArrayList<>();
        Set<Integer> l2Set = new HashSet<>(L2);
        for (int item : L1) {
            if (l2Set.contains(item)) output.add(item);
        }
        return output;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count = 0;
        for (String word : words) {
            char[] wordArr = word.toCharArray();
            for (char character : wordArr) {
                if (character == c) count++;
            }
        }
        return count;
    }
}
