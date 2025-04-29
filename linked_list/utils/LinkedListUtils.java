package linked_list.utils;

import linked_list.utils.classes.SinglyLinkedListNode;

public class LinkedListUtils {

    public static void printSinglyLinkedList(SinglyLinkedListNode head) {
        if(head == null) {
            System.out.println("Linked list is empty!");
            return;
        }
        System.out.println();
        SinglyLinkedListNode temp = head;
        while(temp != null) {
            if(isLastNode(temp)) {
                System.out.println(temp.get_data());
            } else {
                System.out.print(temp.get_data() + " -> ");
            }
            temp = temp.get_nextNode();
        }
        System.out.println();
    }

    public static boolean isLastNode(SinglyLinkedListNode node) {

        if(node.get_nextNode() == null) {
            return true;
        }

        return false;
    }

}
