package src.znu.lvbmoon.exercise.chapter6;

import java.util.Scanner;

public class Exercise6_5_ver2 {
    private double[] values;
    private int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the maximum number of values: ");
        int maxSize = sc.nextInt();
        Exercise6_5_ver2 dataSet = new Exercise6_5_ver2(maxSize);

        System.out.println("Enter floating-point values (type 'stop' to finish):");
        while (true) {
            String input = sc.next();
            if (input.equalsIgnoreCase("stop")) break;
            double value = Double.parseDouble(input);
            dataSet.add(value);
        }
        System.out.println("Smallest: " + dataSet.getSmallest()+ "\nLargest: " + dataSet.getLargest());
        System.out.println("Average: " + dataSet.getAverage()+ "\nRange: " + dataSet.getRange());
    }
    public Exercise6_5_ver2(int maxSize) {
        values = new double[maxSize];
        count = 0;
    }

    public void add(double value) {
        if (count < values.length) {
            values[count] = value;
            count++;
        } else {
            System.out.println("Array is full!You can not add more values!");
        }
    }

    public double getAverage() {
        if (count == 0) {
            return 0;
        }

        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += values[i];
        }
        return sum / count;
    }


    public double getSmallest() {
        if (count == 0) {
            return 0;
        }

        double smallest = values[0];
        for (int i = 1; i < count; i++) {
            if (values[i] < smallest) {
                smallest = values[i];
            }
        }
        return smallest;
    }

    public double getLargest() {
        if (count == 0) {
            return 0;
        }

        double largest = values[0];
        for (int i = 1; i < count; i++) {
            if (values[i] > largest) {
                largest = values[i];
            }
        }
        return largest;
    }

    public double getRange() {
        return getLargest() - getSmallest();
    }
}