package src.znu.lvbmoon.exercise.chapter6;

import java.util.Scanner;

public class Exercise6_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number the string: ");
        String userStr = sc.nextLine();
        //a:
        System.out.println("part a: ");
        int existUppercaseLetter = 0;
        for (int i = 0; i < userStr.length(); i++) {
            if (userStr.charAt(i) >= 'A' && userStr.charAt(i) <= 'Z') {
                System.out.print(userStr.charAt(i));
                existUppercaseLetter++;
            }
        }
        if (existUppercaseLetter == 0)
            System.out.println("There is no uppercase letter...");
        //b:
        System.out.println("\npart b: ");
        for (int i = 0; i < userStr.length(); i += 2) {
            System.out.print(userStr.charAt(i));
        }
        //c,d:
        System.out.println("\npart c: ");
        int numberOfVowels = 0;
        for (int i = 0; i < userStr.length(); i ++) {
            char ch = userStr.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                    ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
                System.out.print('_');
                numberOfVowels++;
            }
            else {
                System.out.print(ch);
            }
            /*Or we can write:
             * String replaced = userStr.replaceAll("[AEIOUaeiou]","_");
             * System.out,println(replased);*/
        }
        System.out.println("\npart d: " + numberOfVowels);
        //e:
        System.out.println("\npart e: ");
        for (int i = 0; i < userStr.length(); i ++) {
            char ch = userStr.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                    ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
                System.out.print(i + " ");
            }
        }
    }
}
