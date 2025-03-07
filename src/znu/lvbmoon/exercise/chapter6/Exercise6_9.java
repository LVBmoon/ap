package src.znu.lvbmoon.exercise.chapter6;

/*The question:
Write a program that reads a word and prints the word in reverse. For example, if the
user provides the input "Harry", the program prints (yrraH)
*/

import java.util.Scanner;

public class Exercise6_9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a word: ");
        String word = sc.next();

        for(int i = word.length() - 1; i >= 0; i--){
            System.out.print(word.charAt(i));
        }
    }
}
