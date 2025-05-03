package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

/*
    Given a linked list containing only 0s, 1s and 2s, our task
    is to sort the linked list.
 */
public class Sort0s1s2s {
    public static void main(String[] args) {
        int[] values = new int[] { 0, 0, 1, 2, 2, 1, 0, 1 };
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(values);

        System.out.println("Original list:");
        LinkedListUtils.printSinglyLinkedList(head);

        Sort0s1s2s obj = new Sort0s1s2s();
        head = obj.approach2(head);

        System.out.println("Sorted list:");
        LinkedListUtils.printSinglyLinkedList(head);
    }
    /*
        Time - O(N) + O(N)
        Space - O(1)

        We try to change the values of the nodes.
     */
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head) {
        if(head == null || head.get_nextNode() == null) {
            return head;
        }
        int cnt0 = 0, cnt1 = 0, cnt2 = 0;
        SinglyLinkedListNode currNode = head;
        while(currNode != null) {
            switch (currNode.get_data()) {
                case 0: cnt0++; break;
                case 1: cnt1++; break;
                case 2: cnt2++; break;
            }
            currNode = currNode.get_nextNode();
        }
        currNode = head;
        int i = 0;
        while(i < cnt0) {
            currNode.set_data(0);
            i++;
            currNode = currNode.get_nextNode();
        }
        i = 0;
        while(i < cnt1) {
            currNode.set_data(1);
            i++;
            currNode = currNode.get_nextNode();
        }
        i = 0;
        while(i < cnt2) {
            currNode.set_data(2);
            i++;
            currNode = currNode.get_nextNode();
        }
        return head;
    }
    /*
        Time - O(N)
        Space - O(1)

        We try to change the links of the nodes.
     */
    private SinglyLinkedListNode approach2(SinglyLinkedListNode head) {
        if(head == null || head.get_nextNode() == null) {
            return head;
        }
        SinglyLinkedListNode zeroHead = new SinglyLinkedListNode(-1);
        SinglyLinkedListNode oneHead = new SinglyLinkedListNode(-1);
        SinglyLinkedListNode twoHead = new SinglyLinkedListNode(-1);
        SinglyLinkedListNode currNode = head;
        SinglyLinkedListNode currZeroNode = zeroHead;
        SinglyLinkedListNode currOneNode = oneHead;
        SinglyLinkedListNode currTwoNode = twoHead;
        while(currNode != null) {
            if(currNode.get_data() == 0) {
                currZeroNode.set_nextNode(currNode);
                currZeroNode = currNode;
            } else if(currNode.get_data() == 1) {
                currOneNode.set_nextNode(currNode);
                currOneNode = currNode;
            } else if(currNode.get_data() == 2) {
                currTwoNode.set_nextNode(currNode);
                currTwoNode = currNode;
            }
            currNode = currNode.get_nextNode();
        }

        currZeroNode.set_nextNode(oneHead.get_nextNode() != null ? oneHead.get_nextNode() : twoHead.get_nextNode());
        currOneNode.set_nextNode(twoHead.get_nextNode());
        currTwoNode.set_nextNode(null);

        return zeroHead.get_nextNode();
    }
}
