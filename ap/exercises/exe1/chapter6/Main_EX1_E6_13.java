package ap.exercises.exe1.chapter6;

import java.util.Scanner;

public class Main_EX1_E6_13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number:  ");
        int num = sc.nextInt();
        sc.close();
        while (num != 0) {
            int remainder = num % 2;
            System.out.println(remainder);
            num /= 2;
        }
    }
}
