package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.DoublyLinkedListBuilder;
import linked_list.utils.classes.DoublyLinkedListNode;

public class DeleteKthNodeInDLL {

    public static void main(String[] args) {
        int[] values = new int[] { 10, 20, 30, 40, 50 };
        int k = 3;
        DoublyLinkedListBuilder builder = new DoublyLinkedListBuilder();
        DoublyLinkedListNode head = (DoublyLinkedListNode) builder.build(values);

        System.out.println("Linked list before deletion of " + k + "th node.");
        LinkedListUtils.printDoublyLinkedList(head);

        DeleteKthNodeInDLL obj = new DeleteKthNodeInDLL();
        head = obj.approach1(head, k);

        System.out.println("Linked list after deletion of " + k + "th node.");
        LinkedListUtils.printDoublyLinkedList(head);
    }
    /*
     * Time - O(N)
     * Space - O(1)
     * 
     * Her we don't need to keep two pointer nodes as the doubly linked list has 
     * reference to both next and previous nodes.
     */
    private DoublyLinkedListNode approach1(DoublyLinkedListNode head, int k) {

        if(head == null) {
            System.out.println("Linked list is empty!");
            return null;
        }
        if(k == 1 && head.get_nextNode() == null) {
            // Only 1 node present in list.
            head = head.get_nextNode();
        } else {
            int currPosition = 1;
            DoublyLinkedListNode currNode = head;
            while(currNode != null) {
                if(k == currPosition) {
                    if(currNode.get_prevNode() != null) {
                        currNode.get_prevNode().set_nextNode(currNode.get_nextNode());
                    }
                    if(currNode.get_nextNode() != null) {
                        currNode.get_nextNode().set_prevNode(currNode.get_prevNode()); 
                    }
                    currNode.set_prevNode(null);
                    currNode.set_nextNode(null);
                    break;
                }
                currPosition++;
                currNode = currNode.get_nextNode();
            }
        }

        return head;
    }

}
