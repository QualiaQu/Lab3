import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

final class Main {
    public static void main(String[] args) throws IOException {
        /*doStackOperation(new File("inputStack.txt"));
        calculatePostfix(new File("4task.txt"));
        doQueueOperation(new File("inputQueue.txt"));
        var firstList = readDoubleLinkedList(new File("firstList.txt"));
        var secondList = readDoubleLinkedList(new File("secondList.txt"));
        firstList.addList(secondList);
        firstList.printLn();*/
        /* task10
        OwnDoubleLinkedList[] result;
        OwnDoubleLinkedList<Object> list = new OwnDoubleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        result = list.splitByTwo(2);
        result[0].printLn();
        System.out.println();
        result[1].printLn();*/
//        OwnDoubleLinkedList<Integer> list = new OwnDoubleLinkedList<>();
//        list.add(1);
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(5);
//        list.insertFBeforeE(1000,6);
//        list.printLn();
//
//        var firstList = readDoubleLinkedList(new File("firstList.txt"));
//        var secondList = readDoubleLinkedList(new File("secondList.txt"));
//        firstList.addList(secondList);
        infixToPostfix("");
        calculatePostfix(new File("4task.txt"));
    }


    static OwnDoubleLinkedList<Object> readDoubleLinkedList(File file){
        OwnDoubleLinkedList<Object> resultList = new OwnDoubleLinkedList<>();
        try(FileReader reader = new FileReader(file.getAbsolutePath()))
        {
            Scanner scanner = new Scanner(reader);
            var objects = scanner.nextLine().split(" ");
            for (var object: objects){
                resultList.add(object);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return resultList;
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
    static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    static void infixToPostfix(String expression) throws IOException {
        var tokens = expression.split(" ");
        OwnStack<String> stack = new OwnStack<>();
        StringBuilder result = new StringBuilder();
        for (var c:tokens) {
            if (tryParseInt(c)) {
                result.append(c).append(" ");
            }
            if (Objects.equals(c, "(")) {
                stack.push(c);
            }
            if (Objects.equals(c, ")")) {
                while (stack.getSize() != 0 && !Objects.equals(stack.peek(), "(")) {
                    result.append(stack.pop()).append(" ");
                }
                stack.pop();
            }
            if (isOperator(c)) {
                while (stack.getSize() != 0 && Priority(stack.peek()) >= Priority(c)) {
                    result.append(stack.pop()).append(" ");
                }
                stack.push(c);
            }
        }
        while (stack.getSize() != 0) result.append(stack.pop()).append(" ");
        File myFoo = new File("4task.txt");
        FileWriter fooWriter = new FileWriter(myFoo, false);
        fooWriter.write(result.toString());
        fooWriter.close();
    }
    static int Priority(String c) {
        if (Objects.equals(c, "^")) {
            return 3;
        } else if (Objects.equals(c, "*") || Objects.equals(c, "/")) {
            return 2;
        } else if (Objects.equals(c, "+") || Objects.equals(c, "-")) {
            return 1;
        } else {
            return 0;
        }
    }
    static boolean isOperator(String c) {
        return Objects.equals(c, "+") || Objects.equals(c, "-") || Objects.equals(c, "*") ||
                Objects.equals(c, "/") || Objects.equals(c, "^");
    }

    static void doStackOperation(File file){
        try(FileReader reader = new FileReader(file.getAbsolutePath())) {
            int stepCount = 0;
            Scanner scanner = new Scanner(reader);
            var commands = scanner.nextLine().split(" ");
            OwnStack<Object> ownStack = new OwnStack<>();
            for (var i:commands) {
                if(i.charAt(0) == '1') {
                    ownStack.push(i.split(",")[1]);
                    System.out.println(ownStack.peek());
                    stepCount++;
                }
                switch (i) {
                    case ("2") -> {
                        System.out.println(ownStack.pop());
                        stepCount++;
                    }
                    case ("3") -> {
                        if (!ownStack.isEmpty()) System.out.println(ownStack.peek());
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
