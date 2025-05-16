package recursion;

import java.util.ArrayList;
import java.util.List;

/*
 * The set [1, 2, 3, 4,.., n] contains n! unique permutations.
 * Consider all permutaions in order, then for a given n and k. return the Kth permutation.
 */
public class KthPermutationSequence {
    public static void main(String[] args) {
        int n = 3;
        int k = 3;
        System.out.println("N = " + n + " K = " + k);
        KthPermutationSequence obj = new KthPermutationSequence();
        obj.approach1(n, k);
    }
    /*
     * Time - O(N) * O(N)[remove taken number from numbers list]
     * Space - O(N)[to store the answer]
     */
    private void approach1(int n, int k) {
        int factorial = 1;
        List<Integer> numbers = new ArrayList<>();
        for(int i = 1; i < n; i++) {
            factorial *= i;
            numbers.add(i);
        }
        numbers.add(n);
        String ans = "";
        k = k - 1; // we are numbering permutations as 0 based.
        while(true) {
            ans = ans + String.valueOf(numbers.get(k / factorial));
            numbers.remove(k / factorial);
            if(numbers.size() == 0) {
                break;
            }
            k = k % factorial;
            factorial = factorial / numbers.size();
        }
        System.out.println("Kth permutation = " + ans);
    }
}
