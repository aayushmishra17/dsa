package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

/*
 * Given the head of two sorted singly linked lists we need to merge both into 
 * a single list and return the head of the new list. The new merged list must be in sorted order.
 * 
 * L1: 2 4 8 10
 * L2: 1 3 3 6 11 14
 * L3: 1 2 3 3 4 6 8 10 11 14
 */
public class MergeTwoSortedLinkedLists {
    public static void main(String[] args) {
        int[] values1 = new int[] { 2, 4, 8, 10 };
        int[] values2 = new int[] { 1, 3, 3, 6, 11, 14 };
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head1 = (SinglyLinkedListNode) builder.build(values1);
        SinglyLinkedListNode head2 = (SinglyLinkedListNode) builder.build(values2);

        System.out.print("List 1:");
        LinkedListUtils.printSinglyLinkedList(head1);
        System.out.print("List 2:");
        LinkedListUtils.printSinglyLinkedList(head2);
        
        MergeTwoSortedLinkedLists obj = new MergeTwoSortedLinkedLists();
        SinglyLinkedListNode newHead = obj.approach2(head1, head2);

        System.out.println("Merged list:");
        LinkedListUtils.printSinglyLinkedList(newHead);
    }
    /*
     * Time - O(N1) + O(N2)
     * Space - O(N1 + N2)[For new list]
     */
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if(head1 == null && head2 != null) {
            return head2;
        } else if(head1 != null && head2 == null) {
            return head1;
        } else if(head1 == null && head2 == null) {
            System.out.println("Both lists are empty!");
            return null;
        }
        SinglyLinkedListNode newHead = new SinglyLinkedListNode(-1);
        SinglyLinkedListNode temp3 = newHead;
        
        SinglyLinkedListNode temp1 = head1;
        SinglyLinkedListNode temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if(temp1.get_data() <= temp2.get_data()) {
                SinglyLinkedListNode newNode = new SinglyLinkedListNode(temp1.get_data());
                if(newHead.get_nextNode() == null) {
                    newHead.set_nextNode(newNode);
                } else {
                    temp3.set_nextNode(newNode);
                }
                temp3 = newNode;
                temp1 = temp1.get_nextNode();
            } else {
                SinglyLinkedListNode newNode = new SinglyLinkedListNode(temp2.get_data());
                if(newHead.get_nextNode() == null) {
                    newHead.set_nextNode(newNode);
                } else {
                    temp3.set_nextNode(newNode);
                }
                temp3 = newNode;
                temp2 = temp2.get_nextNode();
            }
        }
        while(temp1 != null) {
            SinglyLinkedListNode newNode = new SinglyLinkedListNode(temp1.get_data());
            temp3.set_nextNode(newNode);
            temp3 = newNode;
            temp1 = temp1.get_nextNode();
        }
        while(temp2 != null) {
            SinglyLinkedListNode newNode = new SinglyLinkedListNode(temp2.get_data());
            temp3.set_nextNode(newNode);
            temp3 = newNode;
            temp2 = temp2.get_nextNode();
        }

        return newHead.get_nextNode();
    }
    /*
     * Time - O(N1) + O(N2)
     * Space - O(1)
     * 
     * Without creating new nodes.
     */
    private SinglyLinkedListNode approach2(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if(head1 == null && head2 != null) {
            return head2;
        } else if(head1 != null && head2 == null) {
            return head1;
        } else if(head1 == null && head2 == null) {
            System.out.println("Both lists are empty!");
            return null;
        }
        SinglyLinkedListNode temp1 = head1;
        SinglyLinkedListNode temp2 = head2;
        SinglyLinkedListNode dummyNode = new SinglyLinkedListNode(-1);
        SinglyLinkedListNode temp3 = dummyNode;

        while(temp1 != null && temp2 != null) {
            if(temp1.get_data() <= temp2.get_data()) {
                temp3.set_nextNode(temp1);
                temp3 = temp1;
                temp1 = temp1.get_nextNode();
            } else {
                temp3.set_nextNode(temp2);
                temp3 = temp2;
                temp2 = temp2.get_nextNode();
            }
        }
        while(temp1 != null) {
            temp3.set_nextNode(temp1);
            temp3 = temp1;
            temp1 = temp1.get_nextNode();
        }
        while(temp2 != null) {
            temp3.set_nextNode(temp2);
            temp3 = temp2;
            temp2 = temp2.get_nextNode();
        }

        return dummyNode.get_nextNode();
    }
}
