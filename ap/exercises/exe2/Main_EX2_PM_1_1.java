package ap.exercises.exe2;

import java.util.Scanner;

public class Main_EX2_PM_1_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int k = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < k+2; i++) {
            System.out.print("*");
        }
        System.out.println();
        for (int i = 0; i < k ; i++) {
            System.out.print("*");
            for (int j = 0; j < k ; j++) {
                System.out.print(" ");
            }
            System.out.print("*");
            System.out.println();
        }
        for (int i = 0; i < k+2; i++) {
            System.out.print("*");
        }
    }
}
