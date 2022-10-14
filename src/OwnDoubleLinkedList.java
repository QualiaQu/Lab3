public class OwnDoubleLinkedList<T> {
    private static class DoubleNode<T> {
        DoubleNode<T> prev;
        DoubleNode<T> next;
        T data;

        public DoubleNode(T data) {
            this.data = data;
        }
    }

    private DoubleNode<T> head;
    private DoubleNode<T> tail;
    private int size;

    public void Add(T data) {
        DoubleNode<T> doubleNode = new DoubleNode<T>(data);

        if (head == null)
            head = doubleNode;
        else {
            tail.next = doubleNode;
            doubleNode.prev = tail;
        }
        tail = doubleNode;
        size++;
    }

    public T getHead() {
        return this.head.data;
    }

    public T getTail() {
        return this.tail.data;
    }
}
