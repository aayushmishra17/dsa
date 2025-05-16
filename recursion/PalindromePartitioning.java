package recursion;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * E.g.: s = "aab"
 * Possible partitions: ["a", "a", "b"], ["aa", "b"]
 */
public class PalindromePartitioning {
    public static void main(String[] args) {
        String s = "aab";
        System.out.println("s = " + s);
        PalindromePartitioning obj = new PalindromePartitioning();
        obj.approach1(s);
    }
    /*
     * Time - O(2^N)[generate all substring] + O(N/2)[check palindrome] + O(k)[Inserting into ans]; k = avg length of each palindrome.
     * Space - O(k*x)[current partitions] + O(N)[aux. stack space]; x = no. of partitions.
     */
    private void approach1(String s) {
        List<List<String>> ans = new ArrayList<>();
        palindromePartition(0, s, ans, new ArrayList<>());
        System.out.println("Palindrome partitions:");
        System.out.println(ans);
    }
    private void palindromePartition(int index, String s, List<List<String>> ans, List<String> currentPartitions) {
        if(index == s.length()) {
            ans.add(new ArrayList<>(currentPartitions));
            return;
        }
        for(int currentIndex = index; currentIndex < s.length(); currentIndex++) {
            if(isPalindrome(s, index, currentIndex)) {
                currentPartitions.add(s.substring(index, currentIndex + 1));
                palindromePartition(currentIndex + 1, s, ans, currentPartitions);
                currentPartitions.remove(currentPartitions.size() - 1);
            }
        }
    }
    /*
     * Time - O(N/2)
     * Space - O(1)
     */
    private boolean isPalindrome(String s, int startIndex, int endIndex) {
        while(startIndex <= endIndex) {
            if(s.charAt(startIndex++) != s.charAt(endIndex--)) {
                return false;
            }
        }
        return true;
    }
}
