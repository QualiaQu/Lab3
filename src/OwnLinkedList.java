public final class OwnLinkedList<T>{
    private Node<T> head;
    private int size;
    public void insertFirst(T data) {
        Node<T> newNode = new Node<>();
        newNode.data = data;
        newNode.next = head;
        head = newNode;
        size++;
    }
    public void deleteLast() {
        head = head.next;
        size--;
    }
    public T getLast() {
        return this.head.data;
    }
    public void printLinkedList() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
    public int getSize() {
        return size;
    }
}
