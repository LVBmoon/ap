package src.znu.lvbmoon.exercise.chapter6;/*
Write a program that reads a word and prints each character of the word on a separate
line. For example, if the user provides the input "Harry", the program prints
H
a
r
r
y
*/

import java.util.Scanner;

public class Exercise6_8 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a string: ");
        String str = sc.next();
        for (int i = 0; i < str.length(); i++){
            System.out.println(str.charAt(i));
        }
    }
}
