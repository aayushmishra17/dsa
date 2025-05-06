package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
    We are given head nodes of K sorted singly linked lists.
    We need to merge them into one single sorted list and return its head node.
 */
public class MergeKSortedLists {
    public static void main(String[] args) {
        int[] values1 = new int[] { 2, 4, 6 };
        int[] values2 = new int[] { 1, 5 };
        int[] values3 = new int[] { 1, 1, 3, 7 };
        int[] values4 = new int[] { 8 };
        int k = 4;
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head1 = (SinglyLinkedListNode) builder.build(values1);
        SinglyLinkedListNode head2 = (SinglyLinkedListNode) builder.build(values2);
        SinglyLinkedListNode head3 = (SinglyLinkedListNode) builder.build(values3);
        SinglyLinkedListNode head4 = (SinglyLinkedListNode) builder.build(values4);

        System.out.print("List 1:");
        LinkedListUtils.printSinglyLinkedList(head1);
        System.out.print("List 2:");
        LinkedListUtils.printSinglyLinkedList(head2);
        System.out.print("List 3:");
        LinkedListUtils.printSinglyLinkedList(head3);
        System.out.print("List 4:");
        LinkedListUtils.printSinglyLinkedList(head4);

        MergeKSortedLists obj = new MergeKSortedLists();
        SinglyLinkedListNode newHead = obj.approach2(Arrays.asList(head1, head2, head3, head4), k);

        System.out.println("Merged list:");
        LinkedListUtils.printSinglyLinkedList(newHead);
    }
    /*
        Time -  O(N1 + N2) + O(N1 + N2 + N3) + O(N1 + N2 + N3 + N4)
                Assuming all K lists are of length N then we have -
                = N + 2N + 3N + 4N...KN
                = N * (1 + 2 + 3 + 4 + 5 +...+ K)
                = N * (K * (K + 1)/2 ) [This is somewhere in the order of cubes]
        Space - O(1)
     */
    private SinglyLinkedListNode approach1(List<SinglyLinkedListNode> headsList, int k) {
        SinglyLinkedListNode newHead = headsList.get(0);

        for(int indx = 1; indx < headsList.size(); indx++) {
            newHead = mergeTwoSortedLists(newHead, headsList.get(indx));
        }

        return newHead;
    }
    /*
        Time - O(N1 + N2)
        Space - O(1)
     */
    private SinglyLinkedListNode mergeTwoSortedLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode dummyNode = new SinglyLinkedListNode(-1);
        SinglyLinkedListNode temp1 = head1;
        SinglyLinkedListNode temp2 = head2;
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
        while (temp1 != null) {
            temp3.set_nextNode(temp1);
            temp3 = temp1;
            temp1 = temp1.get_nextNode();
        }
        while (temp2 != null) {
            temp3.set_nextNode(temp2);
            temp3 = temp2;
            temp2 = temp2.get_nextNode();
        }

        return dummyNode.get_nextNode();
    }
    /*
        Time - O(K * log K)[Adding the first node of all K lists into priority queue] +
               O(K * N * 3 * log K)[Assuming each list is of size N and, we perform 3 priority queue operations]
        Space - O(K)[At max at any point we have K elements in the priority queue]
     */
    private SinglyLinkedListNode approach2(List<SinglyLinkedListNode> headsList, int k) {
        class Tuple implements Comparable<Tuple> {
            int value;
            SinglyLinkedListNode node;

            public Tuple(int value, SinglyLinkedListNode node) {
                this.value = value;
                this.node = node;
            }

            @Override
            public int compareTo(Tuple other) {
                return Integer.compare(this.value, other.value);
            }
        }
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        for(SinglyLinkedListNode head : headsList) {
            pq.add(new Tuple(head.get_data(), head));
        }
        SinglyLinkedListNode dummyNode = new SinglyLinkedListNode(-1);
        SinglyLinkedListNode temp = dummyNode;
        while(pq.isEmpty() == false) {
            Tuple smallestNode = pq.poll();
            temp.set_nextNode(smallestNode.node);
            if(smallestNode.node.get_nextNode() != null) {
                pq.add(new Tuple(smallestNode.node.get_nextNode().get_data(), smallestNode.node.get_nextNode()));
            }
            temp = temp.get_nextNode();
        }
        return dummyNode.get_nextNode();
    }
}
