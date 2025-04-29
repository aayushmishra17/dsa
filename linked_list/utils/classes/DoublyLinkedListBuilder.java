package linked_list.utils.classes;

import linked_list.utils.interfaces.LinkedListBuilder;
import linked_list.utils.interfaces.LinkedListNode;

public class DoublyLinkedListBuilder implements LinkedListBuilder {

    @Override
    public LinkedListNode build(int[] values) {
        if(values.length == 0) {
            return null;
        }

        DoublyLinkedListNode head = new DoublyLinkedListNode(values[0]);
        DoublyLinkedListNode temp = head;
        for(int index = 1; index < values.length; index++) {
            DoublyLinkedListNode newNode = new DoublyLinkedListNode(values[index]);
            temp.set_nextNode(newNode);
            newNode.set_prevNode(temp);
            temp = newNode;
        }

        return head;
    }
}
