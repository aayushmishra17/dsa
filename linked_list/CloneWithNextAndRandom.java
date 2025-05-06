package linked_list;

import java.util.HashMap;
import java.util.Map;

/*
    Given the head of a linked list where every node of the list has a next and
    a random pointer(pointing to any random node in the list).
    We need to create a clone of this list.
 */
public class CloneWithNextAndRandom {
    public static void main(String[] args) {
        CloneWithNextAndRandom obj = new CloneWithNextAndRandom();
        Node head = obj.buildNodeList();

        System.out.print("Given list:");
        obj.printNodeList(head);

        Node cloneHead = obj.approach2(head);

        System.out.print("Cloned list:");
        obj.printNodeList(cloneHead);
    }
    /*
        Time - O(N * 2 * log N)
        Space - O(N)[hash map] + O(N)[for creating new nodes]
     */
    private Node approach1(Node head) {
        Map<Node, Node> hashMap = new HashMap<>(); // (Original node, clone node)
        Node temp = head;
        while (temp != null) {
            hashMap.put(temp, new Node(temp.data));
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            Node cloneNode = hashMap.get(temp);
            cloneNode.next = hashMap.get(temp.next);
            cloneNode.random = hashMap.get(temp.random);
            temp = temp.next;
        }
        return hashMap.get(head);
    }
    /*
        Time - O(N)[Inserting new node in between] + O(N)[Connect random pointers]
               + O(N)[Connect next pointer of new list and restore next pointer of original list]
        Space - O(N)[for creating new nodes]
     */
    private Node approach2(Node head) {
        if(head == null) {
            return null;
        }
        // Insert new nodes in between.
        Node temp = head;
        while (temp != null) {
            Node newNode = new Node(temp.data);
            newNode.next = temp.next;
            temp.next = newNode;

            temp = temp.next.next;
        }
        // Connect random pointers
        temp = head;
        while (temp != null) {
            if(temp.random != null) {
                temp.next.random = temp.random.next;
            }
            temp = temp.next.next;
        }
        // Connect next pointers
        Node dummyNode = new Node(-1);
        Node res = dummyNode;
        temp = head;
        while (temp != null) {
            res.next = temp.next;
            temp.next = temp.next.next;

            res = res.next;
            temp = temp.next;
        }

        return dummyNode.next;
    }

    private Node buildNodeList() {
        Node one = new Node(7);
        Node two = new Node(13);
        Node three = new Node(11);
        Node four = new Node(10);
        Node five = new Node(1);

        one.next = two;
        one.random = null;

        two.next = three;
        two.random = one;

        three.next = four;
        three.random = five;

        four.next = five;
        four.random = three;

        five.next = null;
        five.random = one;

        return one;
    }
    private void printNodeList(Node head) {
        System.out.println();
        Node temp = head;
        while (temp != null) {
            System.out.print("[ Data=" + temp.data + " Next=" + (temp.next == null ? "null" : temp.next.data) + " Random=" + (temp.random == null ? "null" : temp.random.data) + " ] ");
            temp = temp.next;
        }
        System.out.println();
    }
    private class Node {
        Node next;
        Node random;
        int data;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.random = null;
        }

    }
}
