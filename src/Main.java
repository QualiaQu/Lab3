import org.w3c.dom.css.ElementCSSInlineStyle;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

final class Main {
    public static void main(String[] args){
        //doStackOperation(new File("inputStack.txt"));
        //calculatePostfix(new File("4task.txt"));
        //doQueueOperation(new File("inputQueue.txt"));

        OwnDoubleLinkedList<Integer> list = new OwnDoubleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list.getUnique());


        System.out.println("TEST");
        System.out.println("TEST");
    }

    static void doQueueOperation(File file){
        try(FileReader reader = new FileReader(file.getAbsolutePath()))
        {
            Scanner scanner = new Scanner(reader);
            var commands = scanner.nextLine().split(" ");
            OwnQueue<Object> ownQueue = new OwnQueue<>();
            for (var i:commands)
            {
                if(i.charAt(0) == '1')
                {
                    ownQueue.add(i.split(",")[1]);
                    System.out.println(ownQueue.getLast());
                }
                else
                {
                    switch (i) {
                            case "2" -> {
                                if (ownQueue.isEmpty()) System.out.println("the queue is empty");
                                else {
                                    var deleted = ownQueue.getFirst();
                                    ownQueue.removeFirst();
                                    System.out.println("deleted -> " + deleted);
                                }
                            }
                            case "3" -> ownQueue.printFirst();
                            case "4" -> System.out.println(ownQueue.isEmpty());
                            case "5" -> ownQueue.printLn();
                    }
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    static void calculatePostfix(File file){
        try(FileReader reader = new FileReader(file.getAbsolutePath()))
        {
            Scanner scanner = new Scanner(reader);
            var commands = scanner.nextLine().split(" ");
            OwnStack<Double> ownStack = new OwnStack<>();
            for (var i:commands)
            {
                if (i.charAt(0) >= '0' && i.charAt(0) <= '9')
                {
                    ownStack.push(Double.parseDouble(i));
                }
                else
                {
                    switch (i) {
                        case "+" -> ownStack.push(ownStack.pop() + ownStack.pop());
                        case "-" -> {
                            var a = ownStack.pop();
                            var b = ownStack.pop();
                            ownStack.push(b - a);
                        }
                        case "*" -> ownStack.push(ownStack.pop() * ownStack.pop());
                        case "/", ":" -> {
                            var a = ownStack.pop();
                            var b = ownStack.pop();
                            ownStack.push(b / a);
                        }
                        case "^" -> {
                            var a = ownStack.pop();
                            var b = ownStack.pop();
                            ownStack.push(Math.pow(b, a));
                        }
                        case "ln" -> {
                            var a = ownStack.pop();
                            ownStack.push(Math.log(a));
                        }
                        case "cos" -> ownStack.push(Math.cos(ownStack.pop()));
                        case "sin" -> ownStack.push(Math.sin(ownStack.pop()));
                        case "sqrt" -> ownStack.push(Math.sqrt(ownStack.pop()));
                    }
                }
            }
            System.out.println(ownStack.pop());
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
            OwnStack<Object> ownStack = new OwnStack<>();
            for (var i:commands) {
                if(i.charAt(0) == '1')
                {
                    ownStack.push(i.split(",")[1]);
                    System.out.println(ownStack.top());
                    stepCount++;
                }
                switch (i) {
                    case ("2") -> {
                        System.out.println(ownStack.pop());
                        stepCount++;
                    }
                    case ("3") -> {
                        if (!ownStack.isEmpty()) System.out.println(ownStack.top());
                        else System.out.println("Stack is empty");
                        stepCount++;
                    }
                    case ("4") -> {
                        System.out.println(ownStack.isEmpty());
                        stepCount++;
                    }
                    case ("5") -> {
                        System.out.println("------");
                        ownStack.printLn();
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
