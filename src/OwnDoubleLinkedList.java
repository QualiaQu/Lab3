public class OwnDoubleLinkedList<T>{
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
    public void clear()
    {
        head = null;
        tail = null;
        size = 0;
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
            var start = this;
            var current = this.head;
            while (current != null) {
                var data = current.data;
                var temp = start.head;
                int count = 0;
                while (temp != null){
                    if (data == temp.data) count++;
                    temp = temp.next;
                }
                if (count == 1) result++;
                current = current.next;
            }
        }
        else System.out.println("list is empty");

        return result;
    }

    public void deleteSecond(){
        if (!isEmpty()){
            boolean exit = false;
            var start = this;
            var current = this.head;
            while (current != null){
                var data = current.data;
                var temp = start.head;
                int count = 0;
                while (temp != null){
                    if (data == temp.data) count++;
                    if (count == 2) {
                        temp.prev.next = temp.next;
                        if(temp.next != null) temp.next.prev = temp.prev;
                        temp.next = null;
                        temp.prev = null;
                        size--;
                        count = 0;
                        exit = true;
                    }
                    temp = temp.next;
                    if(exit) break;
                }
                current = current.next;
            }
        }
        else System.out.println("list is empty");
    }
    public void replaceElements(T firstElement, T secondElement ){
        var currentNode = tail;
        for (int i = 0; i < getSize(); i++)
        {
            if (currentNode.data == firstElement) {
                currentNode.data = secondElement;
            }
            else if (currentNode.data == secondElement){
                currentNode.data = firstElement;
            }
            currentNode = currentNode.prev;
        }
    }
    public void removeAll(T element){
        if (!isEmpty()){
            var current = this.head;
            while (current != null){
                if (current.data == element){
                    if(current.next != null) current.next.prev = current.prev;
                    else tail = current.prev;
                    if(current.prev != null) current.prev.next = current.next;
                    else head = current.next;
                    size--;
                }
                current = current.next;
            }
        }
        else System.out.println("list is empty");
    }

    public void addList(OwnDoubleLinkedList<T> list){
        var current = list.head;
        while (current != null){
            this.add(current.data);
            current = current.next;
        }
    }
    private OwnDoubleLinkedList<T> copy(){
        OwnDoubleLinkedList<T> resultList = new OwnDoubleLinkedList<>();
        if (!isEmpty()){
            var current = this.head;
            while (current != null){
                resultList.add(current.data);
                current = current.next;
            }
        }
        else System.out.println("list is empty");
        return resultList;
    }

    public void doubleList(){
        if (!isEmpty()){
            var duplicate = this.copy().head;
            while (duplicate != null){
                this.add(duplicate.data);
                duplicate = duplicate.next;
            }
        }
        else System.out.println("list is empty");
    }

    public void insertListAfter(T x){
        var copy = this.copy();
        this.clear();
        if (!copy.isEmpty()){
            var current = copy.copy();
            while (current.head != null){
                this.add(current.head.data);
                if (current.head.data == x){
                    OwnDoubleLinkedList<T> secondPart = new OwnDoubleLinkedList<>();
                    var temp = current.head.next;
                    while (temp != null){
                        secondPart.add(temp.data);
                        temp = temp.next;
                    }
                    this.addList(copy);
                    this.addList(secondPart);
                    break;
                }
                current.head = current.head.next;
            }
        }
        else System.out.println("list is empty");
    }

    public OwnDoubleLinkedList[] splitByTwo(T x){
        OwnDoubleLinkedList[] result = new OwnDoubleLinkedList[2];
        var copy = this.copy();
        this.clear();
        boolean exit = false;
        if (!copy.isEmpty()){
            var current = copy.copy();
            while (current.head != null){
                this.add(current.head.data);
                if (current.head.data == x){
                    OwnDoubleLinkedList<T> secondPart = new OwnDoubleLinkedList<>();
                    var temp = current.head.next;
                    while (temp != null){
                        secondPart.add(temp.data);
                        temp = temp.next;
                    }
                    result[0] = this;
                    result[1] = secondPart;
                    exit = true;
                    break;
                }
                current.head = current.head.next;
            }
            if (!exit){
                result[0] = copy;
                result[1] = new OwnDoubleLinkedList();
            }
        }
        else System.out.println("list is empty");
        return result;
    }

    public void insertInOrder(T x){
        if (!isEmpty()){
            var current = this.copy().head;
            this.clear();
            while (current != null){
                this.add(current.data);
                if (current.next != null && current.data == x && current.next.data != x){
                    this.add(x);
                } else if (current.next == null && current.data == x) {
                    this.add(x);
                } else if (current.next != null && (int)current.data < (int)x && ((int)current.next.data > (int)x)) {
                    this.add(x);
                } else if (current.next == null && (int)current.data < (int)x) {
                    this.add(x);
                }
                current = current.next;
            }
            if ((int)this.head.data > (int)x) {
                this.insertFirst(x);
            }
        }
        else System.out.println("list is empty");
    }

    public void insertFBeforeE(T elem, T before){
        if (!isEmpty()){
            var current = this.copy().head;
            this.clear();
            boolean insertDone = false;
            while (current != null){
                if (insertDone){
                    this.add(current.data);
                }
                else {
                    if (current.data == before) {
                        this.add(elem);
                        this.add(current.data);
                        insertDone = true;
                    } else this.add(current.data);
                }
                current = current.next;
            }
        }
        else System.out.println("list is empty");
    }
}
