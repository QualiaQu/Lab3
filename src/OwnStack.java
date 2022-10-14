public final class OwnStack<T> {
    private final OwnLinkedList<T> stack;
    private int top;
    private int size;
    public OwnStack() {
        this.stack = new OwnLinkedList<>();
        this.top = -1;
    }
    public void push(T elem) {
        ++this.top;
        this.stack.insertFirst(elem);
        size++;
    }

    public T pop() {
        T res = this.stack.getLast();
        this.stack.deleteLast();
        this.top--;
        return res;
    }
    public void printLn(){
        this.stack.printLn();
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

