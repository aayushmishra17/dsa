package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

import java.util.HashSet;
import java.util.Set;

/*
    We are given head node of two linked list where they
    intersect at some common node. We need to find this
    common node.
    In case the given lists don't intersect then we need
    to return null.
    E.g.:
        List 1: 3 -> 1 -> 4 -> 6 -> 2
        List 2: 1 -> 2 -> 4 -> 5 -> 4 -> 6 -> 2
        Here the last 4 node in the 2nd list is the intersection point.
        3 1 ->
                   4 -> 6 -> 2
        1 2 4 5 ->
 */
public class IntersectionPointOfTwoLinkedList {
    public static void main(String[] args) {
        int[] values1 = new int[] { 3, 1 };
        int[] values2 = new int[] { 1, 2, 4, 5, 4, 6, 2 };
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head1 = (SinglyLinkedListNode) builder.build(values1);
        SinglyLinkedListNode head2 = (SinglyLinkedListNode) builder.build(values2);
        IntersectionPointOfTwoLinkedList obj = new IntersectionPointOfTwoLinkedList();
        obj.joinListsAtIntersectionNode(head1, head2, 4);

        System.out.print("List 1: ");
        LinkedListUtils.printSinglyLinkedList(head1);
        System.out.print("List 2: ");
        LinkedListUtils.printSinglyLinkedList(head2);

        SinglyLinkedListNode intersectionNode = obj.approach3(head1, head2);
        if(intersectionNode != null) {
            System.out.println("The intersection node is " + intersectionNode.get_data());
        } else {
            System.out.println("The given lists do not intersect.");
        }
    }
    private void joinListsAtIntersectionNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2, int intersectionNodeData) {
        SinglyLinkedListNode currNode = head2;
        SinglyLinkedListNode intersectionNode = null;
        while(currNode != null) {
            if(currNode.get_data() == intersectionNodeData) {
                intersectionNode = currNode;
            }
            currNode = currNode.get_nextNode();
        }
        SinglyLinkedListNode tailNodeOfFirstList = head1;
        while (tailNodeOfFirstList.get_nextNode() != null) {
            tailNodeOfFirstList = tailNodeOfFirstList.get_nextNode();
        }
        tailNodeOfFirstList.set_nextNode(intersectionNode);
    }
    /*
        Time - O(N1 * log N1) + O(N2 * log N2)
        Space - O(N)

        We'll use a map to store all the nodes of one list and then iterate over the next list
        and check if any node of this list already exists in the map, if yes then we have an intersecting
        node.
     */
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        Set<SinglyLinkedListNode> set = new HashSet<>();
        SinglyLinkedListNode currNode = head1;
        while(currNode != null) {
            set.add(currNode);
            currNode = currNode.get_nextNode();
        }
        currNode = head2;
        while(currNode != null) {
            if(set.contains(currNode)) {
                return currNode;
            }
            currNode = currNode.get_nextNode();
        }
        return null;
    }
    /*
        Time - O(N1) + O(N2) + O(|N2 - N1|) + O(N1)
        Space - O(1)

        We figure out the length of both the lists.
        Then we use two pointers kept at the head of each list and, we move the pointer of the longer
        list to |N1 - N2| nodes forward so that each pointer is at the same starting point.
        Then if at any stage we find that both nodes are same then we have an intersection node.
     */
    private SinglyLinkedListNode approach2(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode temp1 = head1;
        int n1 = 0;
        while (temp1 != null) {
            n1++;
            temp1 = temp1.get_nextNode();
        }
        SinglyLinkedListNode temp2 = head2;
        int n2 = 0;
        while (temp2 != null) {
            n2++;
            temp2 = temp2.get_nextNode();
        }
        int lengthDiff = Math.abs(n1 - n2);
        temp1 = head1;
        temp2 = head2;
        if(n1 > n2) {
            int i = 0;
            while(i < lengthDiff) {
                temp1 = temp1.get_nextNode();
                i++;
            }
        } else if(n2 > n1) {
            int i = 0;
            while(i < lengthDiff) {
                temp2 = temp2.get_nextNode();
                i++;
            }
        }
        while(temp1 != null && temp2 != null) {
            if(temp1.equals(temp2)) {
                return temp1;
            }
            temp1 = temp1.get_nextNode();
            temp2 = temp2.get_nextNode();
        }
        return null;
    }
    /*
        Time - O(N1 + N2)[When both lists do not collide temp1 and temp2 would traverse through each node in both the lists]
        Space - O(1)

        Algorithm:
            We place a pointer each at the head of both lists and move both pointers 1 node at a time simultaneously.
            When any pointer becomes null, we move it to the head of the other list. Eventually, both pointers would
            be at the same starting point and then would intersect if the lists intersect. Else if both pointers are
            null at the same time then the lists do not intersect.
            Reason why both pointers would eventually start at the same point is when any pointer reaches null
            and, we place that pointer to the head of the other list, at some point this pointer would be placed at the
            start of the shorter list and the other pointer would be at |N2 - N1| distance apart on the longer list,
            which means they would be standing at the same starting point.
     */
    private SinglyLinkedListNode approach3(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if(head1 == null || head2 == null) {
            return null;
        }
        SinglyLinkedListNode temp1 = head1;
        SinglyLinkedListNode temp2 = head2;
        while (temp1 != temp2) { // The first node can also be the intersection node if both lists are same.
            temp1 = temp1.get_nextNode();
            temp2 = temp2.get_nextNode();

            if(temp1 == temp2) {
                return temp1;
            }
            if(temp1 == null) {
                temp1 = head2;
            }
            if(temp2 == null) {
                temp2 = head1;
            }
        }
        return temp1; // In the end both temp1 and temp2 would be null.
    }
}
