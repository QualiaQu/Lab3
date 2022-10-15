public class OwnDoubleLinkedList<T> {
    static class DoubleNode<T> {
        DoubleNode<T> prev;
        DoubleNode<T> next;
        T data;


    }

    private DoubleNode<T> head;
    private DoubleNode<T> tail;
    private int size;

    public void add(T data) {
        DoubleNode<T> doubleNode = new DoubleNode<T>();
        doubleNode.data = data;

        if (head == null)
            head = doubleNode;
        else {
            tail.next = doubleNode;
            doubleNode.prev = tail;
        }
        tail = doubleNode;
        size++;
    }
    public void insertFirst(T data){
        DoubleNode<T> node = new DoubleNode<T>();
        node.data = data;
        node.next = this.head;
        this.head = node;
        if (size == 0)
            tail = head;
        size++;
    }

    public T getHead() {
        return this.head.data;
    }
    public T getTail() {
        return this.tail.data;
    }
    void removeHead(){
        this.head = this.head.next;
        size--;
    }
    public int getSize(){
        return this.size;
    }
    public boolean isEmpty(){
        return getSize() == 0;
    }

    public void reverse(){
        var node = this.head;
        DoubleNode<T> previous = null;
        while(node != null){
            var tmp = node.next;
            node.next = previous;
            previous = node;
            this.head = node;
            node = tmp;
        }
    }

    public void printLn() {
        if (!isEmpty()) {
            var current = head;
            while (current != null) {
                System.out.println(current.data);
                current = current.next;
            }
        }
        else System.out.println("list is empty");
    }

    public void moveHeadToTail(){
        if (!isEmpty()){
            var head = this.head;
            add(head.data);
        }
        else System.out.println("list is empty");
    }
    public void moveTailToHead(){
        if (!isEmpty()){
            var tail = this.tail;
            insertFirst(tail.data);
        }
        else System.out.println("list is empty");
    }
    public int getUnique(){
        int result = 0;
        if (!isEmpty()) {
            var start = this.head;
            var current = this.head;
            while (current != null) {
                var temp = current.data;
                var current2 = current.next;
                int count = 0;
                while (current2 != null){
                    if (temp == current2.data) break;
                    else count++;
                    current2 = current2.next;
                }
                if (count == this.getSize() - 1) result++;
                current = current.next;
            }
        }
        else System.out.println("list is empty");

        return result;
    }
}
