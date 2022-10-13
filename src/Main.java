import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

final class Main {
    public static void main(String[] args){
        doStackOperation(new File("input.txt"));
    }
    static void doStackOperation(File file){

        try(FileReader reader = new FileReader(file.getAbsolutePath()))
        {
            int stepCount = 0;
            Scanner scanner = new Scanner(reader);
            var commands = scanner.nextLine().split(" ");
            Stack<Object> stack = new Stack<>();
            for (var i:commands) {
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
                        else System.out.println("Stack is empty");
                        stepCount++;
                    }
                    case ("4") -> {
                        System.out.println(stack.isEmpty());
                        stepCount++;
                    }
                    case ("5") -> {
                        System.out.println("------");
                        stack.print();
                        System.out.println("------");
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
