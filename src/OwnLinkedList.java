public final class OwnLinkedList<T>{
    private Node<T> tail;
    private int size;
    public void insertFirst(T data) {
        Node<T> newNode = new Node<>();
        newNode.data = data;
        newNode.next = tail;
        tail = newNode;
        size++;
    }
    public void deleteLast() {
        tail = tail.next;
        size--;
    }
    public Node<T> getTail(){
        return this.tail;
    }
    public T getLast() {
        return this.tail.data;
    }
    public void printLn() {
        Node<T> current = tail;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
    public int getSize() {
        return size;
    }
}
