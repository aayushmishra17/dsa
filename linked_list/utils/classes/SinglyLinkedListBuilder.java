package linked_list.utils.classes;

import linked_list.utils.interfaces.LinkedListBuilder;
import linked_list.utils.interfaces.LinkedListNode;

public class SinglyLinkedListBuilder implements LinkedListBuilder {

    @Override
    public LinkedListNode build(int[] values) {
        if(values.length == 0) {
            return null;
        }

        SinglyLinkedListNode head = new SinglyLinkedListNode(values[0]);
        SinglyLinkedListNode temp = head;
        for(int index = 1; index < values.length; index++) {
            SinglyLinkedListNode newNode = new SinglyLinkedListNode(values[index]);
            temp.set_nextNode(newNode);
            temp = newNode;
        }

        return head;
    }
}
