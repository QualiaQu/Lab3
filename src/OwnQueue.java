public final class OwnQueue<T>{
    private final OwnDoubleLinkedList<T> queue;

    private int size;

    public OwnQueue() {
        this.queue = new OwnDoubleLinkedList<>();
    }
    public void add(T e) {
        this.queue.add(e);
        size++;
    }

    public T getFirst() {
        return this.queue.getHead();
    }
    public T getLast() {
        return this.queue.getTail();
    }

    public void removeFirst(){
        this.queue.removeHead();
        size--;
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
    public void printFirst(){
        if (isEmpty()) System.out.println("the queue is empty");
        else System.out.println(getFirst());;
    }
    public void printLn(){
        if (isEmpty()) System.out.println("the queue is empty");
        else {
            var temp = this.queue;
            StringBuilder result = new StringBuilder();
            while(temp.getSize() != 0){
                result.append(getFirst()).append(" -> ");
                temp.removeHead();
            }
            result.delete(result.length() - 4, result.length());
            System.out.println(result);
        }
    }
}
