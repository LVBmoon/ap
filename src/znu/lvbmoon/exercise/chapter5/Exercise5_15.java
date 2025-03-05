package src.znu.lvbmoon.exercise.chapter5;/*The question:
The original U.S. income tax of 1913 was quite simple. The tax was
• 1 percent on the first $50,000.
• 2 percent on the amount over $50,000 up to $75,000.
• 3 percent on the amount over $75,000 up to $100,000.
• 4 percent on the amount over $100,000 up to $250,000.
• 5 percent on the amount over $250,000 up to $500,000.
• 6 percent on the amount over $500,000.
There was no separate schedule for single or married taxpayers. Write a program that
computes the income tax according to this schedule.*/

import java.util.Scanner;

public class Exercise5_15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the income: ");
        float income = scanner.nextFloat();
        double tax = 0;
        if (income > 500_000) {
            tax += (income - 500_000) * 0.06;
            income = 500_000;
        }
        if (income > 250_000) {
            tax += (income - 250_000) * 0.05;
            income = 250_000;
        }
        if (income > 100_000) {
            tax += (income - 100_000) * 0.04;
            income = 100_000;
        }
        if (income > 75_000) {
            tax += (income - 75_000) * 0.03;
            income = 75_000;
        }
        if (income > 50_000) {
            tax += (income - 50_000) * 0.02;
            income = 50_000;
        }
        if (income > 0) {
            tax += income * 0.01;
        }
        System.out.println("The tax is " + tax + "$");
    }
}
/*first I thought I should solve it in this wat:
        if (income <= 50_000) tax = (income*0.01);
        else if (income > 50_000 && income <= 75_000) tax = (income - 50_000)* 0.02;
        else if (income > 75_000 && income <= 100_000) tax = (income - 75_000)* 0.03;
        else if (income > 100_000 && income <= 250_000) tax = (income - 100_000)* 0.04;
        else if (income > 250_000 && income <= 500_000) tax = (income - 250_000)* 0.05;
        else if (income > 500_000) tax = (income - 500_000)* 0.06;
*/