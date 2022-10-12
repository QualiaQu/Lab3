import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

final class Main {
    public static void main(String[] var0){
        Stack<Object> test = new Stack<>();
        test.push(3);
        test.push(2);
        test.push(1);
        test.push(1);
        test.push(1);
        test.push("Top");


        try(FileReader reader = new FileReader("C:\\Users\\Rustam\\IdeaProjects\\Lab3\\src\\input.txt"))
        {
            //1 - Push(elem), 2 - Pop(), 3 - Top(), 4 - isEmpty(), 5 - Print()
            int stepCount = 0;
            Scanner scanner = new Scanner(reader);
            var string = scanner.nextLine();
            var af = string.split(" ");
            Stack<Object> stack = new Stack<>();
            for (var i:af) {
                if(i.charAt(0) == '1')
                {
                    var elem = i.split(",");
                    stack.push(elem[1]);
                    System.out.println(stack.top());
                    stepCount++;
                }
                switch (i) {
                    case ("2") -> {
                        System.out.println(stack.pop());
                        stepCount++;
                    }
                    case ("3") -> {
                        if (!stack.isEmpty()) System.out.println(stack.top());
                        stepCount++;
                    }
                    case ("4") -> {
                        System.out.println(stack.isEmpty());
                        stepCount++;
                    }
                    case ("5") -> {
                        stack.print();
                        stepCount++;
                    }
                }
            }
            System.out.println();
            System.out.println(stepCount);
        }

        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
