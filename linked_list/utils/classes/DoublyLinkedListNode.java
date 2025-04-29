package linked_list.utils.classes;

import linked_list.utils.interfaces.LinkedListNode;

public class DoublyLinkedListNode implements LinkedListNode {
    private DoublyLinkedListNode _prevNode;
    private DoublyLinkedListNode _nextNode;
    private int _data;

    public DoublyLinkedListNode(int data) {
        _data = data;
        _prevNode = null;
        _nextNode = null;
    }

    public DoublyLinkedListNode get_prevNode() {
        return _prevNode;
    }

    public void set_prevNode(DoublyLinkedListNode prevNode) {
        this._prevNode = prevNode;
    }

    public DoublyLinkedListNode get_nextNode() {
        return _nextNode;
    }

    public void set_nextNode(DoublyLinkedListNode nextNode) {
        this._nextNode = nextNode;
    }

    public int get_data() {
        return _data;
    }

    public void set_data(int data) {
        this._data = data;
    }
}
