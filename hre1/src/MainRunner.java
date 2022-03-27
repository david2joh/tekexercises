import java.util.StringJoiner;

public class MainRunner {
    public static void main(String[] args) {
        System.out.println("Hello World");
        StringJoiner joinNames = new StringJoiner(",","[","]");
        joinNames.add("Liz");
        joinNames.add("Laura");
        System.out.println(joinNames);
    }
}
