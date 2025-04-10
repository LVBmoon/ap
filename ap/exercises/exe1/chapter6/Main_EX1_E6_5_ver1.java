package ap.exercises.exe1.chapter6;

import java.util.Scanner;

public class Main_EX1_E6_5_ver1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a set of floating-point numbers(once you end enter stop): ");
        while (true){
            String userInput = sc.next();
            if (userInput.equalsIgnoreCase("stop")) break;
            else{
                double value = Double.parseDouble(userInput);
                DataSet.adding(value);
            }
        }
        System.out.println("Smallest: " + DataSet.getSmallest() + "\nLargest: " + DataSet.getLargest());
        System.out.println("Average: " + DataSet.getAverage() + "\nRange: " + DataSet.getRange());
    }

    public static class DataSet {
        static double smallest = Double.MAX_VALUE;
        static double largest = Double.MIN_VALUE;
        static double sum = 0;
        static double cnt = 0;

        public static void adding(double value){
            sum += value;
            cnt++;
            if (value < smallest) {
                smallest = value;
            }
            else if (value > largest) {
                largest = value;
            }
        }

        public static double getSmallest(){
            return smallest;
        }

        public static double getLargest () {
            return largest;
        }

        public static double getAverage () {
            return (sum/cnt);
        }

        public static double getRange () {
            return (largest - smallest);
        }
    }
}

