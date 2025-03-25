package ap.exercises.exe1.chapter6;

import java.util.Scanner;

public class Main_EX1_E6_9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a word: ");
        String word = sc.next();
        for(int i = word.length() - 1; i >= 0; i--){
            System.out.print(word.charAt(i));
        }
    }
}
