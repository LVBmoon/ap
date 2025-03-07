package src.znu.lvbmoon.exercise.chapter6;

/*The question:
Write a program that reads a number and prints all of its binary digits: Print the
remainder number % 2, then replace the number with number / 2. Keep going until the
number is 0. For example, if the user provides the input 13, the output should be
1
0
1
1
*/

import java.util.Scanner;

public class Exercise6_13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        int num = sc.nextInt();
        sc.close();
        while (num != 0) {
            int remainder = num % 2;
            System.out.println(remainder);
            num /= 2;
        }
    }
}
