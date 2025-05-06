package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

/*
 * Given the head of a singly linked list and a value K, we need to 
 * rotate the linked list by K nodes.
 * E.g.: 1 -> 2 -> 3 -> 4 -> 5 K = 2
 *       5 -> 1 -> 2 -> 3 -> 4; 1 rotation
 *       4 -> 5 -> 1 -> 2 -> 3; 2 rotation
 */
public class RotateLinkedListByKNodes {
    public static void main(String[] args) {
        int[] values = new int[] { 1, 2, 3, 4, 5 };
        int k = 2;

        LinkedListBuilder build = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) build.build(values);

        System.out.println("Given list:");
        LinkedListUtils.printSinglyLinkedList(head);
        System.out.println("K = " + k);

        RotateLinkedListByKNodes obj = new RotateLinkedListByKNodes();
        head = obj.approach2(head, k);

        System.out.println("Rotated list:");
        LinkedListUtils.printSinglyLinkedList(head);
    }
    /*
     * Time - K * O(N)[Traverse to 2nd last node]
     * Space - O(1)
     * 
     * Steps - 
     *  1. Traverse to the 2nd last node.
     *  2. Store the last node and make the 2nd last node point to null.
     *  3. Make the stored node point to head and update head.
     */
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head, int k) {
        if(head == null || head.get_nextNode() == null) {
            return head;
        }
        
        while(k-- > 0) {
            SinglyLinkedListNode secondLastNode = head;
            while(secondLastNode.get_nextNode().get_nextNode() != null) {
                secondLastNode = secondLastNode.get_nextNode();
            }
    
            SinglyLinkedListNode lastNode = secondLastNode.get_nextNode();
    
            secondLastNode.set_nextNode(null);
            lastNode.set_nextNode(head);
            head = lastNode;
        }

        return head;
    }
    /*
     * Time - O(N)[Find the length of list] + O(N)[Traverse to the length - k th node, if k=1 then we traverse almost to the end]
     * Space - O(1)
     * 
     * If length of the list is a multiple of K then the final K rotated list would be exactly the 
     * same as the original list, so in this case we don't need to do anything.
     * 
     * We'll find the length of the list.
     * 
     * We traverse to the tail node and make it point to the head node.
     * 
     * We'll traverse to the Kth node, standing on the Kth node we know this will be the new tail node 
     * so we modify its next to point to null.
     */
    private SinglyLinkedListNode approach2(SinglyLinkedListNode head, int k) {
        if(head == null || head.get_nextNode() == null) {
            return head;
        }
        SinglyLinkedListNode tailNode = head;
        int length = 1;
        while (tailNode.get_nextNode() != null) {
            length++;
            tailNode = tailNode.get_nextNode();
        }
        /*
         * If length of the list is a multiple of K then simply return current head.
         */
        if(length % k == 0) {
            return head;
        }
        k = k % length; // E.g. length = 5, k = 13 then 13 % 5 = 3; we only need to rotate by 3 instead of 13 times.

        tailNode.set_nextNode(head);

        int newTailNodePosition = length - k - 1;
        SinglyLinkedListNode newTailNode = head;
        while(newTailNodePosition-- > 0) {
            newTailNode = newTailNode.get_nextNode();
        }
        SinglyLinkedListNode newHead = newTailNode.get_nextNode();
        newTailNode.set_nextNode(null);

        return newHead;
    }
}
