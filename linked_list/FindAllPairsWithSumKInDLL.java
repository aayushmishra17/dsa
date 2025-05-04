package linked_list;

import linked_list.utils.LinkedListUtils;
import linked_list.utils.classes.DoublyLinkedListBuilder;
import linked_list.utils.classes.DoublyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

import java.util.ArrayList;
import java.util.List;

/*
    We are given a sorted doubly linked list and a value K.
    We need to return all pairs of nodes whose values sum to K.
 */
public class FindAllPairsWithSumKInDLL {
    public static void main(String[] args) {
        int[] values = new int[] { 1, 2, 3, 4, 9 };
        int k = 5;
        LinkedListBuilder builder = new DoublyLinkedListBuilder();
        DoublyLinkedListNode head = (DoublyLinkedListNode) builder.build(values);

        System.out.print("Original list:");
        LinkedListUtils.printDoublyLinkedList(head);
        System.out.println("K = " + k);

        FindAllPairsWithSumKInDLL obj = new FindAllPairsWithSumKInDLL();
        List<Pair> pairs = obj.approach2(head, k);

        if(pairs.size() == 0) {
            System.out.println("No such pair exists!");
        } else {
            obj.printPairs(pairs);
        }
    }
    private class Pair {
        DoublyLinkedListNode first;
        DoublyLinkedListNode second;

        public Pair(DoublyLinkedListNode firstNode, DoublyLinkedListNode secondNode) {
            first = firstNode;
            second = secondNode;
        }

        public DoublyLinkedListNode getFirst() {
            return first;
        }
        public DoublyLinkedListNode getSecond() {
            return second;
        }
    }
    /*
        Time - O(N * N)
        Space - O(1)
     */
    private List<Pair> approach1(DoublyLinkedListNode head, int targetSum) {
        DoublyLinkedListNode temp1 = head;
        DoublyLinkedListNode temp2;
        List<Pair> result = new ArrayList<>();
        if(head == null) {
            return result;
        }
        while(temp1.get_nextNode() != null) {
            temp2 = temp1.get_nextNode();
            while(temp2 != null) {
                if(temp1.get_data() + temp2.get_data() == targetSum) {
                    Pair pair = new Pair(temp1, temp2);
                    result.add(pair);
                } else if(temp1.get_data() + temp2.get_data() > targetSum) {
                    /*
                        Since list is sorted so if at any point the current sum is more than target value
                        then no point iterating further since numbers ahead would be even larger in value.
                     */
                    break;
                }
                temp2 = temp2.get_nextNode();
            }
            temp1 = temp1.get_nextNode();
        }
        return result;
    }
    /*
        Time - O(N)[get to the tail node] + O(N)
        Space - O(1)

        Since the list is sorted we can use this, we keep one pointer at beginning and
        one pointer at the end. Calculate teh current sum and move the left and right pointers
        accordingly.
     */
    private List<Pair> approach2(DoublyLinkedListNode head, int targetSum) {
        DoublyLinkedListNode left = head;
        DoublyLinkedListNode right = head;
        List<Pair> result = new ArrayList<>();
        while(right.get_nextNode() != null) {
            right = right.get_nextNode();
        }
        while(left.get_data() <= right.get_data()) {
            int currSum = left.get_data() + right.get_data();
            if(currSum == targetSum) {
                Pair pair = new Pair(left, right);
                result.add(pair);
                left = left.get_nextNode();
                right = right.get_prevNode();
            } else if(currSum < targetSum) {
                left = left.get_nextNode();
            } else {
                right = right.get_prevNode();
            }
        }
        return result;
    }
    private void printPairs(List<Pair> list) {
        System.out.print("Pairs - ");
        for(Pair pair : list) {
            System.out.print("( " + pair.first.get_data() + "," + pair.second.get_data() + " ) ");
        }
        System.out.println();
    }
}
