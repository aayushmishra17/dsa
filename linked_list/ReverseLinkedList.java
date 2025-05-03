package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

/*
    Reverse a singly linked list iteratively and recursively.
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        int[] values = new int[] { 1, 2, 3, 4, 5 };
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(values);

        System.out.println("Original list:");
        LinkedListUtils.printSinglyLinkedList(head);

        ReverseLinkedList obj = new ReverseLinkedList();
        head = obj.iterativeApproach(head);
        //head = obj.recursiveApproach(head);

        System.out.println("Reversed list:");
        LinkedListUtils.printSinglyLinkedList(head);
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private SinglyLinkedListNode iterativeApproach(SinglyLinkedListNode head) {
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
    /*
        Time - O(N)
        Space - O(N)(recursive stack space)
     */
    private SinglyLinkedListNode recursiveApproach(SinglyLinkedListNode head) {
        if(head == null || head.get_nextNode() == null) {
            return head;
        }
        SinglyLinkedListNode newHead = recursiveApproach(head.get_nextNode());
        head.get_nextNode().set_nextNode(head);
        head.set_nextNode(null);
        return newHead;
    }
}
