package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Given an array of intervals, merge all the overlapping intervals and return an array of non-overlapping intervals.
 */
public class MergeOverlappingSubIntervals {
    public static void main(String[] args) {
        MergeOverlappingSubIntervals obj = new MergeOverlappingSubIntervals();
        // int[][] intervals = new int[][] { 
        //     {1, 3},
        //     {2, 6},
        //     {8, 10},
        //     {15, 18}
        //  };
        int[][] intervals = new int[][] { 
            {1, 3},
            {2, 6},
            {8, 9},
            {9, 11},
            {8, 10},
            {2, 4},
            {15, 18},
            {16, 17}
         };
         obj.approach1(intervals);
         obj.approach2(intervals);
        }
    /*
     * Time - O(N logN)[sort] + O(2N)[We effectively iterate over all the elements twice while merging intervals]
     * Space - O(N)[used to store the answer]
     */
    private void approach1(int[][] intervals) {
        int[][] newIntervals = new int[intervals.length][];
        for(int indx = 0; indx < intervals.length; indx++) {
            newIntervals[indx] = Arrays.copyOf(intervals[indx], intervals[indx].length);
        }
        Arrays.sort(newIntervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = newIntervals.length;
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int start = newIntervals[i][0]; // current interval's starting element.
            int end = newIntervals[i][1]; // current interval's ending element.
            if(ans.size() > 0 && end <= ans.get(ans.size() - 1).get(1)) {
                /*
                 * If current interval's ending element is <= the previous intervals end then this interval is already 
                 * present inside the previous interval.
                 */
                continue;
            }
            for(int j = i + 1; j < n; j++) {
                if(newIntervals[j][0] < end) {
                    end = Math.max(end, newIntervals[j][1]);
                } else {
                    break;
                }
            }
            ans.add(List.of(start, end));
        }
        System.out.println("Overlapping intervals = ");
        for(int[] interval : intervals) {
            System.out.println(Arrays.toString(interval));
        }
        System.out.println("Non-overlapping intervals = " + ans);
    }
    /*
     * Time - O(N logN)[sort] + O(N)
     * Space - O(N)[used to store the answers]
     */
    private void approach2(int[][] intervals) {
        int[][] newIntervals = new int[intervals.length][];
        for(int indx = 0; indx < intervals.length; indx++) {
            newIntervals[indx] = Arrays.copyOf(intervals[indx], intervals[indx].length);
        }
        int n = newIntervals.length;
        Arrays.sort(newIntervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(ans.size() == 0 || newIntervals[i][0] > ans.get(ans.size() - 1).get(1)) {
                ans.add(Arrays.stream(newIntervals[i]).boxed().collect(Collectors.toList()));
            } else {
                ans.get(ans.size() - 1).set(1, Math.max(ans.get(ans.size() - 1).get(1), newIntervals[i][1]));
            }
        }
        System.out.println("Overlapping intervals = ");
        for(int[] interval : intervals) {
            System.out.println(Arrays.toString(interval));
        }
        System.out.println("Non-overlapping intervals = " + ans);
    }
}
