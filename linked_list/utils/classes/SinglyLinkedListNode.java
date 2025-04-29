package linked_list.utils.classes;

import linked_list.utils.interfaces.LinkedListNode;

public class SinglyLinkedListNode implements LinkedListNode {
    private SinglyLinkedListNode _nextNode;
    private int _data;

    public SinglyLinkedListNode(int data) {
        _data = data;
        _nextNode = null;
    }

    public SinglyLinkedListNode get_nextNode() {
        return _nextNode;
    }

    public void set_nextNode(SinglyLinkedListNode nextNode) {
        this._nextNode = nextNode;
    }

    public int get_data() {
        return _data;
    }

    public void set_data(int data) {
        this._data = data;
    }
}
