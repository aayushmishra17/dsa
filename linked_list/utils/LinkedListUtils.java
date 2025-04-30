package linked_list.utils;

import linked_list.utils.classes.DoublyLinkedListNode;
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
                System.out.print(temp.get_data());
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

    public static boolean isLastNode(DoublyLinkedListNode node) {

        if(node.get_nextNode() == null) {
            return true;
        }

        return false;
    }

    public static void printDoublyLinkedList(DoublyLinkedListNode head) {
        if(head == null) {
            System.out.println("Linked list is empty!");
            return;
        }
        System.out.println();
        DoublyLinkedListNode temp = head;
        while(temp != null) {
            if(isLastNode(temp)) {
                System.out.print(temp.get_data());
            } else {
                System.out.print(temp.get_data() + " -> ");
            }
            temp = temp.get_nextNode();
        }
        System.out.println();
    }

}
