import java.util.*;

class FixingStringIndexOutOfBoundsException {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();
        int index = scanner.nextInt();

        try {
            System.out.println(string.charAt(index));
        } catch (Exception e) {
            System.out.println("Out of bounds!");
        }
    }
}