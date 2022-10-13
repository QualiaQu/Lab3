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
        this.stack.deleteLast();
        return res;
    }
    public void print(){
        var temp = this.stack;
        for (int i = 0; i <= temp.getSize(); i++){
            System.out.println(temp.getLast());
            temp.deleteLast();
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

