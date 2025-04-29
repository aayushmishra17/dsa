package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;

public class DeleteKthNode {
    public static void main(String[] args) {
        int[] values = new int[] { 10, 20, 30, 40, 50 };
        int k = 5;
        SinglyLinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(values);

        System.out.println("Linked list before deleting " + k + "th node");
        LinkedListUtils.printSinglyLinkedList(head);

        DeleteKthNode obj = new DeleteKthNode();
        head = obj.approach1(head, k);

        System.out.println("Linked list after deleting " + k + "th node");
        LinkedListUtils.printSinglyLinkedList(head);
    }
    /*
        Deleting the Kth node, the Kth node can be the 1st or the last node or
        any other node in between.
        Return the head of the linked list.

        Time - O(N)
        Space - O(1)
     */
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head, int k) {
        if(head == null) {
            System.out.println("Empty list.");
            return head;
        } else if(k == 1) {
            head = head.get_nextNode();
            System.out.println("Removed first node.");
            return head;
        }

        SinglyLinkedListNode prev = head;
        SinglyLinkedListNode curr = head;
        int currPosition = 1;
        while(curr != null) {
            if(currPosition == k) {
                prev.set_nextNode(curr.get_nextNode());
                System.out.println("Removed " + k + "th node.");
                break;
            }
            prev = curr;
            curr = curr.get_nextNode();
            currPosition++;
        }

        return head;
    }
}
