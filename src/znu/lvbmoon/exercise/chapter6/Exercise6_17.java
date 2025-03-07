package src.znu.lvbmoon.exercise.chapter6;

/*The question:
Write a program that reads an integer and displays, using asterisks, a filled and hollow
square, placed next to each other. For example, if the side length is 5, the program
should display
***** *****
***** *   *
***** *   *
***** *   *
***** *****
*/

import java.util.Scanner;

public class Exercise6_17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter an integer: ");
        int num = sc.nextInt();
        for (int i = 0; i < num; i++) {
            //The filled square:
            for (int j = 0; j < num; j++) {
                System.out.print('*');
            }

            //space between squares:
            System.out.print(' ');

            //The hollow square:
            for (int j = 0; j < num; j++) {
                if (i == 0 || i == num - 1 || j == 0 || j == num - 1) {
                    System.out.print('*');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}

