package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

/*
    Given the head of a singly linked list we need to remove the middle node of the list.
 */
public class DeleteMiddleNode {
    public static void main(String[] args) {
        int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(values);

        System.out.println("Given list:");
        LinkedListUtils.printSinglyLinkedList(head);

        DeleteMiddleNode obj = new DeleteMiddleNode();
        head = obj.approach2(head);

        System.out.println("List after deletion of middle node: ");
        LinkedListUtils.printSinglyLinkedList(head);
    }
    /*
        Time - O(N) + O(N/2)
        Space - O(1)

        1 2 3 4 5   N = 5 Middle node = N/2 + 1 = 3
        1 2 3 4 5 6 N = 6 Middle node = N/2 + 1 = 4
        To remove the node we would want to stop at one node before the middle node.
        So, we can use N/2 as the node where we stop.
     */
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head) {
        if (head == null || head.get_nextNode() == null) {
            return null;
        }
        SinglyLinkedListNode currNode = head;
        int n = 0;
        while (currNode != null) {
            n++;
            currNode = currNode.get_nextNode();
        }
        currNode = head;
        int currPosition = 1;
        while (currNode != null) {
            if(currPosition == n/2) {
                currNode.set_nextNode(currNode.get_nextNode().get_nextNode());
                break;
            }
            currPosition++;
            currNode = currNode.get_nextNode();
        }
        return head;
    }
    /*
        Time - O(N/2)
        Space - O(1)

        We use slow-fast pointer approach to identify the middle node,
        however since we want to stop at 1 node before the middle node, we'll
        first move the fast pointer ahead by 2 nodes and then start moving both
        the pointers.
     */
    private SinglyLinkedListNode approach2(SinglyLinkedListNode head) {
        if(head == null || head.get_nextNode() == null) {
            return null;
        }
        SinglyLinkedListNode slowPointer = head;
        SinglyLinkedListNode fastPointer = head;
        fastPointer = fastPointer.get_nextNode().get_nextNode();
        while(fastPointer != null && fastPointer.get_nextNode() != null) {
            slowPointer = slowPointer.get_nextNode();
            fastPointer = fastPointer.get_nextNode().get_nextNode();
        }
        slowPointer.set_nextNode(slowPointer.get_nextNode().get_nextNode());
        return head;
    }
}
