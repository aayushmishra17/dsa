package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

import java.util.LinkedList;

/*
    We are given a linked list which represents a number.
    We need to check if this number is a palindrome or not.
 */
public class CheckForPalindrome {
    public static void main(String[] args) {
        int[] values = new int[] { 1, 2, 3, 2, 1 };
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(values);

        System.out.println("List: ");
        LinkedListUtils.printSinglyLinkedList(head);

        CheckForPalindrome obj = new CheckForPalindrome();
        boolean isPalindrome = obj.approach2(head);
        if(isPalindrome) {
            System.out.println("Palindrome!");
        } else {
            System.out.println("Not a palindrome.");
        }
    }
    /*
        Time - O(N) + O(N)
        Space - O(N)

        We use a stack to get all elements of the list in reverse order
        and, then we compare if the reverse sequence and original sequence match.
     */
    private boolean approach1(SinglyLinkedListNode head) {
        if(head == null) {
            System.out.println("List is empty!");
            return false;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        SinglyLinkedListNode currNode = head;
        while(currNode != null) {
            stack.addFirst(currNode.get_data());
            currNode = currNode.get_nextNode();
        }
        currNode = head;
        while(currNode != null) {
            int value = stack.poll();
            if(value != currNode.get_data()) {
                return false;
            }
            currNode = currNode.get_nextNode();
        }
        return true;
    }
    /*
        Time - O(N/2)[fast-slow pointer to find middle] + O(N/2)[reverse half of list] +
               O(N/2)[iterate over both halves simultaneously] + O(N/2)[reverse half of the list]
        Space - O(1)

        In order to identify a palindrome we need to figure out the middle
        element and then the first half and second half value should exactly match.
        We use slow and fast pointer approach to find the middle element.
        For odd number of nodes we have only one middle element.
        For even number of nodes we take the first middle element as the end
        of the first half of the list.
        We reverse the second half of the list and then compare the first and second halves.
     */
    private boolean approach2(SinglyLinkedListNode head) {
        if(head == null) {
            System.out.println("List is empty!");
            return false;
        }
        SinglyLinkedListNode slowPointer = head;
        SinglyLinkedListNode fastPointer = head;
        while(fastPointer.get_nextNode() != null && fastPointer.get_nextNode().get_nextNode() != null) {
            slowPointer = slowPointer.get_nextNode();
            fastPointer = fastPointer.get_nextNode().get_nextNode();
        }

        SinglyLinkedListNode newHead = reverseLinkList(slowPointer.get_nextNode());
        SinglyLinkedListNode first = head;
        SinglyLinkedListNode second = newHead;

        while(second != null) {
            if(first.get_data() != second.get_data()) {
                reverseLinkList(newHead);
                return false;
            }
            first = first.get_nextNode();
            second = second.get_nextNode();
        }

        reverseLinkList(newHead);

        return true;
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private SinglyLinkedListNode reverseLinkList(SinglyLinkedListNode head) {
        if(head == null || head.get_nextNode() == null) {
            return head;
        }
        SinglyLinkedListNode prevNode = null;
        SinglyLinkedListNode currNode = head;
        SinglyLinkedListNode nextNode;
        while(currNode != null) {
            nextNode = currNode.get_nextNode();
            currNode.set_nextNode(prevNode);
            prevNode = currNode;
            currNode = nextNode;
        }
        return prevNode;
    }
}
