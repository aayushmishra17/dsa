package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.DoublyLinkedListBuilder;
import linked_list.utils.classes.DoublyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

/*
 * Given the head of a doubly linked list, we need to remove all nodes with duplicate data 
 * and return the head of the new list.
 */
public class RemoveDuplicatesFromSortedDLL {
    public static void main(String[] args) {
        int[] values = new int[] { 1, 1, 1, 2, 3, 3, 4 };
        LinkedListBuilder builder = new DoublyLinkedListBuilder();
        DoublyLinkedListNode head = (DoublyLinkedListNode) builder.build(values);

        System.out.println("Original list:");
        LinkedListUtils.printDoublyLinkedList(head);

        RemoveDuplicatesFromSortedDLL obj = new RemoveDuplicatesFromSortedDLL();
        head = obj.approach1(head);

        System.out.println("New list:");
        LinkedListUtils.printDoublyLinkedList(head);
    }
    /*
     * Time - O(N)
     * Space - O(1)
     */
    private DoublyLinkedListNode approach1(DoublyLinkedListNode head) {
        if(head == null || head.get_nextNode() == null) {
            return head;
        }
        DoublyLinkedListNode temp1 = head;
        while(temp1 != null && temp1.get_nextNode() != null) {
            DoublyLinkedListNode temp2 = temp1.get_nextNode();
            while(temp2 != null && temp1.get_data() == temp2.get_data()) {
                temp2 = temp2.get_nextNode();
            }
            temp1.set_nextNode(temp2);
            if(temp2 != null) {
                temp2.set_prevNode(temp1);
            }
            temp1 = temp2;
        }
        return head;
    }
}
