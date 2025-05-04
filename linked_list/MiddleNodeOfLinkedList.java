package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

/*
    Find the middle node of given singly linked list.
    For odd number of nodes we have only one middle node.
    For even number of nodes we'll return the second middle node.
 */
public class MiddleNodeOfLinkedList {
    public static void main(String[] args) {
        int[] values = new int[] { 1, 2, 3, 4, 5, 6 };
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(values);

        System.out.println("Given list:");
        LinkedListUtils.printSinglyLinkedList(head);

        MiddleNodeOfLinkedList obj = new MiddleNodeOfLinkedList();
        SinglyLinkedListNode middleNode = obj.approach2(head);
        if(middleNode == null) {
            System.out.println("No middle node exists!");
        } else {
            System.out.println("Middle node is - " + middleNode.get_data());
        }
    }
    /*
        Time - O(N) + O(N/2)
        Space - O(1)

        Find the length of the list.
        The middle node would be = n/2 + 1 th node.
     */
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head) {
        if(head == null) {
            System.out.println("List is empty!");
            return null;
        }
        SinglyLinkedListNode currNode = head;
        int n = 0;
        while (currNode != null) {
            n++;
            currNode = currNode.get_nextNode();
        }
        int middlePosition = (n / 2) + 1;
        int currPosition = 0;
        currNode = head;
        while (currNode != null) {
            currPosition++;
            if(currPosition == middlePosition) {
                break;
            }
            currNode = currNode.get_nextNode();
        }
        return currNode;
    }
    /*
        Time - O(N/2)
        Space - O(1)

        Tortoise & Hare algo:
            We use slow and fast pointers, slow pointer moves 1 node at a time while the
            fast pointer moves 2 nodes at a time.
            We stop when fast pointer reaches the last node or, it becomes null.
     */
    private SinglyLinkedListNode approach2(SinglyLinkedListNode head) {
        if(head == null) {
            System.out.println("List is empty!");
            return null;
        }
        SinglyLinkedListNode slowPointer = head;
        SinglyLinkedListNode fastPointer = head;
        while(fastPointer != null && fastPointer.get_nextNode() != null) {
            slowPointer = slowPointer.get_nextNode();
            fastPointer = fastPointer.get_nextNode().get_nextNode();
        }

        return slowPointer;
    }
}
