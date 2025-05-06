package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
    Given the head node of a linked list we need to sort the linked list
    and return the head of the sorted list.
 */
public class SortGivenLinkedList {
    public static void main(String[] args) {
        int[] values = new int[] { 3, 4, 2, 1, 5, 6 };
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(values);

        System.out.println("Given list:");
        LinkedListUtils.printSinglyLinkedList(head);

        SortGivenLinkedList obj = new SortGivenLinkedList();
        head = obj.approach2(head);

        System.out.println("Sorted list:");
        LinkedListUtils.printSinglyLinkedList(head);
    }
    /*
        Time - O(N) + O(N logN) + O(N)
        Space - O(N)[bucket] + O(N)[for creating a new list]
     */
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head) {
        SinglyLinkedListNode temp = head;
        List<Integer> bucket = new ArrayList<>();
        while (temp != null) { // O(N)
            bucket.add(temp.get_data());
            temp = temp.get_nextNode();
        }
        bucket.sort(Comparator.naturalOrder()); // O(N logN)
        int[] values = bucket.stream().mapToInt(Integer::intValue).toArray();
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        head = (SinglyLinkedListNode) builder.build(values); // O(N)

        return head;
    }
    /*
        Time - O( (N + N/2) * log N)
        Space - O(1)

        We'll use merge sort to change the links of the given list itself.
     */
    private SinglyLinkedListNode approach2(SinglyLinkedListNode head) {
        if(head == null || head.get_nextNode() == null) {
            return head;
        }
        SinglyLinkedListNode middleNode = getMiddleNode(head);
        SinglyLinkedListNode leftHead = head;
        SinglyLinkedListNode rightHead = middleNode.get_nextNode();
        middleNode.set_nextNode(null);

        leftHead = approach2(leftHead);
        rightHead = approach2(rightHead);
        return mergeTwoSortedLists(leftHead, rightHead);
    }
    /*
        Time - O(N/2)
        Space - O(1)
     */
    private SinglyLinkedListNode getMiddleNode(SinglyLinkedListNode head) {
        if(head == null || head.get_nextNode() == null) {
            return head;
        }
        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = head.get_nextNode();
        while(fast != null && fast.get_nextNode() != null) {
            slow = slow.get_nextNode();
            fast = fast.get_nextNode().get_nextNode();
        }

        return slow;
    }
    /*
        Time - O(N1 + N2)
        Space - O(1)
     */
    private SinglyLinkedListNode mergeTwoSortedLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if(head1 != null && head2 == null) {
            return head1;
        } else if(head1 == null && head2 != null) {
            return head2;
        } else if(head1 == null && head2 == null) {
            return null;
        }
        SinglyLinkedListNode temp1 = head1;
        SinglyLinkedListNode temp2 = head2;
        SinglyLinkedListNode dummyNode = new SinglyLinkedListNode(-1);
        SinglyLinkedListNode temp3 = dummyNode;

        while (temp1 != null && temp2 != null) {
            if(temp1.get_data() <= temp2.get_data()) {
                temp3.set_nextNode(temp1);
                temp3 = temp1;
                temp1 = temp1.get_nextNode();
            } else {
                temp3.set_nextNode(temp2);
                temp3 = temp2;
                temp2 = temp2.get_nextNode();
            }
        }
        while(temp1 != null) {
            temp3.set_nextNode(temp1);;
            temp3 = temp1;
            temp1 = temp1.get_nextNode();
        }
        while (temp2 != null) {
            temp3.set_nextNode(temp2);
            temp3 = temp2;
            temp2 = temp2.get_nextNode();
        }

        return dummyNode.get_nextNode();
    }
}
