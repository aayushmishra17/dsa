package binary_search;

import java.util.Arrays;

/*
 * A monkey is given ‘n’ piles of bananas, whereas the 'ith' pile has ‘a[i]’ bananas. 
 * An integer ‘h’ is also given, which denotes the time (in hours) for all the bananas to be eaten. 
 * Each hour, the monkey chooses a non-empty pile of bananas and eats ‘k’ bananas. 
 * If the pile contains less than ‘k’ bananas, then the monkey consumes all the bananas and 
 * won’t eat any more bananas in that hour. Find the minimum number of bananas ‘k’ to eat per hour 
 * so that the monkey can eat all the bananas within ‘h’ hours.
 * Koko cannot move to the next pile before finishing the current pile of bananas.
 */
public class KokoEatingBananas {
    public static void main(String[] args) {
        KokoEatingBananas obj = new KokoEatingBananas();
        int[] arr1 = new int[] { 7, 15, 6, 3 };
        int h1 = 8; // Total time within which all bananas should be eaten.
        int[] arr2 = new int[] { 25, 12, 8, 14, 19 };
        int h2 = 5;
        //obj.approach1(arr1, h1);
        obj.approach2(arr1, h1);
    }
    /*
     * Time - O(max(Arr))[to find the max element] * O(n)[to calculate the total hours required to finish all the piles of bananas]
     * Space - O(1)
     * 
     * We can observe that the minimum bananas monkey can eat is 1 and the maximum bananas monkey 
     * can eat is max(arr). So, we can iterate over [1, max(arr)] and check which value satisfies the criteria.
     */
    private void approach1(int[] arr, int h) {
        int maxBananas = findMax(arr);
        int ans = 1;
        for(int bananasToEat = 1; bananasToEat <= maxBananas; bananasToEat++) {
            int hrsRequired = findTotalHrsRequired(arr, bananasToEat);
            if(hrsRequired <= h) {
                ans = bananasToEat;
                break;
            }
        }
        System.out.println("Bananas pile = " + Arrays.toString(arr));
        System.out.println("Max hours allowed = " + h);
        System.out.println("Minimum bananas/hours required to eat = " + ans);
    }
    /*
     * Time - O(N)
     * Space - O(1)
     */
    private int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }
    /*
     * Time - O(N)
     * Space - O(1)
     */
    private int findTotalHrsRequired(int[] arr, int bananasPerHour) {
        int totalHrs = 0;
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            totalHrs += Math.ceil((double)arr[i]/ (double)bananasPerHour);
        }
        return totalHrs;
    }
    /*
     * Time - O(N) * O(log max(arr))
     * Space - O(1)
     * 
     * Use binary search.
     * The search space is [1, max(arr)]
     * For every mid value we can check if eating arr[mid] number of bananas satisfies 
     * the criteria. If yes, then since we need minimum number we can eliminate the right half.
     * If no, then we need to eat more bananas/hr so we can eliminate the left half.
     */
    private void approach2(int[] arr, int totalTime) {
        int low = 1;
        int high = findMax(arr);
        int ans = Integer.MAX_VALUE;
        while(low <= high) {
            int bananasPerHour = low + (high - low) / 2;
            int hrsRequired = findTotalHrsRequired(arr, bananasPerHour);
            if(hrsRequired <= totalTime) {
                ans = Math.min(ans, bananasPerHour);
                high = bananasPerHour - 1;
            } else {
                low = bananasPerHour + 1;
            }
        }
        System.out.println("Bananas pile = " + Arrays.toString(arr));
        System.out.println("Max hours allowed = " + totalTime);
        System.out.println("Minimum bananas/hours required to eat = " + ans);
    }
}
