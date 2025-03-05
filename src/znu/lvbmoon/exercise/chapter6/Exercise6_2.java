package src.znu.lvbmoon.exercise.chapter6;

import java.util.Scanner;

public class Exercise6_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int smallest = Integer.MAX_VALUE;
        int largest = Integer.MIN_VALUE;
        int evenCount = 0, oddCount = 0, cumulativeTotal = 0, previousNumber = Integer.MIN_VALUE;
        boolean isDuplicatePrinted = false;
        boolean hasDuplicates = false;

        System.out.println("Please enter numbers (type 'stop' to finish): ");
        while (true) {
            String input = sc.next();
            if (input.equalsIgnoreCase("stop")) break;
            int number = Integer.parseInt(input);

            // Part a: Find smallest and largest
            if (number < smallest) smallest = number;
            if (number > largest) largest = number;

            // Part b: Count even and odd
            if (number % 2 == 0) evenCount++;
            else oddCount++;

            // Part c: Cumulative total
            cumulativeTotal += number;

            // Part d: Check for adjacent duplicates
            if (previousNumber != Integer.MIN_VALUE && number == previousNumber && !isDuplicatePrinted) {
                if (!hasDuplicates) {
                    System.out.print("d) Adjacent duplicates: ");
                    hasDuplicates = true;
                }
                System.out.print(number + " ");
                isDuplicatePrinted = true;
            } else if (number != previousNumber) {
                isDuplicatePrinted = false;
            }
            previousNumber = number;
        }
        // Print results
        System.out.println("\na) Smallest = " + smallest + "  -  Largest = " + largest);
        System.out.println("b) Even count = " + evenCount + "  -  Odd count = " + oddCount);
        System.out.println("c) Cumulative total = " + cumulativeTotal);

        if (!hasDuplicates) {
            System.out.println("d) No adjacent duplicates found.");
        }
    }
}