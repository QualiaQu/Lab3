public final class OwnQueue<T>{
    private final OwnDoubleLinkedList<T> queue;


    public OwnQueue() {
        this.queue = new OwnDoubleLinkedList<>();
    }
    public void add(T e) {
        this.queue.Add(e);
    }

    public T getFirst() {
        return this.queue.getHead();
    }

    public T getLast(){
        return this.queue.getTail();
    }

}
