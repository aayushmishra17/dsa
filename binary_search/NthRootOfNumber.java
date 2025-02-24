package binary_search;

/*
 * Given two numbers N and M, find the Nth root of M. 
 * The nth root of a number M is defined as a number X when raised to the power N equals M. 
 * If the 'nth root is not an integer, return -1.
 */
public class NthRootOfNumber {
    public static void main(String[] args) {
        NthRootOfNumber obj = new NthRootOfNumber();
        int n = 3;
        int m = 27;
        obj.findNthRoot(n, m);
    }
    /*
     * Time - O(log m) * O(n)[to calculate the nth power](this can be log n if we use exponentiation method to calculate power)
     * Space - O(1)
     */
    private void findNthRoot(int n, int m) {
        int low = 1;
        int high = m;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int ntimes = 1;
            for(int i = 1; i <= n; i++) {
                ntimes *= mid;
                if(ntimes > m) {
                    /*
                     * Don't need to multiply further as the product has already crossed m 
                     * and multiplying further may lead to overflow for large numbers.
                     */
                    break;
                }
            }
            if(ntimes == m) {
                ans = mid;
                break;
            } else if(ntimes < m) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if(ans == -1) {
            System.out.println(n + "th root of " + m + " doesn't exist.");
        } else {
            System.out.println(n + "th root of " + m + " = " + ans);
        }
    }
}
