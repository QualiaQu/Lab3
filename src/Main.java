import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Arrays;

final class Main {
    public static void main(String[] args) {
        StringBuilder results = new StringBuilder("Объём данных;Время (миллисекунды)\n");

        try (FileReader reader = new FileReader((new File("10 млн с пушем.txt")).getAbsolutePath())) {
            Scanner scanner = new Scanner(reader);
            String[] commands = scanner.nextLine().split(" ");
            System.out.println(commands.length);
            int count = 0;
            for (int i = 100000; i <= commands.length; i += 100000) {
                count++;
                String[] resizedArray = Arrays.copyOf(commands, i);
                long start = System.currentTimeMillis();
                System.out.println(count);
                doDefaultQueueOperation(resizedArray);
                long end = System.currentTimeMillis();
                results.append(String.format("%d; %d \n", i, end - start));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            File myFoo = new File("(DefaultQueue)10 млн с пушем.txt");
            FileWriter fooWriter = new FileWriter(myFoo, false);
            fooWriter.write(results.toString());
            fooWriter.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        /*OwnDoubleLinkedList<Object> list = new OwnDoubleLinkedList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.printLn();

        Menu.Start(list);*/
    }

    static void doQueueOperation(File file) {
        try (FileReader reader = new FileReader(file.getAbsolutePath())) {
            Scanner scanner = new Scanner(reader);
            var commands = scanner.nextLine().split(" ");
            OwnQueue<Object> ownQueue = new OwnQueue<>();
            for (var i : commands) {
                if (i.charAt(0) == '1') {
                    ownQueue.add(i.split(",")[1]);
                    System.out.println(ownQueue.getLast());
                } else {
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
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void calculatePostfix(File file) {
        try (FileReader reader = new FileReader(file.getAbsolutePath())) {
            Scanner scanner = new Scanner(reader);
            var commands = scanner.nextLine().split(" ");
            OwnStack<Double> ownStack = new OwnStack<>();
            for (var i : commands) {
                if (i.charAt(0) >= '0' && i.charAt(0) <= '9') {
                    ownStack.push(Double.parseDouble(i));
                } else {
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
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void doStackOperation(String[] commands) {
        OwnStack<Object> ownStack = new OwnStack<>();
        for (var i : commands) {
            if (i.charAt(0) == '1') {
                ownStack.push(i.split(",")[1]);
                //System.out.println(ownStack.peek());
            }
            switch (i) {
                case ("2") -> {
                    if (!ownStack.isEmpty()) ownStack.pop();
                }
                case ("3") -> {
                    if (!ownStack.isEmpty())  ownStack.peek();
                        //System.out.println(ownStack.peek());
                    //else System.out.println("Stack is empty");
                }
                case ("4") -> {
                    //System.out.println(ownStack.isEmpty());
                    ownStack.isEmpty();
                }
                case ("5") -> {
                    //System.out.println("------");
                    ownStack.printLn();
                    //System.out.println("------");
                }
            }
        }
    }
    static void doQueueOperation(String[] commands) {
        OwnQueue<Object> ownQueue = new OwnQueue<>();
        for (var i : commands) {
            if (i.charAt(0) == '1') {
                ownQueue.add(i.split(",")[1]);
                ownQueue.getLast();
            } else {
                switch (i) {
                    case "2" -> {
                        if (ownQueue.isEmpty()) continue;
                        else {
                            var deleted = ownQueue.getFirst();
                            ownQueue.removeFirst();
                            //System.out.println("deleted -> " + deleted);
                        }
                    }
                    case "3" -> ownQueue.printFirst();
                    //case "4" -> System.out.println(ownQueue.isEmpty());
                    case "4" -> ownQueue.isEmpty();
                    case "5" -> ownQueue.printLn();
                }
            }
        }
    }
    static void doDefaultQueueOperation(String[] commands) {
        ArrayDeque<Object> queue = new ArrayDeque<>();
        for (var i : commands) {
            if (i.charAt(0) == '1') {
                queue.add(i.split(",")[1]);
                queue.getLast();
            } else {
                switch (i) {
                    case "2" -> {
                        if (queue.isEmpty()) continue;
                        else {
                            var deleted = queue.getFirst();
                            queue.removeFirst();
                            //System.out.println("deleted -> " + deleted);
                        }
                    }
                    case "3" -> {
                        if (!queue.isEmpty()) queue.getFirst();
                    }
                    //case "4" -> System.out.println(ownQueue.isEmpty());
                    case "4" -> queue.isEmpty();
                    case "5" -> queue.toString();
                }
            }
        }
    }

    static void doStackOperation(File file) {
        try (FileReader reader = new FileReader(file.getAbsolutePath())) {
            int stepCount = 0;
            Scanner scanner = new Scanner(reader);
            var commands = scanner.nextLine().split(" ");
            OwnStack<Object> ownStack = new OwnStack<>();
            for (var i : commands) {
                if (i.charAt(0) == '1') {
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
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
