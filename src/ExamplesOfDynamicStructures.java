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
//        int []arr = {10, 5, 15, 45};
//        int n = arr.length;
//        sortArrayUsingStacks(arr, n);
//        for (int j : arr) System.out.print(j + " ");
//        String pattern = "AAAAAAA";
//        String text = "AAAAAAAAA";
//
//        List<Integer> foundIndexes = performKMPSearch(text, pattern);
//
//        if (foundIndexes.isEmpty()) {
//            System.out.println("Pattern not found in the given text String");
//        } else {
//            System.out.println("Pattern found in the given text String at positions: " + foundIndexes.stream().map(Object::toString).collect(Collectors.joining(", ")));
//        }

        ProductReceipt receipt = new ProductReceipt();
        receipt.addToReceipt(new Product("хлеб", 30));
        receipt.addToReceipt(new Product("молоко", 50));
        receipt.addToReceipt(new Product("колбаса", 200));
        System.out.println(receipt);
    }
}
class ProductReceipt{
    private int amount;
    private final ArrayList<Product> receipt;
    private Product product;

    public ArrayList<Product> getReceipt(){
        return this.receipt;
    }
    public void addToReceipt(Product product){
        this.receipt.add(product);
    }
    public ProductReceipt() {
        receipt = new ArrayList<>();
    }

    public String toString(){
        StringBuilder result = new StringBuilder("Ваш чек:\n");
        for(var position: this.receipt){
            result.append("Название товара: ").append(position.name()).append(". ").append("Цена = ").append(position.price()).append(" рублей\n");
        }
        return result.toString();
    }
}

record Product(String name, double price) {

}
