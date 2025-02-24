package binary_search;

/*
 * You are given a positive integer n. Your task is to find and return its square root. 
 * If ‘n’ is not a perfect square, then return the floor value of 'sqrt(n)'.
 * Note: The question explicitly states that if the given number, n, is not a perfect square, 
 * our objective is to find the maximum number, x, such that x squared is less than or equal to n (x*x <= n). 
 * In other words, we need to determine the floor value of the square root of n.
 */
public class SquareRoot {
    public static void main(String[] args) {
        SquareRoot obj = new SquareRoot();
        int num = 51;
        obj.findSquareRoot(num);
    }
    /*
     * Time - O(log N)
     * Space - O(1)
     */
    private void findSquareRoot(int num) {
        int low = 1;
        int high = num;
        int ans = 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(mid * mid <= num) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Number = " + num);
        System.out.println("Square root = " + ans);
    }
}
