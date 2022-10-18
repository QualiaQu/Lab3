import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExamplesOfDynamicStructures {
    static OwnStack<Integer> sortStack(OwnStack<Integer> input)
    {
        OwnStack<Integer> tmpStack = new OwnStack<>();

        while (!input.isEmpty())
        {
            int tmp = input.peek();
            input.pop();

            while (!tmpStack.isEmpty() &&
                    tmpStack.peek() < tmp)
            {
                input.push(tmpStack.peek());
                tmpStack.pop();
            }

            tmpStack.push(tmp);
        }

        return tmpStack;
    }

    static void sortArrayUsingStacks(int []arr, int n)
    {
        OwnStack<Integer> input = new OwnStack<Integer>();
        for (int i = 0; i < n; i++) input.push(arr[i]);

        OwnStack<Integer> tmpStack = sortStack(input);

        for (int i = 0; i < n; i++)
        {
            arr[i] = tmpStack.peek();
            tmpStack.pop();
        }
    }
    public static int[] compilePatternArray(String pattern) {
        int patternLength = pattern.length();
        int len = 0;
        int i = 1;
        int[] compliedPatternArray = new int[patternLength];
        compliedPatternArray[0] = 0;

        while (i < patternLength) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                compliedPatternArray[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = compliedPatternArray[len - 1];
                } else {
                    compliedPatternArray[i] = len;
                    i++;
                }
            }
        }
        System.out.println("Compiled Pattern Array " + Arrays.toString(compliedPatternArray));
        return compliedPatternArray;
    }
    public static List<Integer> performKMPSearch(String text, String pattern) {
        int[] compliedPatternArray = compilePatternArray(pattern);

        int textIndex = 0;
        int patternIndex = 0;

        List<Integer> foundIndexes = new ArrayList<>();

        while (textIndex < text.length()) {
            if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
                patternIndex++;
                textIndex++;
            }
            if (patternIndex == pattern.length()) {
                foundIndexes.add(textIndex - patternIndex);
                patternIndex = compliedPatternArray[patternIndex - 1];
            }

            else if (textIndex < text.length() && pattern.charAt(patternIndex) != text.charAt(textIndex)) {
                if (patternIndex != 0)
                    patternIndex = compliedPatternArray[patternIndex - 1];
                else
                    textIndex = textIndex + 1;
            }
        }
        return foundIndexes;
    }
    public static void main(String[] args)
    {
        /* Сортировка стеком
        int []arr = {10, 5, 15, 45};
        int n = arr.length;
        sortArrayUsingStacks(arr, n);
        for (int j : arr) System.out.print(j + " ");
        String pattern = "AAAAAAA";
        String text = "AAAAAAAAA";

        List<Integer> foundIndexes = performKMPSearch(text, pattern);

        if (foundIndexes.isEmpty()) {
            System.out.println("Pattern not found in the given text String");
        } else {
            System.out.println("Pattern found in the given text String at positions: " + foundIndexes.stream().map(Object::toString).collect(Collectors.joining(", ")));
        }*/

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

        /* сортировка бинарным деревом поиска
        int[] arr = {50, 30, 70, 15, 7, 62, 22, 35, 87, 22, 31};
        System.out.println("Original array- " + Arrays.toString(arr));
        Tree tree = new Tree(arr[0]);
        for(int num : arr){
            tree.insert(tree.node, num);
        }
        System.out.println("Sorted Array (Ascending)- ");
        tree.inOrder(tree.node);
        System.out.println();
        System.out.println("Sorted Array (Descending)- ");
        tree.inOrderDesc(tree.node);*/
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

final class Client {
    private final String problem;

    Client(String problem) {
        this.problem = problem;
    }

    public String problem() {
        return problem;
    }
}

final class Product {
    private final String name;
    private final double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String name() {
        return name;
    }

    public double price() {
        return price;
    }
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
