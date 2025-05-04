package linked_list;

import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

import java.util.HashMap;
import java.util.Map;

/*
    Given the head of a linked list we need to first check if a loop exists
    in the list and if it does then return the length of the loop.
 */
public class LengthOfLoop {
    public static void main(String[] args) {
        int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(values);
        LengthOfLoop obj = new LengthOfLoop();
        obj.buildLoopInList(head, 3);

        int lengthOfLoop = obj.approach2(head);
        if(lengthOfLoop == 0) {
            System.out.println("Loop does not exits.");
        } else {
            System.out.println("Length of loop = " + lengthOfLoop);
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
    private int approach1(SinglyLinkedListNode head) {
        if(checkForLoop(head)) {
            Map<SinglyLinkedListNode, Integer> hashMap = new HashMap();
            int timer = 0;
            SinglyLinkedListNode currNode = head;
            while (currNode != null) {
                timer++;
                if(hashMap.containsKey(currNode)) {
                    return timer - hashMap.get(currNode);
                }
                hashMap.put(currNode, timer);
                currNode = currNode.get_nextNode();
            }
        }
        return 0;
    }
    /*
        Time - O(N) + O(loop length)
        Space - O(1)
     */
    private int approach2(SinglyLinkedListNode head) {
        SinglyLinkedListNode slowPointer = head;
        SinglyLinkedListNode fastPointer = head;
        while (fastPointer != null && fastPointer.get_nextNode() != null) {
            slowPointer = slowPointer.get_nextNode();
            fastPointer = fastPointer.get_nextNode().get_nextNode();
            if(slowPointer == fastPointer) {
                int loopLength = 1;
                slowPointer = slowPointer.get_nextNode();
                while(slowPointer != fastPointer) {
                    loopLength++;
                    slowPointer = slowPointer.get_nextNode();
                }
                return loopLength;
            }
        }
        return 0;
    }
    private boolean checkForLoop(SinglyLinkedListNode head) {
        SinglyLinkedListNode slowPointer = head;
        SinglyLinkedListNode fastPointer = head;
        while (fastPointer != null && fastPointer.get_nextNode() != null) {
            slowPointer = slowPointer.get_nextNode();
            fastPointer = fastPointer.get_nextNode().get_nextNode();
            if (slowPointer == fastPointer) {
                return true;
            }
        }
        return false;
    }
}
