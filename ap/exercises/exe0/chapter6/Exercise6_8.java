package ap.exercises.exe0.chapter6;

import java.util.Scanner;

public class Exercise6_8 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a string:  ");
        String str = sc.next();
        for (int i = 0; i < str.length(); i++){
            System.out.println(str.charAt(i));
        }
    }
}
