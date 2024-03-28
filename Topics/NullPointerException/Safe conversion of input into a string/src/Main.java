import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //TODO: 
        // 1. Define a method safeToString(Object obj)
        // 2. This method should try to convert obj to String
        // 3. In case a null value is found, the method should return a "null" String
        // 4. Use the ternary operator '?' to implement this condition in just one line
        // 5. Call safeToString(...) with the input and store the result
        // 6. Print the result

        System.out.println(safeToString(input));

        scanner.close();
    }

    public static String safeToString(Object obj) {
        if (obj == null) {
            return "null";
        } else {
            return obj.toString();
        }
    }
}