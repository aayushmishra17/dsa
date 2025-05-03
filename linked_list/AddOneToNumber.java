package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

/*
    Given a number in the form of a singly linked list, we need to
    add 1 to the number and then return the new number in the form
    of a linked list.

    E.g.: Given number: 9 -> 9 -> 9 -> 9
          Add 1:        1 -> 0 -> 0 -> 0 -> 0
 */
public class AddOneToNumber {
    public static void main(String[] args) {
        int[] number = new int[] { 9, 9, 9, 9 };
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(number);

        System.out.println("Given number: ");
        LinkedListUtils.printSinglyLinkedList(head);

        AddOneToNumber obj = new AddOneToNumber();
        head = obj.approach2(head);

        System.out.println("Number after adding 1: ");
        LinkedListUtils.printSinglyLinkedList(head);
    }
    /*
        Time - O(N) + O(N) + O(N)
        Space - O(1)

        Since to add 1 we must add it to the unit's place so we first reverse the list
        and then add 1.
     */
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head) {
        if(head == null) {
            System.out.println("Empty list!");
            return head;
        }
        SinglyLinkedListNode newHead = reverseList(head);
        int carry = 1; // Since we have to add 1.
        SinglyLinkedListNode currNode = newHead;
        while (currNode != null) {
            int sum = carry + currNode.get_data();
            currNode.set_data(sum % 10);
            if(sum < 10) {
                carry = 0;
                break;
            }
            currNode = currNode.get_nextNode();
        }
        head = reverseList(newHead);
        if(carry == 1) {
            SinglyLinkedListNode carryNode = new SinglyLinkedListNode(1);
            carryNode.set_nextNode(head);
            return carryNode;
        }
        return head;
    }
    /*
        Time - O(N)
        Space - O(N)[due to recursiveList method]

        If we are not allowed to reverse the list then the only option to
        reach the unit's place is via recursion.
     */
    private SinglyLinkedListNode approach2(SinglyLinkedListNode head) {
        int carry = recurseList(head);
        if(carry == 1) {
            SinglyLinkedListNode newNode = new SinglyLinkedListNode(1);
            newNode.set_nextNode(head);
            return newNode;
        }
        return head;
    }
    /*
        Time - O(N)
        Space - O(N)[recursive stack space]
     */
    private int recurseList(SinglyLinkedListNode node) {
        if(node == null) {
            return 1;
        }
        int carry = recurseList(node.get_nextNode());
        int sum = node.get_data() + carry;
        node.set_data(sum % 10);
        if(sum == 10) {
            return 1;
        } else {
            return 0;
        }
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private SinglyLinkedListNode reverseList(SinglyLinkedListNode head) {
        if(head == null || head.get_nextNode() == null) {
            return head;
        }
        SinglyLinkedListNode prevNode = null;
        SinglyLinkedListNode currNode = head;
        SinglyLinkedListNode nextNode;
        while (currNode != null) {
            nextNode = currNode.get_nextNode();
            currNode.set_nextNode(prevNode);
            prevNode = currNode;
            currNode = nextNode;
        }
        return prevNode;
    }
}
