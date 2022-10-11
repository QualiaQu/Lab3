import java.util.ArrayList;
import java.util.Collections;

public final class Stack<T> {
    private final ArrayList<T> stack;
    private int top;
    private final int size;

    public void push(T elem) {
        ++this.top;
        this.stack.add(this.top, elem);
    }

    public T pop() {
        int elem;
        this.top = (elem = this.top) - 1;
        return this.stack.get(elem);
    }
    public void print(){
        Collections.reverse(this.stack);
        for(var elem: this.stack){
            System.out.println(elem);
        }
        Collections.reverse(this.stack);
    }
    public T top() {
        return this.stack.get(this.top);
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public boolean isFull() {
        return this.top == this.size - 1;
    }
    public Stack(int size) {
        this.size = size;
        this.stack = new ArrayList<>(size);
        this.top = -1;
    }
}
