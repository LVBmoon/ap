package src.znu.lvbmoon.exercise.chapter6;

import java.util.Scanner;

public class Exercise6_13 {
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
