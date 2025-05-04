package linked_list;

import linked_list.utils.classes.SinglyLinkedListBuilder;
import linked_list.utils.classes.SinglyLinkedListNode;
import linked_list.utils.interfaces.LinkedListBuilder;

import java.util.HashSet;
import java.util.Set;

/*
    Given the head of a linked list find the starting node of the cycle in case
    a cycle exists in the list.
 */
public class FindStartingNodeOfCycle {
    public static void main(String[] args) {
        int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        LinkedListBuilder builder = new SinglyLinkedListBuilder();
        SinglyLinkedListNode head = (SinglyLinkedListNode) builder.build(values);
        FindStartingNodeOfCycle obj = new FindStartingNodeOfCycle();
        obj.buildLoopInList(head, 3);

        SinglyLinkedListNode startingNode = obj.approach2(head);
        if(startingNode == null) {
            System.out.println("Loop does not exists!");
        } else {
            System.out.println("Staring node of loop is " + startingNode.get_data());
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
    private SinglyLinkedListNode approach1(SinglyLinkedListNode head) {
        Set<SinglyLinkedListNode> hashSet = new HashSet<>();
        SinglyLinkedListNode currNode = head;
        while (currNode != null) {
            if(hashSet.contains(currNode)) {
                // Loop exists, now find the starting node of the loop.
                /*
                    The current node must be the starting node since this is the first node
                    that already exists in the map.
                 */
                return currNode;
            }
            hashSet.add(currNode);
            currNode = currNode.get_nextNode();
        }
        return null;
    }
    /*
        Time - O(N)
        Space - O(1)

        We use the slow-fast pointer approach to detect a cycle.
        Once cycle is found, we move either of the slow or fast pointer to the head
        and then start moving both pointer 1 node at a time. The node at which both
        pointers point to the same node will be the starting node.

        Why?
            Since fast pointer = 2x slow pointer, whatever distance the slow pointer
            covers, the fast pointer would cover 2x of that distance, which means the
            fast pointer would at a distance of L1 ahead of slow pointer if slow pointer
            is at a distance of L1 from the head.

            #1 Let's say slow pointer is at the beginning of the loop at distance of L1
            then the distance of the fast pointer is L1 from the beginning of the loop.

            #2 Let's say there is a distance d remaining which the fast pointer needs to
            cover to reach the slow pointer, but the slow pointer is also moving in the
            same direction. When both pointers meet, the slow pointer would have covered a
            distance of d while the fast pointer must have covered a distance of 2 * d since
            only then they would meet because 2d-d = d meaning the distance being decreased by 1.

            #3 Now the length of the loop = L1 + d and since the slow pointer has moved through
            a distance of d from teh starting point of the loop till the meeting point, the
            remaining distance from the meeting point to the starting point must be L1.

            #4 Therefore, from #3 we can see the distance from head to starting point "L1" is
            same as the distance from meeting point to the starting point of loop, L1.
     */
    private SinglyLinkedListNode approach2(SinglyLinkedListNode head) {
        SinglyLinkedListNode slowPointer = head;
        SinglyLinkedListNode fastPointer = head;
        while (fastPointer != null && fastPointer.get_nextNode() != null) {
            slowPointer = slowPointer.get_nextNode();
            fastPointer = fastPointer.get_nextNode().get_nextNode();
            if(slowPointer == fastPointer) {
                // Loop exists, now find the starting node of the loop.
                slowPointer = head;
                while(slowPointer != fastPointer) {
                    slowPointer = slowPointer.get_nextNode();
                    fastPointer = fastPointer.get_nextNode();
                }
                return slowPointer;
            }
        }
        return null;
    }
}
