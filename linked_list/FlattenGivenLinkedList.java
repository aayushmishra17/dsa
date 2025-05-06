package linked_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given the head of following linked list structure, we need to flatten and return the head of the linked list 
 * such that the flattened list has only child nodes and all child nodes are in sorted order.
 * Every node has two reference next node and child node. Next node gorws horizontally while child node grows vertically.
 * The next node order is not sorted while the child node is in sorted order.
 * 
 * e.g. 3  2  1  4  5 x
 *      x  10 7  9  6
 *         x  11 x  8
 *            12    x
 * Resultant list:
 *      1  x
 *      2  x
 *      3  x
 *      4  x
 *      5  x
 *      6  x
 *      7  x
 *      8  x
 *      9  x
 *      10 x
 */
public class FlattenGivenLinkedList {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>(
            Arrays.asList(
                Arrays.asList(3),
                Arrays.asList(2, 10),
                Arrays.asList(1, 7, 11, 12),
                Arrays.asList(4, 9),
                Arrays.asList(5, 6, 8)
            )
        );
        FlattenGivenLinkedList obj = new FlattenGivenLinkedList();
        Node head = obj.buildListStructure(list);
        obj.printListStructure(head);
        
        head = obj.approach1(head);

        System.out.println("Flattened list:");
        obj.printListStructure(head);
    }
    private class Node {
        private Node nextNode;
        private Node childNode;
        private int value;

        public Node(int value) {
            nextNode = null;
            childNode = null;
            this.value = value;
        }
        public Node(int value, Node nextNode, Node childNode) {
            this.nextNode = nextNode;
            this.childNode = childNode;
            this.value = value;
        }

        public Node next() {
            return this.nextNode;
        }
        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
        public Node child() {
            return this.childNode;
        }
        public void setChildNode(Node childNode) {
            this.childNode = childNode;
        }
        public int data() {
            return this.value;
        }
        public void setData(int value) {
            this.value = value;
        }
    }
    
    private Node buildListStructure(List<List<Integer>> values) {
        Node dummyHead = new Node(-1);
        Node temp = dummyHead;
        for(List<Integer> list : values) {
            Node newNode = new Node(list.get(0));
            Node dummyChildNode = newNode;
            for(int indx = 1; indx < list.size(); indx++) {
                Node childNode = new Node(list.get(indx));
                dummyChildNode.setChildNode(childNode);
                dummyChildNode = childNode;
            }
            temp.setNextNode(newNode);
            temp = newNode;
        }
        return dummyHead.next();
    }
    private void printListStructure(Node head) {
        System.out.println();
        Node horizontal = head;
        while(horizontal != null) {
            Node vertical = horizontal;
            while(vertical != null) {
                System.out.print(vertical.data() + " ");
                vertical = vertical.child();
            }
            horizontal = horizontal.next();
            System.out.println();
        }
    }

    /*
     * Time - O(N) * O(N+M)
     * Space - O(N)[auxiliary stack space]
     */
    private Node approach1(Node head) {
        if(head == null || head.next() == null) {
            return head;
        }
        Node previousResult = approach1(head.next());
        return mergeTwoLists(head, previousResult);
    }
    /*
     * Time - O(N + M)
     * Space - O(1)
     */
    private Node mergeTwoLists(Node head1, Node head2) {
        Node dummyNode = new Node(-1);
        Node temp1 = head1;
        Node temp2 = head2;
        Node temp3 = dummyNode;
        while(temp1 != null && temp2 != null) {
            if(temp1.data() <= temp2.data()) {
                temp3.setChildNode(temp1);
                temp3 = temp1;
                temp1 = temp1.child();
            } else {
                temp3.setChildNode(temp2);
                temp3 = temp2;
                temp2 = temp2.child();
            }
            temp3.setNextNode(null);
        }
        while(temp1 != null) {
            temp3.setChildNode(temp1);
            temp3 = temp1;
            temp1 = temp1.child();
        }
        while(temp2 != null) {
            temp3.setChildNode(temp2);
            temp3 = temp2;
            temp2 = temp2.child();
        }
        if(dummyNode.next() != null) {
            dummyNode.child().setNextNode(null);
        }
        return dummyNode.child();
    }
}
