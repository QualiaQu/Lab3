public final class OwnQueue<T>{
    private final OwnDoubleLinkedList<T> queue;

    private int size;

    public OwnQueue() {
        this.queue = new OwnDoubleLinkedList<>();
    }
    public void add(T e) {
        this.queue.Add(e);
        size++;
    }

    public T getFirst() {
        return this.queue.getHead();
    }

    public void removeFirst(){
        this.queue.removeHead();
        size--;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
    public void printFirst(){
        if (isEmpty()) System.out.println("the queue is empty");
        else System.out.println(getFirst());;
    }
    public void printLn(){
        if (isEmpty()) System.out.println("the queue is empty");
        else {
            StringBuilder result = new StringBuilder();
            while(this.queue.getSize() != 0){
                result.append(getFirst()).append(" -> ");
                removeFirst();
            }
            result.delete(result.length() - 4, result.length());
            System.out.print(result);
        }
    }
}
