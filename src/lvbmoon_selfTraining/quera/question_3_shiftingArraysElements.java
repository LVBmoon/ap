package src.lvbmoon_selfTraining.quera;

import java.util.Scanner;

public class question_3_shiftingArraysElements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        for(int i =  n - k ; i < n; i++){
            System.out.print(arr[i] + " ");
        }
        for(int i = 0; i < n - k; i++){
            System.out.print(arr[i] + " ");
        }
    }
}

/*second way:
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Read n and k
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        // Array Input: Read n integers into the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // Rotate the array to the right k times
        for (int i = 0; i < k; i++) {
            int t = nums[n - 1];
            for (int j = n - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = t;
        }

        // Output: Print the rotated array
        for (int number : nums) {
            System.out.print(number + " ");
        }
    }
}
*/