package arrays;

/*
    Find the Greatest Common Divisor(GCD)/ Highest Common Factor(HCF) of
    two numbers.
 */
public class GcdOfTwoNumbers {
    public static void main(String[] args) {
        GcdOfTwoNumbers obj = new GcdOfTwoNumbers();
        int n1 = 52;
        int n2 = 10;
        obj.approach1(n1, n2);
        obj.approach2(n1, n2);
    }
    /*
        Time - O(min(n1, n2))
        Space - O(1)
     */
    private void approach1(int n1, int n2) {
        int gcd = 1;
        for(int i = Math.min(n1, n2); i >= 1; i--) {
            if(n1 % i == 0 && n2 % i == 0) {
                gcd = i;
                break;
            }
        }
        System.out.println("n1 = " + n1 + " n2 = " + n2);
        System.out.println("G.C.D. = " + gcd);
    }
    /*
        Time - O(log (min(n1, n2)))
        Space - O(1)

        Euclidean algo : if n1 > n2, gcd(n1, n2) = gcd(n1-n2, n2) = gcd(n1%n2, n2)
     */
    private void approach2(int n1, int n2) {
        int num1 = n1;
        int num2 = n2;
        while(num1 > 0 && num2 > 0) {
            if(num1 > num2) {
                num1 = num1 % num2;
            } else {
                num2 = num2 % num1;
            }
        }
        int gcd = num1 != 0 ? num1 : num2;
        System.out.println("n1 = " + n1 + " n2 = " + n2);
        System.out.println("G.C.D. = " + gcd);
    }
}
