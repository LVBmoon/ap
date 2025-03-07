package src.znu.lvbmoon.exercise.chapter6;

/*The question:
Write a program that reads an integer and displays, using asterisks, a filled diamond
of the given side length. For example, if the side length is 4, the program should
display
   *
  ***
 *****
*******
 *****
  ***
   *
 */

import java.util.Scanner;

public class Exercise6_18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter an integer:  ");
        int number = sc.nextInt();
        sc.close();

        for (int i = 1; i <= number; i++) {
            for (int j = 1; j <= number - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = number - 1; i >= 1; i--) {
            for (int j = 1; j <= number - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
