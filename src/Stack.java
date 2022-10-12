class Node<T>{
    T data;
    Node<T> next;
    public void displayNodeData() {
        System.out.print( data +  " -> ");
    }
}
class LinkedList<T>{
    private Node<T> head;
    private int size;
    public void insertFirst(T data) {
        Node<T> newNode = new Node<>();
        newNode.data = data;
        newNode.next = head;
        head = newNode;
        size++;
    }
    public void deleteFirst() {
        head = head.next;
        size--;
    }
    public T getLast(){
        return this.head.data;
    }
    public void printLinkedList() {
        Node<T> current = head;
        while (current != null) {
            current.displayNodeData();
            current = current.next;
        }
        System.out.print("NULL");
    }
    public int getSize(){
        return size;
    }
}
public final class Stack<T> {
    private final LinkedList<T> stack;
    private int top;
    private int size;
    public Stack() {
        this.stack = new LinkedList<>();
        this.top = -1;
    }
    public void push(T elem) {
        ++this.top;
        this.stack.insertFirst(elem);
        size++;
    }

    public T pop() {
        T res = this.stack.getLast();
        this.stack.deleteFirst();
        return res;
    }
    public void print(){
        var temp = this.stack;
        for (int i = 0; i <= temp.getSize(); i++){
            System.out.println(temp.getLast());
            temp.deleteFirst();
        }
    }
    public T top() {
        return this.stack.getLast();
    }

    public boolean isEmpty() {
        return this.top == -1;
    }
    public int getSize(){
        return size;
    }
}

