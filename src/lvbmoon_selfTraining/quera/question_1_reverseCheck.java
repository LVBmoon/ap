package src.lvbmoon_selfTraining.quera;

import java.util.Scanner;

public class question_1_reverseCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int number1= sc.nextInt();
        int number2= sc.nextInt();

        if(reverseNumber(number1) > reverseNumber(number2)) {
            System.out.println(number2 + " < " +number1);
        }
        else if(reverseNumber(number1) < reverseNumber(number2)) {
            System.out.println(number1 + " < " +number2);
        }
        else {
            System.out.println(number1 + " = " + number2);
        }
    }
    public static int reverseNumber(int number){
        int newNumber = 0;
        while(number != 0){
            int remain = number % 10;
            newNumber = newNumber * 10 + remain;
            number = number / 10;
        }
        return newNumber;
    }
}
