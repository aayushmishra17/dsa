package binary_search;

import java.util.Arrays;

/*
    You are given an array 'arr' of size 'n' which denotes the position of stalls.
    You are also given an integer 'k' which denotes the number of aggressive cows.
    You are given the task of assigning stalls to 'k' cows such that the minimum
    distance between any two of them is the maximum possible.
    Find the maximum possible minimum distance.
 */
public class AggressiveCows {
    public static void main(String[] args) {
        AggressiveCows obj = new AggressiveCows();
        int[] arr = new int[] { 0, 3, 4, 7, 10, 9 };
        int k = 4;
        obj.approach1(arr, k);
        obj.approach2(arr, k);
    }
    /*
        Time - O(n log n)[sort] + O(max(arr) - min(arr)) * O(N)
        Space - O(1)

        First we need to sort the arr.

        The minimum distance b/t two cows can be 1, but be want to
        maximize this minimum distance b/t any two cows. So, we start with
        maintaining a distance of 1 and check if that is feasible, then we keep
        incrementing this distance and checking if it is feasible. The moment it is not
        possible with current distance then we can stop iterating. The range of possible
        distance would be [1, (max - min)]
     */
    private void approach1(int[] arr, int k) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        int max = Arrays.stream(newArr).max().getAsInt();
        int min = Arrays.stream(newArr).min().getAsInt();
        int maxMinDistance = 1;
        for(int i = 1; i <= max - min; i++) {
            if(canPlace(newArr, k, i)) {
                continue;
            } else {
                maxMinDistance = i - 1;
                break;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("No. of aggressive cows, k = " + k);
        System.out.println("Max min. distance b/t any two cows possible = " + maxMinDistance);
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private boolean canPlace(int[] arr, int k, int distanceToMaintain) {
        int cowsPlaced = 1;
        int positionOfPreviousCowStall = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] - positionOfPreviousCowStall >= distanceToMaintain) {
                cowsPlaced++;
                positionOfPreviousCowStall = arr[i];
            }
            if(cowsPlaced >= k) {
                return true;
            }
        }
        return false;
    }
    /*
        Time - O(n log n)[sort] + O( log ( max(arr) - min(arr) ) ) * O(N)
        Space - O(1)
     */
    private void approach2(int[] arr, int k) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        int max = newArr[newArr.length - 1];
        int min = newArr[0];
        int maxMinDistance = 1;
        int low = 1;
        int high = max - min;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(canPlace(newArr, k, mid)) {
                maxMinDistance = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("No. of aggressive cows, k = " + k);
        System.out.println("Max min. distance b/t any two cows possible = " + maxMinDistance);
    }
}
