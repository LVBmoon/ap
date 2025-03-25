package ap.exercises.exe1.chapter5;

import java.util.Scanner;

public class Main_EX1_E5_2 {
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
