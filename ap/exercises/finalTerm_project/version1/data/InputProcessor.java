package ap.exercises.finalTerm_project.version1.data;

import java.util.Scanner;

public class InputProcessor {
    private Scanner scanner;

    public InputProcessor(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }
}