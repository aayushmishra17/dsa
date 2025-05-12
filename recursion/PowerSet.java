package recursion;

/*
    Find x ^ n => pow(x, n)
    Here, n can be -ve or +ve.
    x can be a decimal value.
 */
public class PowerSet {
    public static void main(String[] args) {
        int x = 2;
        int n = 10;
        System.out.println("X = " + x);
        System.out.println("N = " + n);

        PowerSet obj = new PowerSet();
        System.out.println("pow(x, n) = " + obj.approach2(x, n));
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private double approach1(double x, int n) {
        double ans = 1.0;
        long newN = n;
        if(n < 0) {
            newN *= -1;
        }
        for(int i = 0; i < n; i++) {
            ans *= x;
        }
        if(n < 0) {
            ans = 1 / ans;
        }
        return ans;
    }
    /*
        Time - O(log N)
        Space - O(1)

        Binary exponentiation method-
            2 ^ 10 can be written as (2 ^ 2) ^ 5
                If n is even we divide it by 2 and multiply x by x.
            4 ^ 5 can be written as 4 * (4 ^ 4)
                If n is odd, we multiply answer by x and reduce n by 1.
            We repeat this till n > 0.
     */
    private double approach2(double x, int n) {
        double ans = 1.0;
        /*
            Need to take newN as long because in case the n is -ve and has max -ve value possible
            then when we convert it to +ve it will overflow since +ve range for int is 1 less tha -ve range.
         */
        long newN = n;
        if(newN < 0) {
            newN *= -1; // If n is -ve change it to +ve.
        }
        while (newN > 0) {
            if(newN % 2 == 1) {
                ans *= x;
                newN -= 1;

            } else {
                x *= x;
                newN /= 2;
            }
        }
        if(n < 0) {
            ans = 1 / ans;
        }
        return ans;
    }
}
