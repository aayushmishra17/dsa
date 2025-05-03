package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

import java.util.ArrayList;
import java.util.List;

/*
    Given a linked list we need to group the elements such that
    the odd numbers come first followed by even numbers.
    E.g. :   1 4 2 3 5 7 6
    Result : 1 3 5 7 2 4 6
 */
public class OddEvenLinkedList {
    public static void main(String[] args) {
        int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(values);

        System.out.println("Original List:");
        LinkedListUtils.printSinglyLinkedList(head);

        OddEvenLinkedList obj = new OddEvenLinkedList();
        head = obj.approach2(head);

        System.out.println("Modified List:");
        LinkedListUtils.printSinglyLinkedList(head);
    }
    /*
        Time - O(n/2) + O(n/2) + O(n)
        Space - O(n)

        We try to change the node values instead of changing the nodes themselves.
     */
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head) {
        if(head == null || head.get_nextNode() == null) {
            return head;
        }
        List<Integer> container = new ArrayList<>();

        // Iterate over the odd indices
        SinglyLinkedListNode oddPointer = head;
        while(oddPointer != null) {
            container.add(oddPointer.get_data());
            oddPointer = oddPointer.get_nextNode();
            if(oddPointer != null) {
                oddPointer = oddPointer.get_nextNode();
            }
        }

        // Iterate even the odd indices
        SinglyLinkedListNode evenPointer = head.get_nextNode();
        while(evenPointer != null) {
            container.add(evenPointer.get_data());
            evenPointer = evenPointer.get_nextNode();
            if(evenPointer != null) {
                evenPointer = evenPointer.get_nextNode();
            }
        }
        SinglyLinkedListNode currNode = head;
        for (Integer integer : container) {
            currNode.set_data(integer);
            currNode = currNode.get_nextNode();
        }
        return head;
    }
    /*
        Time - O(N)
        Space - O(1)

        We try to change the links of the nodes.
     */
    private SinglyLinkedListNode approach2(SinglyLinkedListNode head) {
        if(head == null || head.get_nextNode() == null) {
            return head;
        }
        SinglyLinkedListNode oddPointer = head;
        SinglyLinkedListNode evenPointer = head.get_nextNode();
        SinglyLinkedListNode evenHead = head.get_nextNode();
        while(evenPointer.get_nextNode() != null) {
            if(oddPointer.get_nextNode() != null) {
                oddPointer.set_nextNode(oddPointer.get_nextNode().get_nextNode());
            }
            if(evenPointer.get_nextNode() != null) {
                evenPointer.set_nextNode(evenPointer.get_nextNode().get_nextNode());
            }
            oddPointer = oddPointer.get_nextNode();
            evenPointer = evenPointer.get_nextNode();
        }
        oddPointer.set_nextNode(evenHead);
        return head;
    }
}
