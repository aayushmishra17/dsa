package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

/*
    We have to delete the Nth node from the end of
    the linked list.
 */
public class DeleteNthNodeFromEnd {
    public static void main(String[] args) {
        int[] values = new int[] { 1, 2, 3, 4, 5 };
        int n = 2;
        if(n < 1 || n > values.length) {
            System.out.println("Value of N must be between 1 and " + values.length);
            return;
        }
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(values);

        System.out.println("Given list:");
        LinkedListUtils.printSinglyLinkedList(head);

        DeleteNthNodeFromEnd obj = new DeleteNthNodeFromEnd();
        head = obj.approach2(head, n);

        System.out.println("List after deletion of " + n + "th node:");
        LinkedListUtils.printSinglyLinkedList(head);
    }
    /*
        Time - O(N) + O(N)
        Space - O(1)
     */
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head, int n) {
        if(head == null) {
            System.out.println("List is empty!");
            return head;
        }
        int count = 0;
        SinglyLinkedListNode currNode = head;
        while(currNode != null) {
            count ++;
            currNode = currNode.get_nextNode();
        }
        if(count == n) {
            // We need to delete the head node.
            head = head.get_nextNode();
        } else {
            currNode = head;
            int currPos = 1;
            while(currNode != null) {
                if(currPos == count - n) {
                    /*
                        We need to iterate to one node before the target node so
                        we can change the node links.
                        count - n + 1 is the target node.
                     */
                    currNode.set_nextNode(currNode.get_nextNode().get_nextNode());
                    break;
                }
                currNode = currNode.get_nextNode();
                currPos++;
            }
        }
        return head;
    }
    /*
        Time - O(N)
        Space - O(1)

        We'll use the fast and slow pointer approach.
        We'll first move the fast pointer N steps, then we'll move both
        fast and slow pointer simultaneously one step.
        We stop when fast pointer reaches the last node or becomes null.
        So fast pointer would be placed at the end and slow would be
        standing at count - N position.

        1 2 3 4 5 6 7 8 ;N = 2
            F
        S
          S   F
            S   F
              S   F
                S   F
                  S   F
     */
    private SinglyLinkedListNode approach2(SinglyLinkedListNode head, int n) {
        if(head == null) {
            System.out.println("List is empty!");
            return head;
        }
        SinglyLinkedListNode fastPointer = head;
        for(int i = 0; i < n && fastPointer != null; i++) {
            fastPointer = fastPointer.get_nextNode();
        }
        if(fastPointer == null) {
            // We have to remove the head node.
            return head.get_nextNode();
        }
        SinglyLinkedListNode slowPointer = head;
        while(fastPointer != null && fastPointer.get_nextNode() != null) {
            slowPointer = slowPointer.get_nextNode();
            fastPointer = fastPointer.get_nextNode();
        }
        slowPointer.set_nextNode(slowPointer.get_nextNode().get_nextNode());
        return head;
    }
}
