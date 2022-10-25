public final class OwnQueue<T>{
    private final OwnDoubleLinkedList<T> queue;

    public int getSize() {
        return size;
    }

    private int size;

    public OwnQueue() {
        this.queue = new OwnDoubleLinkedList<>();
    }
    public void add(T e) {
        this.queue.add(e);
        size++;
    }

    public T getFirst() {
        return this.queue.getHeadData();
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
        if (!isEmpty()) getFirst();
    }
    public void printLn(){
        if (!isEmpty()) {
            var temp = this.queue;
            StringBuilder result = new StringBuilder();
            while(temp.getSize() != 0){
                result.append(getFirst()).append(" -> ");
                temp.removeHead();
            }
            result.delete(result.length() - 4, result.length());
            //System.out.println(result);
        }
    }

    public OwnQueue<T> copy() {
        OwnQueue<T> resultQueue = new OwnQueue<>();
        if (!isEmpty()) {
            var current = this.queue.getHead();
            while (current != null) {
                resultQueue.add(current.data);
                current = current.next;
            }
        }
        return resultQueue;
    }
}
