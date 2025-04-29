package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;

public class InsertAtKthPosition {
    public static void main(String[] args) {
        int[] values = new int[] { 10, 20, 30, 40, 50 };
        int k = 5;
        int value = 60;
        if(k < 1 || k > values.length + 1) {
            System.out.println("K must be between " + 1 + " and " + values.length + 1);
            return;
        }
        SinglyLinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(values);
        System.out.println("Linked list before inserting node at " + k + "th position");
        LinkedListUtils.printSinglyLinkedList(head);

        InsertAtKthPosition obj = new InsertAtKthPosition();
        head = obj.approach1(head, k, value);

        System.out.println("Linked list after inserting node at " + k + "th position");
        LinkedListUtils.printSinglyLinkedList(head);
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head, int k, int value) {
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(value);
        if(head == null) {
            return newNode;
        }
        if(k == 1) {
            newNode.set_nextNode(head);
            head = newNode;
            return head;
        }
        SinglyLinkedListNode currNode = head;
        int currPos = 1;
        while(currNode != null) {
            if(currPos == k - 1) {
                newNode.set_nextNode(currNode.get_nextNode());
                currNode.set_nextNode(newNode);
                break;
            }
            currNode = currNode.get_nextNode();
            currPos++;
        }
        return head;
    }
}
