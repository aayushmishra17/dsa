package linked_list;

import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

import java.util.HashSet;
import java.util.Set;

/*
    Given a singly linked list check if a loop exists in the list.
 */
public class DetectCycle {
    public static void main(String[] args) {
        int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(values);
        DetectCycle obj = new DetectCycle();
        obj.buildLoopInList(head, 3);

        boolean loopExists = obj.approach2(head);
        if(loopExists) {
            System.out.println("Loop exists!");
        } else {
            System.out.println("Loop not found.");
        }
    }
    private void buildLoopInList(SinglyLinkedListNode head, int loopHead) {
        SinglyLinkedListNode tailNode = head;
        while (tailNode.get_nextNode() != null) {
            tailNode = tailNode.get_nextNode();
        }
        SinglyLinkedListNode loopStartNode = head;
        while (loopStartNode != null) {
            if(loopStartNode.get_data() == loopHead) {
                break;
            }
            loopStartNode = loopStartNode.get_nextNode();
        }
        tailNode.set_nextNode(loopStartNode);
    }
    /*
        Time - O(N * 2 * logN)
        Space - O(N)
     */
    private boolean approach1(SinglyLinkedListNode head) {
        Set<SinglyLinkedListNode> hashSet = new HashSet<>();
        SinglyLinkedListNode currNode = head;
        while(currNode != null) {
            if(hashSet.contains(currNode)) {
                return true;
            }
            hashSet.add(currNode);
            currNode = currNode.get_nextNode();
        }
        return false;
    }
    /*
        Time - O(N)
        Space - O(1)

        We use the slow-fast pointer approach, slow pointer moves 1 node at a time while
        the fast pointer moves 2 nodes at a time. If loop exists then at some stage slow
        and fast pointers would point to the same node.
        How are we sure that slow and fast pointers would point to the same node in
        case of a loop?
            Since both slow and fast pointers are moving in the same direction and slow
            pointer moves 1 node while fast pointer moves 2 nodes at a time, effectively the
            fast pointer is moving towards the slow pointer at a rate of 1 node in case a
            loop exists. This means the fast pointer is decreasing the distance between
            them by 1 node at a time, so eventually if a loop exists then they would end up
            pointing to the same node.
            This is the reason why we must move the fast pointer exactly 2 nodes at a time and
            not 3 or 4 nodes at a time, otherwise they would not point to the same node.
     */
    private boolean approach2(SinglyLinkedListNode head) {
        SinglyLinkedListNode slowPointer = head;
        SinglyLinkedListNode fastPointer = head;
        while(fastPointer != null && fastPointer.get_nextNode() != null) {
            slowPointer = slowPointer.get_nextNode();
            fastPointer = fastPointer.get_nextNode().get_nextNode();
            if(slowPointer == fastPointer) {
                return true;
            }
        }
        return false;
    }
}
