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
    public static void main(String[] args)
    {
        int []arr = {10, 5, 15, 45};
        int n = arr.length;
        sortArrayUsingStacks(arr, n);
        for (int j : arr) System.out.print(j + " ");
    }
}
