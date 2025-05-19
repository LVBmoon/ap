package ap.exercises.midTerm_project.version2_part2;

import java.util.Scanner;

public class InputProcessor {
    private Scanner scanner;

    public InputProcessor(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getIntInput(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
