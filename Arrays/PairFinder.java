// Name: Batyr Issabekov
// Date: 11/1/2020
// CS112
import java.util.Arrays; //for tests

public class PairFinder {

    /*find all pairs of values in the array (if any) that sum to a given integer k, having O(n^2) time*/

    public static void findPairSums(int k, int[] arr) {

        for (int i = 0; i < arr.length; i++) { //loops through each item in array
            for (int j = i+1; j < arr.length; j++) { //loops through each NEXT item in the array
                if (arr[i] + arr[j] == k) { //if the pair is found, then
                    System.out.println(arr[i] + " + " + arr[j] + " = " + k); //prints the pairs on new line
                }
            } //double for loop results in O(n^2)

        }
    }

    

    // bubble sort and swap from Sort class in order to sort the array

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }
 

    /*
    does the same thing but having O(nlogn) time */

    public static void findPairSumsFaster(int k, int[] arr) {

        bubbleSort(arr); // sorts the array using bubble sort ^

        int i = 0; //initializing i to 0
        int j = arr.length - 1; //initializing j to last index of array

        while (i < j) { //while loop (goes from i left and j from right)
            if (arr[i] + arr[j] == k) { //if the pair is found
                System.out.println(arr[i] + " + " + arr[j] + " = " + k); //prints the pairs on new line
                i++; //increments i
                j--; // decreases j
            }
            if (arr[i] + arr[j] < k) { //if the current number arr[i] is not enough to reach k,then
                i++; //moves on to next
            }
            else { //if the current number arr[j] is more than k, then
                j--; //goes to previous (smaller) number to check
            }
        }
    }
    
    
/* public static void main(String[] args) {
    int arr[] = {10, 4, 7, 7, 8, 5, 15};
    findPairSums(12, arr);
    findPairSumsFaster(12, arr); 
} */
}