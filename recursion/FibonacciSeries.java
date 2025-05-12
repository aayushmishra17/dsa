package recursion;

/*
    Print N elements of the fibonacci series.
    0 1 1 2 3 5 8 13...
 */
public class FibonacciSeries {
    public static void main(String[] args) {
        int n = 7;
        System.out.println("N = " + n);
        System.out.println("Fibonacci series - ");
        FibonacciSeries obj = new FibonacciSeries();
        for(int i = 0; i < n + 1; i++) {
            int currentTerm = obj.getNthTermOfFibonacciSeries(i);
            System.out.print(currentTerm + " ");
        }
    }
    /*
        Time - O(2 * N)
        Space - O(N)[aux. stack space]
     */
    private int getNthTermOfFibonacciSeries(int n) {
        if(n <= 1) {
            return n;
        }
        int secondLast = getNthTermOfFibonacciSeries(n - 1);
        int last = getNthTermOfFibonacciSeries(n - 2);
        return secondLast + last;
    }
}
