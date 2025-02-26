Title: Sorting elements in a list by breaking them into sub-lists. 
Problem Description: Run the program for varied values of n to demonstrate the behaviour of the algorithm in the Worst, Best 
and Average Cases. Record the time taken to sort. Plot a graph Of the time taken versus n on graph sheet. The elements can be 
read from a file or can be generated using the random number generator for large values of n. 
Method: A Divide & Conquer- Recursive algorithm implementation- Merge Sort.
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class MergeSort {

    public static void main(String[] args) {
        int[] sizes = {10, 100, 500, 1000, 5000, 10000, 50000}; // Array sizes to test

        // Write data into CSV file
        try (FileWriter writer = new FileWriter("mergeSortTimes.csv")) {
            writer.append("Size,Time (ns)\n");

            for (int n : sizes) {
                int[] arr = generateRandomArray(n);
                long startTime = System.nanoTime(); // Record start time
                
                mergeSort(arr);
                
                long endTime = System.nanoTime(); // Record end time
                long timeTaken = endTime - startTime; // Time taken
                
                writer.append(n + "," + timeTaken + "\n"); // Write size & time to CSV
            }

            System.out.println("Data has been written to 'mergeSortTimes.csv'");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Merge Sort recursive function
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return; // Base case: 1 element array already sorted
        }

        // Split array into 2 halves
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        // Recursively sort both halves
        mergeSort(left);
        mergeSort(right);

        // Merge sorted halves
        merge(arr, left, right);
    }

    // Function to merge 2 sorted subarrays into the original array
    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Merge arrays while both have elements
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        // If left array has remaining elements, add them
        while (i < left.length) {
            arr[k++] = left[i++];
        }

        // If right array has remaining elements, add them
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // Function to generate a random array of given size
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100000); // Random values between 0 and 99999
        }

        return arr;
    }
}
