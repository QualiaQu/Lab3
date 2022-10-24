import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public static void Start(OwnDoubleLinkedList<Object> list) {
        boolean exit = true;
        while (exit) {
            System.out.println("Исходный лист:");
            list.printLn();
            System.out.println("Введите номер задачи:");
            Scanner in = new Scanner(System.in);
            String a = in.nextLine();
            switch (a) {
                case "1" -> {
                    list.reverse();
                    list.printLn();
                }
                case "2" -> {
                    System.out.println("Если вы хотите перенести первый элемент в конец списка, введите H. \nЕсли вы хотите перенести последний элемент в начало списка, введите T");
                    Scanner input = new Scanner(System.in);
                    String elem = input.nextLine();
                    if (elem.equals("H") || elem.equals("h"))
                        list.moveHeadToTail();
                    else if (elem.equals("T") || elem.equals("t"))
                        list.moveTailToHead();
                    else
                        System.out.println("вы ввели нераспознанный символ");
                    list.printLn();
                }
                case "3" -> {
                    int rez = list.getUnique();
                    System.out.println(rez);
                }
                case "4" -> {
                    list.deleteSecond();
                    list.printLn();
                }
                case "5" -> {
                    System.out.println("Введите элемент после которого необходимо дублировать список");
                    Scanner input = new Scanner(System.in);
                    int elem = input.nextInt();
                    list.insertListAfter(elem);
                    list.printLn();
                }
                case "6" -> {
                    System.out.println("Введите элемент который ходите вставить в упорядоченный список");
                    Scanner input = new Scanner(System.in);
                    int elem = input.nextInt();
                    list.insertInOrder(elem);
                    list.printLn();
                }
                case "7" -> {
                    System.out.println("Введите элемент для удаления из списка");
                    Scanner input = new Scanner(System.in);
                    int elem = input.nextInt();
                    list.removeAll(elem);
                    list.printLn();
                }
                case "8" -> {
                    System.out.println("Введите новый элемент");
                    Scanner input = new Scanner(System.in);
                    int newElem = input.nextInt();
                    System.out.println("Введите элемент перед которым вставить новый элемент");
                    int elem = input.nextInt();
                    list.insertFBeforeE(newElem, elem);
                    list.printLn();
                }
                case "9" -> {
                    var firstList = readDoubleLinkedList(new File("firstList.txt"));
                    var secondList = readDoubleLinkedList(new File("secondList.txt"));
                    firstList.addList(secondList);
                    firstList.printLn();
                }
                case "10" -> {
                    System.out.println("Введите элемент на котором сделать разбиение на два списка");
                    Scanner input = new Scanner(System.in);
                    int elem = input.nextInt();
                    OwnDoubleLinkedList[] rez = list.splitByTwo(elem);
                    System.out.println("Первый список:");
                    rez[0].printLn();
                    System.out.println("Второй список:");
                    rez[1].printLn();
                }
                case "11" -> {
                    list.doubleList();
                    list.printLn();
                }
                case "12" -> {
                    System.out.println("Введите элемент у которого хотите поменять место");
                    Scanner input = new Scanner(System.in);
                    int firstInput = input.nextInt();
                    System.out.println("Введите элемент с которым хотите поменять место");
                    int secondInput = input.nextInt();
                    list.replaceElements(firstInput, secondInput);
                    list.printLn();
                }
                default -> System.out.println("Такой задачи нет");
            }
            System.out.println("Если вы хотите закончить нажмите \"Y\"");

            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            if (choice.equals("Y") || choice.equals("y"))
                exit = false;
        }
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
}
