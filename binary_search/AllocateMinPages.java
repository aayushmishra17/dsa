package binary_search;

import java.util.Arrays;

/*
    Given an array arr of integer numbers, ‘ar[i]’ represents the number of pages in the ‘i-th’ book.
    There are a ‘m’ number of students, and the task is to allocate all the books to the students.
    Allocate books in such a way that:
        Each student gets at least one book.
        Each book should be allocated to only one student.
        Book allocation should be in a contiguous manner.
    You have to allocate the book to ‘m’ students such that the maximum number of pages assigned to a student is minimum.
    If the allocation of books is not possible then return -1
 */
public class AllocateMinPages {
    public static void main(String[] args) {
        AllocateMinPages obj = new AllocateMinPages();
        int m1 = 2;
        int[] arr1 = new int[] { 12, 34, 67, 90 };
        obj.approach2(arr1, m1);

        int m2 = 4;
        int[] arr2 = new int[] { 25, 46, 28, 49, 24 };
        obj.approach2(arr2, m2);
    }
    /*
        Time- O(N) * O(sum(arr) - max(arr) + 1)
        Space- O(1)

        Search space - [ max(arr), sum(arr) ]
        The minimum number of pages we can take to allocate all books is equal to the maximum number of pages
        in a book.
        The maximum number of pages we can take would be the scenario where we have only one student in which case
        we'll need to allocate all the books to that single student which is equal to sum of all pages in all books.
     */
    private void approach1(int[] arr, int m) {
        int maxMinimumPagesRequired = -1;
        for(int i = Arrays.stream(arr).max().getAsInt(); i <= Arrays.stream(arr).sum(); i++) {
            if(countStudents(arr, i) == m) {
                maxMinimumPagesRequired = i;
                break;
            }
        }
        if(m > arr.length) {
            // No of student is more than available books.
            System.out.println("Can't allocate books.");
            return;
        }
        System.out.println("Arr = " + Arrays.toString(arr) + " m = " + m);
        System.out.println("Pages assigned = " + maxMinimumPagesRequired);
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private int countStudents(int[] arr, int maxPages) {
        int studentCount = 1;
        int availablePages = maxPages;
        for(int currPages : arr) {
            if(availablePages - currPages < 0) {
                studentCount++;
                availablePages = maxPages - currPages;
            } else {
                availablePages -= currPages;
            }
        }
        return studentCount;
    }
    /*
        Time - O(N) * O( log [sum(arr) - max(arr) + 1] )
        Space - O(1)
     */
    private void approach2(int[] arr, int m) {
        int low = Arrays.stream(arr).max().getAsInt();
        int high = Arrays.stream(arr).sum();
        int maxMinimumPagesRequired = -1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(countStudents(arr, mid) <= m) {
                // Need to reduce no of pages so that more students can be allocated.
                maxMinimumPagesRequired = mid;
                high = mid - 1;
            } else {
                // Need to increase no of pages do that fewer students can can be used to allocate all books.
                low = mid + 1;
            }
        }
        if(m > arr.length) {
            // No of student is more than available books.
            System.out.println("Can't allocate books.");
            return;
        }
        System.out.println("Arr = " + Arrays.toString(arr) + " m = " + m);
        System.out.println("Pages assigned = " + maxMinimumPagesRequired);
    }
}
