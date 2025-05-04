package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.DoublyLinkedListBuilder;
import linked_list.utils.classes.DoublyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

public class DeleteAllNodesWithKeyInDLL {
    public static void main(String[] args) {
        int[] values = new int[] { 10, 4, 10, 10, 6, 10 };
        int key = 10;
        LinkedListBuilder builder = new DoublyLinkedListBuilder();
        DoublyLinkedListNode head = (DoublyLinkedListNode) builder.build(values);

        System.out.print("Original list:");
        LinkedListUtils.printDoublyLinkedList(head);

        DeleteAllNodesWithKeyInDLL obj = new DeleteAllNodesWithKeyInDLL();
        head = obj.approach1(head, key);

        System.out.print("New list:");
        LinkedListUtils.printDoublyLinkedList(head);
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private DoublyLinkedListNode approach1(DoublyLinkedListNode head, int key) {
        if(head == null) {
            System.out.println("Empty list! Nothing to remove.");
            return null;
        }
        DoublyLinkedListNode currNode = head;
        while (currNode != null) {
            if(currNode.get_data() == key) {
                DoublyLinkedListNode back = currNode.get_prevNode();
                DoublyLinkedListNode front = currNode.get_nextNode();
                if(back != null) {
                    back.set_nextNode(currNode.get_nextNode());
                }
                if(front != null) {
                    front.set_prevNode(currNode.get_prevNode());
                }
                currNode.set_nextNode(null);
                currNode.set_prevNode(null);
                currNode = front;
                if(back == null) {
                    head = front;
                }
            } else {
                currNode = currNode.get_nextNode();
            }
        }
        return head;
    }
}
