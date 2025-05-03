package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

/*
    We have been given two singly linked list, each representing a number
    in reverse. We need to return a new singly linked list which represents the
    sum of the given numbers in reverse.
    E.g.: L1 : 3 -> 2 -> 1 => 123
          L2 : 6 -> 5->  4 =>  456
          Sum: 9 -> 7 -> 5 => 579
*/
public class AddTwoNumbers {
    public static void main(String[] args) {
        int[] values1 = new int[] { 3, 2, 1 };
        int[] values2 = new int[] { 6, 5, 4 };

        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head1 = (SinglyLinkedListNode) builder.build(values1);
        SinglyLinkedListNode head2 = (SinglyLinkedListNode) builder.build(values2);

        System.out.println("List 1 :");
        LinkedListUtils.printSinglyLinkedList(head1);
        System.out.println("List 2 :");
        LinkedListUtils.printSinglyLinkedList(head2);

        AddTwoNumbers obj = new AddTwoNumbers();
        SinglyLinkedListNode resultHead = obj.approach1(head1, head2);

        System.out.println("Sum of given list :");
        LinkedListUtils.printSinglyLinkedList(resultHead);
    }
    /*
        Time - O(max(N1, N2))
        Space - O(1)

        Whenever we need to create a new linked list it is advisable to use a dummy node
        to point to the head node of the new list as it will make the code much more simpler.
     */
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode resultHead = new SinglyLinkedListNode(-1);
        SinglyLinkedListNode currNode = resultHead;
        int carry = 0;
        SinglyLinkedListNode temp1 = head1;
        SinglyLinkedListNode temp2 = head2;
        int sum = 0;
        while(temp1 != null || temp2 != null) {
            sum = carry;
            if(temp1 != null) {
                sum += temp1.get_data();
            }
            if(temp2 != null) {
                sum += temp2.get_data();
            }
            SinglyLinkedListNode newNode = new SinglyLinkedListNode(sum % 10);
            carry = sum / 10;

            currNode.set_nextNode(newNode);
            currNode = currNode.get_nextNode();

            if(temp1 != null) {
                temp1 = temp1.get_nextNode();
            }
            if(temp2 != null) {
                temp2 = temp2.get_nextNode();
            }
        }
        if(carry != 0) {
            SinglyLinkedListNode newNode = new SinglyLinkedListNode(carry);
            currNode.set_nextNode(newNode);
        }
        return resultHead.get_nextNode();
    }
}
