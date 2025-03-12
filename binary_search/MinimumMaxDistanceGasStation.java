package binary_search;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * You are given a sorted array ‘arr’ of length ‘n’, which contains positive integer positions of ‘n’ gas stations 
 * on the X-axis. You are also given an integer ‘k’. You have to place 'k' new gas stations on the X-axis. 
 * You can place them anywhere on the non-negative side of the X-axis, even on non-integer positions. 
 * Let 'dist' be the maximum value of the distance between adjacent gas stations after adding k new gas stations. 
 * Find the minimum value of ‘dist’.
 */
public class MinimumMaxDistanceGasStation {
    public static void main(String[] args) {
        MinimumMaxDistanceGasStation obj = new MinimumMaxDistanceGasStation();
        int[] arr1 = new int[] { 1, 2, 3, 4, 5 };
        int k1 = 4;
        obj.approach3(arr1, k1);
        int[] arr2 = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int k2 = 1;
        obj.approach3(arr2, k2);
    }
    /*
     * Time - O(K * N) + O(N)
     * Space - O(N - 1)
     * 
     * Placing the new gas stations before the first gas station or after the last gas station won't 
     * reduce the distance between the gas station, so we must place new gas stations in between the existing stations.
     * Now, in order to minimize the distance b/t any two stations we must try place the new stations at equal distances.
     * Start taking one gas station at a time and place that station between two stations where the current distance is maximum.
     * Then finally iterate over all placed stations and find the max distance.
     */
    private void approach1(int[] arr, int k) {
        int n = arr.length;
        /*
         * howManyStations array represents how many gas stations were placed in the existing gap between any two stations.
         * For any given number of stations n, the number of gaps would always be n - 1;
         */
        int[] howManyStations = new int[n - 1];
        for(int gasStation = 1; gasStation <= k; gasStation++) {
            double maxSectionLength = -1;
            int maxSectionIndex = -1;
            for(int i = 0; i < n - 1; i++) {
                double currDistance = arr[i + 1] - arr[i];
                double eachSectionLength = currDistance / (howManyStations[i] + 1);
                if(eachSectionLength > maxSectionLength) {
                    maxSectionLength = eachSectionLength;
                    maxSectionIndex = i;
                }
            }
            /*
             * Place this gas station at index where distance is largest.
             */
            howManyStations[maxSectionIndex]++;
        }

        /*
         * Now iterate over all gas station and find the max distance.
         */
        double maxDistance = -1;
        for(int i = 0; i < n - 1; i++) {
            double distance = arr[i + 1] - arr[i];
            double sectionLength = distance / (howManyStations[i] + 1);
            maxDistance = Math.max(sectionLength, maxDistance);
        }
        System.out.println("Arr = " + Arrays.toString(arr) + " K = " + k);
        System.out.println("Minimum max distance between any two gas stations = " + maxDistance);
    }
    /*
     * Time - O(N * log N)[Initialing the distances] + O(K * log N)[placing new gas stations]
     * Space - O(N - 1) + O(N - 1)
     * 
     * We can reduce the O(N) time take to find the max length section in approach1 by storing 
     * all the distance in a priority queue.
     */
    private void approach2(int[] arr, int k) {
        int n = arr.length;
        int[] howManyStations = new int[n - 1];
        class Pair {
            double first;
            int second;
    
            Pair(double first, int second) {
                this.first = first;
                this.second = second;
            }
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>( (a, b) -> Double.compare(b.first, a.first) );
        for(int i = 0; i < n - 1; i++) {
            pq.add(new Pair(arr[i + 1] - arr[i], i));
        }
        for(int gasStation = 1; gasStation <= k; gasStation++) {
            Pair maxDistPair = pq.poll();
            int maxSectionIndex = maxDistPair.second;
            
            howManyStations[maxSectionIndex]++;

            double distance = arr[maxSectionIndex + 1] - arr[maxSectionIndex];
            double newSectionLength = distance / (howManyStations[maxSectionIndex] + 1);
            pq.add(new Pair(newSectionLength, maxSectionIndex));
        }
        System.out.println("Arr = " + Arrays.toString(arr) + " K = " + k);
        System.out.println("Minimum max distance between any two gas stations = " + pq.peek().first);
    }
    /*
     * Time - O(N log N) + O(N)
     * Space - O(1)
     * 
     * Apply binary search to find the maximum distance.
     * Search space - [0, max distance b/t existing gas stations]
     * 0 if we place the new gas station at the coordinates of the existing gas stations.
     * The max would be the existing max distance because since we want to reduce the distance b/t gas stations we won't 
     * increase the existing max distance.
     */
    private void approach3(int[] arr, int k) {
        int n = arr.length;
        double low = 0;
        double high = 0;
        // Find max existing distance.
        for(int i = 0; i < n - 1; i++) {
            high = Math.max(high, arr[i + 1] - arr[i]);
        }

        /*
         * Since we are working with fractional numbers we can't compare for equality 
         * as two numbers may differ slightly as some position after decimal.
         * Also, we can't increment/decrement mid by 1 since we would be skipping potential 
         * fractional min distances which would be less than 1. So we update low and high with the mid value.
         */
        double diff = 1e-6;
        while(high - low > diff) {
            double mid = low + (high - low) / 2;
            int count = numberOfGasStationsRequired(arr, mid);
            if(count > k) {
                low = mid;
            } else {
                high = mid;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr) + " K = " + k);
        System.out.println("Minimum max distance between any two gas stations = " + high);
    }
    /*
     * Time - O(N)
     * Space - O(1)
     */
    private int numberOfGasStationsRequired(int[] arr, double distance) {
        int count = 0;
        for(int i = 1; i < arr.length; i++) {
            int numberInBetween = (int)((arr[i] - arr[i - 1]) / distance);
            /*
             * In case of exact multiple we need to substract 1.
             * E.g. : distance = 0.5 and section length b/t two stations is 1 then
             * section length / distance = 2 but actually we can place only 1 station in between.
             * 
             * For other case we take the lower bound.
             * E.g.: 1 / 0.4 = 2.5... = 2
             *       1 / 0.6 = 1.66666 = 1
             */
            if((arr[i] - arr[i - 1]) / distance == numberInBetween * distance) {
                numberInBetween--;
            }
            count += numberInBetween;
        }
        return count;
    }
}
