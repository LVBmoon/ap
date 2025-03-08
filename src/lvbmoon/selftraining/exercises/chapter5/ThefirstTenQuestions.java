package src.lvbmoon.selftraining.exercises.chapter5;

import java.util.Scanner;

public class ThefirstTenQuestions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //e5.1:
        System.out.println("Please enter an integer:  ");
        int number1 = sc.nextInt();
        e1(number1);

        //e5.2:
        System.out.println("Please enter a floating-point number: ");
        float number2 = sc.nextFloat();
        e2(number2);

        //e5.3:
        System.out.print("Enter an integer (less than ten billion): ");
        long number = sc.nextLong();
        e3(number);

        //e5.4:
        System.out.println("Please enter a string:  ");
        String string = sc.next();
        e4(string);
    }

    public static void e1 (int number){
        if(number == 0 ){
            System.out.println("The number is zero");
        }
        else if(number > 0){
            System.out.println("The number is positive");
        }
        else{
            System.out.println("The number is negative");
        }
        return;
    }

    public static void e2 (float number){
        if (number == 0){
            System.out.println("zero");
        }
        else{
            System.out.println(number > 0 ? "positive" : "negative");
        }
        float absNumber = Math.abs(number);
        if (absNumber < 1){
            System.out.println("small");
        }
        else if (absNumber > 1000000){
            System.out.println("large");
        }
        return;
    }

    public static void e3 (long number){
        int digitCount = 0;

        if (number < 0) {
            number *= -1;
        }

        if (number < 10) {
            digitCount = 1;
        } else if (number < 100) {
            digitCount = 2;
        } else if (number < 1000) {
            digitCount = 3;
        } else if (number < 10000) {
            digitCount = 4;
        } else if (number < 100000) {
            digitCount = 5;
        } else if (number < 1000000) {
            digitCount = 6;
        } else if (number < 10000000) {
            digitCount = 7;
        } else if (number < 100000000) {
            digitCount = 8;
        } else if (number < 1000000000) {
            digitCount = 9;
        } else if (number < 10000000000L) {
            digitCount = 10;
        } else {
            System.out.println("The number is too large!");
            return;
        }
        System.out.println("The number of digits is: " + digitCount);
    }

    public static void e4 (String string){
        if (string.charAt(0) == string.charAt(string.length()-1)) {
            System.out.println("first and last letter same");
        }
        else {
            System.out.println("first and last letter different");
        }
        return;
    }

}

