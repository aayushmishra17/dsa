package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PrintAllPermutationsOfArray {
    public static void main(String[] args) {
        PrintAllPermutationsOfArray obj = new PrintAllPermutationsOfArray();
        int[] arr = new int[] { 1, 3, 2 };
        obj.approach1(arr);
        obj.approach2(arr);
    }
    /*
        Time - O(N! * N)
        Space - O(N)[bucket list] + O(N)[freq array]
     */
    private void approach1(int[] arr) {
        int n = arr.length;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> bucket = new ArrayList<>();
        boolean[] freq = new boolean[n];

        class Permute {
            private void permute(int[] arr, List<List<Integer>> ans, List<Integer> bucket, boolean[] freq) {
                if(bucket.size() == arr.length) {
                    ans.add(new ArrayList<>(bucket));
                    return;
                }
                for(int i = 0; i < freq.length; i++) {
                    if(freq[i] != true) {
                        freq[i] = true;
                        bucket.add(arr[i]);
                        permute(arr, ans, bucket, freq);
                        bucket.remove(bucket.size() - 1);
                        freq[i] = false;
                    }
                }
            }
        }

        Permute obj = new Permute();
        obj.permute(arr, ans, bucket, freq);

        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("All possible permutations = ");
        System.out.println(ans);
    }
    /*
        Time - O(N! * N)
        Space - O(1) aux stk. space = O(N)
     */
    private void approach2(int[] arr) {
        int n = arr.length;
        List<List<Integer>> ans = new ArrayList<>();

        class Permute {
            private void permute(int indx, int[] arr, List<List<Integer>> ans) {
                if(indx == arr.length) {
                    ans.add(new ArrayList<Integer>(Arrays.stream(arr).boxed().collect(Collectors.toList())));
                    return;
                }
                for(int i = indx; i < arr.length; i++) {
                    swap(i, indx, arr);
                    permute(indx + 1, arr, ans);
                    swap(i, indx, arr);
                }
            }
            private void swap(int indx1, int indx2, int[] arr) {
                int temp = arr[indx1];
                arr[indx1] = arr[indx2];
                arr[indx2] = temp;
            }
        }

        Permute obj = new Permute();
        obj.permute(0, arr, ans);

        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("All possible permutations = ");
        System.out.println(ans);
    }
}
