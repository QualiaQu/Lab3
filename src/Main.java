import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

final class Main {
    public static void main(String[] args){
        //doStackOperation(new File("input.txt"));

        calculatePostfix(new File("4task.txt"));
    }


    static void calculatePostfix(File file){
        try(FileReader reader = new FileReader(file.getAbsolutePath()))
        {
            Scanner scanner = new Scanner(reader);
            var commands = scanner.nextLine().split(" ");
            Stack<Double> stack = new Stack<>();
            for (var i:commands)
            {
                if (i.charAt(0) >= '0' && i.charAt(0) <= '9')
                {
                    stack.push(Double.parseDouble(i));
                }
                else
                {
                    switch (i) {
                        case "+" -> stack.push(stack.pop() + stack.pop());
                        case "-" -> {
                            var a = stack.pop();
                            var b = stack.pop();
                            stack.push(b - a);
                        }
                        case "*" -> stack.push(stack.pop() * stack.pop());
                        case "/", ":" -> {
                            var a = stack.pop();
                            var b = stack.pop();
                            stack.push(b / a);
                        }
                        case "^" -> {
                            var a = stack.pop();
                            var b = stack.pop();
                            stack.push(Math.pow(b, a));
                        }
                        case "ln" -> {
                            var a = stack.pop();
                            stack.push(Math.log(a));
                        }
                        case "cos" -> stack.push(Math.cos(stack.pop()));
                        case "sin" -> stack.push(Math.sin(stack.pop()));
                        case "sqrt" -> stack.push(Math.sqrt(stack.pop()));
                    }
                }
            }
            System.out.println(stack.pop());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
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
                    stack.push(i.split(",")[1]);
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
