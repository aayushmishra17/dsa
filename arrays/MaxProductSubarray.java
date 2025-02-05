package arrays;

import java.util.Arrays;

/*
 * Given an array that contains both negative and positive integers, 
 * find the maximum product subarray.
 */
public class MaxProductSubarray {
    public static void main(String[] args) {
        MaxProductSubarray obj = new MaxProductSubarray();
        int[] arr = new int[] { 1, 2, 3, 4, 5, 0 };
        // int[] arr = new int[] { 1, 2, -3, 0, -4, -5 };
        obj.approach1(arr);
        obj.approach2(arr);
        obj.approach3(arr);
        obj.approach4(arr);
    }
    /*
     * Time - O(N * N * N)
     * Space - O(1)
     */
    private void approach1(int[] arr) {
        int n = arr.length;
        int maxProduct = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int currProduct = 1;
                for(int k = i; k <= j; k++) {
                    currProduct *= arr[k];
                }
                maxProduct = Math.max(maxProduct, currProduct);
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Max product = " + maxProduct);
    }
    /*
     * Time - O(N * N)
     * Space - O(1)
     */
    private void approach2(int[] arr) {
        int n = arr.length;
        int maxProduct = 1;
        for(int i = 0; i < n; i++) {
            int currProduct = 1;
            for(int j = 0; j < n; j++) {
                currProduct *= arr[j];
                maxProduct = Math.max(maxProduct, currProduct);
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Max product = " + maxProduct);
    }
    /*
     * Time - O(N)
     * Space - O(1)
     * 
     * Based on observation-
     * 1. All +ve elements
     * 2. Even no. of -ve and even/odd no. of +ve elements
     * 3. Odd no. of -ve and even/odd no. of +ve elements
     *      For case 3. we can't take all -ve elements as there are odd no. of -ve elements so that 
     *      would give us a -ve product. So we need to eliminate any one -ve no. and then find the maximum 
     *      product possible.
     *      Whenever we remove one element that would leave us with a prefix arr and a suffix arr.
     * 4. If array has 0s.
     *      If the array has 0s then we know that the product would come out to be 0, so we need to 
     *      avoid considering subarrays with 0s in them. So if we have 0s then we need to split the arrays 
     *      into subarrays such that 0s are not included in the subarrays.
     */
    private void approach3(int[] arr) {
        int n = arr.length;
        long maxProduct = 1;
        long prefixProduct = 1;
        long suffixProduct = 1;
        for(int i = 0; i < n; i++) {
            if(prefixProduct == 0) {
                prefixProduct = 1;
            }
            if(suffixProduct == 0) {
                suffixProduct = 1;
            }
            prefixProduct *= arr[i];
            suffixProduct *= arr[n - 1 - i];
            maxProduct = Math.max(maxProduct, Math.max(prefixProduct, suffixProduct));
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Max product = " + maxProduct);
    }
    /*
     * Time - O(N)
     * Space - O(1)
     * 
     * Can also be solved using modified Kadane's algo.
     */
    private void approach4(int[] arr) {
        int prod1 = arr[0];
        int prod2 = arr[0]; 
        int result = arr[0];
    
        for(int i=1;i<arr.length;i++) {
            int temp = Math.max(arr[i],Math.max(prod1*arr[i],prod2*arr[i]));
            prod2 = Math.min(arr[i],Math.min(prod1*arr[i],prod2*arr[i]));
            prod1 = temp;
            
            result = Math.max(result,prod1);
        }

        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Max product = " + result);
    }
}

