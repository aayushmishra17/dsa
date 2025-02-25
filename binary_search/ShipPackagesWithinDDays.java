package binary_search;

import java.util.Arrays;

/*
    You are the owner of a Shipment company.
    You use conveyor belts to ship packages from one port to another.
    The packages must be shipped within 'd' days.
    The weights of the packages are given in an array 'of weights'.
    The packages are loaded on the conveyor belts every day in the same order as
    they appear in the array. The loaded weights must not exceed the maximum weight
    capacity of the ship. Find out the least-weight capacity so that you can ship all
    the packages within 'd' days.
 */
public class ShipPackagesWithinDDays {
    public static void main(String[] args) {
        ShipPackagesWithinDDays obj = new ShipPackagesWithinDDays();
        int[] weights = new int[] { 5, 4, 5, 2, 3, 4, 5, 6 };
        int d = 5;
        obj.approach1(weights, d);
    }
    /*
        Time - O(sum(arr) - max(arr) + 1) * O(N)
        Space - O(1)

        Search space would be [max(arr), sum(arr)]
     */
    private void approach1(int[] weights, int d) {
        int sum = Arrays.stream(weights).sum();
        int max = findMax(weights);
        int ans = -1;
        for(int capacity = max; capacity <= sum; capacity++) {
            int requiredDays = getRequiredDays(weights, capacity);
            if(requiredDays <= d) {
                ans = capacity;
                break;
            }
        }
        System.out.println("Arr = " + Arrays.toString(weights));
        System.out.println("Max. days to ship = " + d);
        System.out.println("Minimum days required = " + ans);
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private int findMax(int[] weights) {
        int n = weights.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, weights[i]);
        }
        return max;
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private int getRequiredDays(int[] weights, int capacity) {
        int n = weights.length;
        int daysRequired = 1;
        int currentLoad = 0;
        for(int i = 0; i < n; i++) {
            if(currentLoad + weights[i] > capacity) {
                daysRequired++;
                currentLoad = weights[i];
            } else {
                currentLoad += weights[i];
            }
        }
        return daysRequired;
    }
    /*
        Time - O( log ( sum(arr) + max(arr) - 1 ) ) * O(N)
        Space - O(1)
     */
    private void approach2(int[] weights, int d) {
        int low = findMax(weights);
        int high = Arrays.stream(weights).sum();
        int ans = -1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(getRequiredDays(weights, mid) <= d) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println("Arr = " + Arrays.toString(weights));
        System.out.println("Max. days to ship = " + d);
        System.out.println("Minimum days required = " + ans);
    }
}
