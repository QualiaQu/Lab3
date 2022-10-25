import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ExamplesOfDynamicStructures {


    public static void main(String[] args)
    {
        /* Реализация списка, кассовый аппарат
        ProductReceipt receipt = new ProductReceipt();
        receipt.addToReceipt(new Product("хлеб", 30));
        receipt.addToReceipt(new Product("молоко", 50));
        receipt.addToReceipt(new Product("колбаса", 200));
        System.out.println(receipt);*/

        /* Реализация очереди ,очередь звонков
        CallCenter callCenter = new CallCenter(3);
        callCenter.newClient(new Client("1"));
        callCenter.newClient(new Client("2"));
        callCenter.newClient(new Client("3"));
        callCenter.newClient(new Client("4"));
        callCenter.newClient(new Client("5"));
        callCenter.newClient(new Client("6"));
        callCenter.printQueue();*/

        /* стеллаж с коробками
        BoxesRack rack = new BoxesRack();
        rack.putBox(new Box("1"));
        rack.putBox(new Box("2"));
        rack.putBox(new Box("3"));
        System.out.println(rack.takeBox().getContent());*/

        // сортировка бинарным деревом поиска
        FilmLibrary library = new FilmLibrary();
        library.add(new Film(2001));
        library.add(new Film(2000));
        library.add(new Film(2020));

        int[] arr = new int[library.getList().size()];
        ArrayList<Film> list = library.getList();
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i).getYearOfRelease();
        }
        System.out.println("Года фильмов = " + Arrays.toString(arr));
        Tree tree = new Tree(arr[0]);
        for(int num : arr){
            tree.insert(tree.node, num);
        }
        System.out.println("По возрастанию = ");
        tree.inOrder(tree.node);
        System.out.println();
        System.out.println("По убыванию = ");
        tree.inOrderDesc(tree.node);
    }
}

class FilmLibrary{
    public ArrayList<Film> getList() {
        return list;
    }

    private final ArrayList<Film> list;
    public FilmLibrary(){
        this.list = new ArrayList<>();

    }
    public void add(Film film){
        list.add(film);
    }
}
class Film {
    private final int yearOfRelease;
    public int getYearOfRelease() {
        return yearOfRelease;
    }
    public Film(int yearOfRelease){
        this.yearOfRelease = yearOfRelease;
    }
}
class BoxesRack {
    private final Stack<Box> rack;
    public BoxesRack(){
        this.rack = new Stack<>();
    }
    public void putBox(Box box){
        rack.push(box);
    }
    public Box takeBox(){
        return rack.pop();
    }
}
class Box{
    public String getContent() {
        return content;
    }
    public String content;
    public Box(String content){
        this.content = content;
    }
}

class CallCenter{
    private final int numberOfOperators;
    private int numberOfClients;
    private final OwnQueue<Client> clientQueue;
    public void printQueue(){
        var current = this.clientQueue.copy();
        var queueSize = current.getSize();
        while (queueSize > 0){
            System.out.println(current.getFirst().problem());
            current.removeFirst();
            queueSize--;
        }
    }
    public CallCenter(int numberOfOperators){
        this.numberOfOperators = numberOfOperators;
        clientQueue = new OwnQueue<>();
    }
    public void newClient(Client client){
        if (numberOfClients >= numberOfOperators){
            this.clientQueue.add(client);
        }
        this.numberOfClients++;
    }
    public void callEnded(){
        this.numberOfClients--;
        this.clientQueue.removeFirst();
    }
}

record Client(String problem) {
}

record Product(String name, double price) {
}

class ProductReceipt{
    private final OwnLinkedList<Product> receipt;

    public OwnLinkedList<Product> getReceipt(){
        return this.receipt;
    }
    public void addToReceipt(Product product){
        this.receipt.add(product);
    }
    public ProductReceipt() {
        receipt = new OwnLinkedList<>();
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("Ваш чек:\n");
        var current = receipt.head;
        while(current != null){
            result.append("Название товара: ").append(current.data.name()).append(". ").append("Цена = ").append(current.data.price()).append(" рублей\n");
            current = current.next;
        }
        return result.toString();
    }
}
class BinaryTreeNode{
    int value;
    BinaryTreeNode left;
    BinaryTreeNode right;
    BinaryTreeNode(int value){
        this.value = value;
        left = null;
        right = null;
    }
}

class Tree{
    BinaryTreeNode node;
    Tree(int value){
        node = new BinaryTreeNode(value);
    }
    public BinaryTreeNode insert(BinaryTreeNode node, int value){
        if(node == null){
            return new BinaryTreeNode(value);
        }
        if(value < node.value){
            node.left = insert(node.left, value);
        } else if(value > node.value){
            node.right = insert(node.right, value);
        }
        return node;
    }

    public void inOrder(BinaryTreeNode node){
        if(node != null){
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }
    }

    public void inOrderDesc(BinaryTreeNode node){
        if(node != null){
            inOrderDesc(node.right);
            System.out.print(node.value + " ");
            inOrderDesc(node.left);
        }
    }
}
