import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public ArrayList<String> startReader(){
        while (true){
            ArrayList<String> list = parseLine(expressionInput());
            if (list == null){
                System.out.println("Your expression is not correct");
            } else {
                return list;
            }
        }
    }

    private String expressionInput(){
        System.out.println("Enter your math expression");
        return scanner.nextLine();
    }

    private ArrayList<String> parseLine(String line){
        Pattern pattern1 = Pattern.compile("[^+\\d \\-.]");
        Matcher matcher1 = pattern1.matcher (line);
        while (matcher1.find()){
            System.out.println(matcher1.group() + " " + matcher1.start());
        }
        if (matcher1.find()){
            return null;
        }

        Pattern pattern2 = Pattern.compile ("\\d+\\.\\d+|\\d+|[+\\-]");
        Matcher matcher2 = pattern2.matcher(line);
        ArrayList<String> list = new ArrayList<>();
        while (matcher2.find()){
            list.add(matcher2.group());
        }
        return list;
    }
}
