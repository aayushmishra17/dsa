package arrays;

/*
    Check if the given array is sorted in non-descending order.
    Array may contain duplicate elements, and all its elements are positive.
 */
public class CheckIfArrayIsSortedAscending {
    public static void main(String[] args) {
        int[] arr = new int[] { 3, 3, 5, 6, 7, 7, 8, 9 };
        CheckIfArrayIsSortedAscending obj = new CheckIfArrayIsSortedAscending();
        System.out.println(obj.checkIfSorted(arr));
    }
    /*
        Time - O(n)
        Space - O(1)
     */
    private boolean checkIfSorted(int[] arr) {
        for(int itr = 1; itr < arr.length; itr++) {
            if(arr[itr] < arr[itr - 1]) {
                return false;
            }
        }
        return true;
    }
}
