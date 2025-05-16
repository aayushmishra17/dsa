package recursion;

import java.util.LinkedList;

/*
    Reverse the elements of a stack using recursion.
    We cannot use any extra space/ data structure to store the
    given stack elements.
 */
public class ReverseStackWithRecursion {
    public static void main(String[] args) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(6);
        stack.push(7);
        System.out.println("Stack = " + stack);
        ReverseStackWithRecursion obj = new ReverseStackWithRecursion();
        obj.reverseStack(stack);
        System.out.println("Reversed stack = " + stack);
    }
    /*
        Time - O(N * N)
        Space - O(N)[aux. stack space]
     */
    private void reverseStack(LinkedList<Integer> stack) {
        if (stack.isEmpty() == false) {
            int topElem = stack.poll();
            reverseStack(stack);
            insertAtBottom(stack, topElem);
        }
    }
    /*
        Time - O(N)
        Space - O(N)[aux. stack space]
     */
    private void insertAtBottom(LinkedList<Integer> stack, int item) {
        if(stack.isEmpty()) {
            stack.push(item);
        } else {
            int topElement = stack.poll();
            insertAtBottom(stack, item);
            stack.push(topElement);
        }
    }
}
