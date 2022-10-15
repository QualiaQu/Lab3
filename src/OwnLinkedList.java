public final class OwnLinkedList<T>{
    public Node<T> head;
    public Node<T> tail;
    private int size;

    public void insertFirst(T data){
        Node<T> node = new Node<T>();
        node.data = data;
        node.next = this.head;
        this.head = node;
        if (size == 0)
            tail = head;
        size++;
    }

    public int getSize() {
        return size;
    }

    public void printLn() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public void add(T data){
        Node<T> node = new Node<>();
        node.data = data;
        if (head == null) head = node;
        else tail.next = node;
        tail = node;

        size++;
    }

    public void reverse(){
        var node = this.head;
        Node<T> previous = null;
        while(node != null){
            var tmp = node.next;
            node.next = previous;
            previous = node;
            this.head = node;
            node = tmp;
        }
    }
}