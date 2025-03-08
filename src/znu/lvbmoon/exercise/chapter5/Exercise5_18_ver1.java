package src.znu.lvbmoon.exercise.chapter5;

import java.util.Scanner;

public class Exercise5_18_ver1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter three strings: ");
        String str1 = sc.next();
        String str2 = sc.next();
        String str3 = sc.next();
        String temp;
        if (str1.compareTo(str2) > 0) {
            temp = str1;
            str1 = str2;
            str2 = temp;
        }
        if (str2.compareTo(str3) > 0) {
            temp = str2;
            str2 = str3;
            str3 = temp;
        }
        if (str1.compareTo(str2) > 0) {
            temp = str1;
            str1 = str2;
            str2 = temp;
        }
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
    }
}
