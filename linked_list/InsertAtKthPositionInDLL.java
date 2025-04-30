package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.DoublyLinkedListBuilder;
import linked_list.utils.classes.DoublyLinkedListNode;

public class InsertAtKthPositionInDLL {

    public static void main(String[] args) {
        int[] values = new int[] { 10, 20, 30, 40, 50 };
        int k = 3;
        int value = 60;
        if(k < 1 || k > values.length + 1) {
            System.out.println("K must be between " + 1 + " and " + values.length + 1);
            return;
        }

        DoublyLinkedListBuilder builder = new DoublyLinkedListBuilder();
        DoublyLinkedListNode head = (DoublyLinkedListNode) builder.build(values);

        System.out.println("Linked list before deletion of node at " + k + "th position.");
        LinkedListUtils.printDoublyLinkedList(head);

        InsertAtKthPositionInDLL obj = new InsertAtKthPositionInDLL();
        head = obj.approach1(head, k, value);

        System.out.println("Linked list after insertion of node at " + k + "th position.");
        LinkedListUtils.printDoublyLinkedList(head);
    }
    /*
     * Time - O(N)
     * Space - O(1)
     */
    private DoublyLinkedListNode approach1(DoublyLinkedListNode head, int k, int value) {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(value);
        if (head == null) {
            return newNode;
        }
        if(head.get_nextNode() == null && head.get_prevNode() == null) {
            // List has only 1 node.
            if(k == 1) {
                // Insert before head
                newNode.set_nextNode(head);
                head.set_prevNode(newNode);
                return newNode;
            } else if(k == 2) {
                // Insert after head
                newNode.set_prevNode(head);
                head.set_nextNode(newNode);
                return head;
            }
        }
        
        if(k == 1) {
            // Insert before head node.
            newNode.set_nextNode(head);
            head.set_prevNode(newNode);
            return newNode;
        }

        int currPosition = 1;
        DoublyLinkedListNode currNode = head;
        while(currNode != null) {
            if(currPosition == k - 1) {
                // Stop at the position after which the new node would be inserted.
                newNode.set_prevNode(currNode);
                newNode.set_nextNode(currNode.get_nextNode());
                if(currNode.get_nextNode() != null) {
                    currNode.get_nextNode().set_prevNode(newNode);
                }
                currNode.set_nextNode(newNode);
                break;
            }
            currNode = currNode.get_nextNode();
            currPosition++;
        }

        return head;
    }

}
