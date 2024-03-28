package cinema;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    private static final ArrayList<byte[]> selectedSeats = new ArrayList<>();
    private static int income = 0, totalIncome = 0;
    private static String[][] room;
    private static byte rowAmt, seatAmt;
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean isProgramRunning = true;

    public static void main(String[] args) {


        System.out.println("Enter the number of rows:");
        rowAmt = scanner.nextByte();
        System.out.println("Enter the number of seats in each row:");
        seatAmt = scanner.nextByte();

        initRoom(rowAmt, seatAmt);

        while (isProgramRunning) {
            displayMenu();
        }
    }

    private static void initRoom(byte rowAmt, byte seatAmt) {
        // init room size
        room = new String[rowAmt][seatAmt];

        for (int i = 0; i < rowAmt; i++) {
            for (int j = 0; j < seatAmt; j++) {
                room[i][j] = "S";
            }
        }

        byte firstHalfRows = (byte) (rowAmt / 2);
        byte secondHalfRows = (byte) ( rowAmt - firstHalfRows);
        
        if (rowAmt * seatAmt <= 60) {
            totalIncome = rowAmt * seatAmt * 10;
        } else {
            totalIncome = (firstHalfRows * 10 + secondHalfRows * 8)  * seatAmt;
        }
        
    }

    private static void displayRoom() {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 0; i < seatAmt; i++) {
            System.out.printf(" " + (i + 1));
        }

        System.out.print("\n");

        for (int i = 0; i < rowAmt; i++) {
            System.out.print(i+1);

            for (int j = 0; j < seatAmt; j++) {
                System.out.print(" ");
                int finalI = i;
                int finalJ = j;
                if (selectedSeats.stream().anyMatch(seat -> Arrays.equals(
                        seat, new byte[]{(byte) finalI, (byte) finalJ}
                ))) {
                    System.out.print("B");
                } else {
                    System.out.print(room[i][j]);
                }
            }

            System.out.print("\n");
        }
    }

    private static void selectSeat() {

        System.out.println("Enter a row number:");
        byte selectedRow = (byte) (scanner.nextByte() - 1);
        System.out.println("Enter a seat number in that row:");
        byte selectedSeat = (byte) (scanner.nextByte() - 1);

        try {
            final String selection = room[selectedRow][selectedSeat];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input!");
            return;
        }

        if (selectedSeats.stream().anyMatch(seat -> Arrays.equals(
                seat, new byte[]{selectedRow, selectedSeat}
        ))) {
            System.out.println("That ticket has already been purchased!");
            selectSeat();
        } else {
            int ticketPrice;

            if (rowAmt * seatAmt <= 60) {
                ticketPrice = 10;
            } else {
                ticketPrice = ((selectedRow + 1) <= rowAmt / 2) ? 10 : 8;
            }

            income += ticketPrice;
            System.out.println("Ticket price: $" + ticketPrice);

            selectedSeats.add(new byte[]{selectedRow, selectedSeat});

            displayRoom();
        }
    }

    private static void displayStats() {
        final int purchasedTicketsAmt = selectedSeats.size();
        final float roomPercentage = (purchasedTicketsAmt / (float) (seatAmt * rowAmt)) * 100;
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Number of purchased tickets: " + purchasedTicketsAmt);
        System.out.println("Percentage: " + df.format(roomPercentage) + "%");
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalIncome);
    }

    private static void displayMenu() {
        System.out.println("""
                
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit"""
        );

        byte choice = scanner.nextByte();

        switch (choice) {
            default:
                System.out.println("Invalid choice");
                break;

            case (0):
                isProgramRunning = false;
                break;

            case (1):
                displayRoom();
                break;

            case (2):
                selectSeat();
                break;

            case (3):
                displayStats();
        }
    }
}