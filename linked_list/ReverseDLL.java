package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.DoublyLinkedListBuilder;
import linked_list.utils.classes.DoublyLinkedListNode;

import java.util.LinkedList;

public class ReverseDLL {
    public static void main(String[] args) {
        int[] values = new int[] { 10, 20, 30, 40, 50 };
        DoublyLinkedListBuilder builder = new DoublyLinkedListBuilder();
        DoublyLinkedListNode head = (DoublyLinkedListNode) builder.build(values);

        System.out.println("Original List :");
        LinkedListUtils.printDoublyLinkedList(head);

        ReverseDLL obj = new ReverseDLL();
        //head = obj.approach1(head);
        head = obj.approach2(head);

        System.out.println("Reversed List :");
        LinkedListUtils.printDoublyLinkedList(head);
    }
    /*
        Time - O(N) + O(N)
        Space - O(N)

        We iterate through the doubly linked list and use a stack
        to store the elements in reverse order. We then pop all elements from the
        stack and iterate through the linked list while replacing its values.
     */
    private DoublyLinkedListNode approach1(DoublyLinkedListNode head) {
        if(head == null) {
            System.out.println("Linked list is empty!");
            return null;
        }

        LinkedList<Integer> stack = new LinkedList<Integer>();
        DoublyLinkedListNode currNode = head;
        while(currNode != null) {
            stack.addFirst(currNode.get_data());
            currNode = currNode.get_nextNode();
        }

        currNode = head;
        while(currNode != null) {
            currNode.set_data(stack.removeFirst());
            currNode = currNode.get_nextNode();
        }

        return head;
    }
    /*
        Time - O(N)
        Space - O(1)

        We'll reverse the links of each node in the list.
     */
    private DoublyLinkedListNode approach2(DoublyLinkedListNode head) {
        if(head == null) {
            System.out.println("List is empty!");
            return null;
        }

        if(head.get_nextNode() == null && head.get_prevNode() == null) {
            // Single node present in linked list.
            return head;
        }

        DoublyLinkedListNode currNode = head;
        DoublyLinkedListNode lastNode = null;
        while(currNode != null) {
            lastNode = currNode.get_prevNode();
            currNode.set_prevNode(currNode.get_nextNode());
            currNode.set_nextNode(lastNode);

            currNode = currNode.get_prevNode(); // the previous node is now the next node.
        }

        return lastNode.get_prevNode();
    }
}
