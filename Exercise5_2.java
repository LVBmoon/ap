/*The question:
Write a program that reads a floating-point number and prints “zero” if the number
is zero. Otherwise, print “positive” or “negative”. Add “small” if the absolute value
of the number is less than 1, or “large” if it exceeds 1,000,000.*/

import java.util.Scanner;
public class Exercise5_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a floating-point number: ");
        float number = scanner.nextFloat();
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
    }
}
