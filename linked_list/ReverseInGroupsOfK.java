package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

/*
 * Given the head of a singly linked list and a value K, we need to reverse all nodes in groups of K.
 * E.g.:            1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10; K = 3
 * Modified list:   3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 9 -> 8 -> 7 -> 10
 */
public class ReverseInGroupsOfK {
    public static void main(String[] args) {
        int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int k = 3;

        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(values);

        System.out.println("Given list:");
        LinkedListUtils.printSinglyLinkedList(head);
        System.out.println("K = " + k);

        ReverseInGroupsOfK obj = new ReverseInGroupsOfK();
        head = obj.approach1(head, k);

        System.out.println("Modified list:");
        LinkedListUtils.printSinglyLinkedList(head);
    }
    /*
     * Time - O(N)[Find kth node] + O(N)[Reverse list]
     * Space - O(1)
     */
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head, int k) {
        SinglyLinkedListNode temp = head;
        SinglyLinkedListNode prevNode = null;
        while(temp != null) {
            SinglyLinkedListNode kthNode = temp;
            int currPos = 1;
            while(currPos < k && kthNode != null) {
                currPos++;
                kthNode = kthNode.get_nextNode();
            }
            if(kthNode == null) {
                /*
                 * We don't have any remaining group of size K so we stop iterating.
                 * Before we return, we need to set the next node of the last node of previous group 
                 * to the current node.
                 */
                if(prevNode != null) { // prevNode can be null if entire length of list is < k.
                    prevNode.set_nextNode(temp);
                }
                break;
            }
            SinglyLinkedListNode nextNode = kthNode.get_nextNode();
            kthNode.set_nextNode(null);
            
            SinglyLinkedListNode newHead = reverseList(temp);
            if(temp == head) {
                /*
                * In case of 1st group after reversing the nodes, head would get changed.
                * So we need to modify head.
                */
                head = newHead;
            } else {
                prevNode.set_nextNode(kthNode);
            }

            prevNode = temp;
            temp = nextNode;
        }
        return head;
    }
    /*
     * Time - O(N)
     * SPace - O(1)
     */
    private SinglyLinkedListNode reverseList(SinglyLinkedListNode head) {
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
