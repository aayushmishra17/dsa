package arrays;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Print all divisors of a number.
 */
public class PrintAllDivisors {
    public static void main(String[] args) {
        PrintAllDivisors obj = new PrintAllDivisors();
        int n = 36;
        obj.printAllDivisors(n);
    }
    /*
     * Time - O(sqrt N)
     * Space - O(1)
     * 
     * Only need to iterate till the square root of the number.
     * 1    *   36 / 1    = 36
     * 2    *   18 / 2    = 36
     * 3    *   12 / 3    = 36
     * 4    *   9 / 4     = 36
     * 6    *   6       = 36 <- sqrt(36) <- n / i == i
     * 9    *   4 / 9     = 36
     * 12   *   3 / 12    = 36
     * 18   *   2 / 18    = 36
     * 36   *   1 / 36    = 36
     * 
     */
    private void printAllDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        for(int i = 1; i * i <= n; i++) {
            if(n % i == 0) {
                divisors.add(i);
                if(n / i != i) {
                    divisors.add(n / i);
                }
            }
        }
        divisors.sort(Comparator.naturalOrder());
        System.out.println("Divisors = " + divisors);
    }
}
