package src.znu.lvbmoon.exercise.chapter6;

import java.util.Scanner;

public class Exercise6_1_a_and_e {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //a:
        int sum = 0;
        for (int i = 2; i <= 100; i += 2) {
            sum += i;
        }
        System.out.print(sum);

        //e:
        System.out.println();
        int result = 0;
        System.out.println("Please enter a number: ");
        int number = scanner.nextInt();
        while (number != 0) {
            int remain = number % 10;
            if (remain % 2 == 1) result += remain ;
            number = number / 10;
        }
        System.out.println(result);
    }
}
