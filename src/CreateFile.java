import java.io.*;

public class CreateFile {
    public static void main(String[] args) throws IOException {
        File myFoo = new File("20 млн без пуша.txt");
        FileWriter fooWriter = new FileWriter(myFoo, false);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 20000000; i++) {
            int command = rnd(2,5);
            if(command == 1){
                result.append(command).append(",").append(rnd(10000,99999)).append(" ");
            } else {
                result.append(command).append(" ");
            }
        }
        fooWriter.write(result.toString());
        fooWriter.close();
    }
    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
