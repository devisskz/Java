// Name: Batyr Issabekov
// Date: 11/1/2020
// CS112

import java.util.Arrays;

public class MergeIntersect {

        //merge sort from Sort, used in order to sort the arrays

    /* merge - helper method for mergesort */
    private static void merge(int[] arr, int[] temp, 
      int leftStart, int leftEnd, int rightStart, int rightEnd)
    {
        int i = leftStart;    // index into left subarray
        int j = rightStart;   // index into right subarray
        int k = leftStart;    // index into temp
        
        while (i <= leftEnd && j <= rightEnd) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++; k++;
            } else {
                temp[k] = arr[j];
                j++; k++;
            }
        }
        
        while (i <= leftEnd) {
            temp[k] = arr[i];
            i++; k++;
        }
        while (j <= rightEnd) {
            temp[k] = arr[j];
            j++; k++;
        }
        
        for (i = leftStart; i <= rightEnd; i++) {
            arr[i] = temp[i];
        }
    }
    
    /** mSort - recursive method for mergesort */
    private static void mSort(int[] arr, int[] temp, int start, int end) {
        if (start >= end) {
            return;
        }
        
        int middle = (start + end)/2;
        mSort(arr, temp, start, middle);
        mSort(arr, temp, middle + 1, end);
        merge(arr, temp, start, middle, middle + 1, end);
    }
    
    /** mergesort */
    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        mSort(arr, temp, 0, arr.length - 1);
    }



    //this is a helper method to find the minimum length of the array

    public static int minimum(int a[], int b[]) {
        if (a.length >= b.length) {
            return b.length; //if array a is larger than b, the new array should be length of b
        }
        else {
            return a.length; //if the array a is smaller than b, the new array should be length of a
        }
    }
    /*  takes two arrays of integers as parameters and uses an approach based on merging to
     find and return the intersection of the two arrays – i.e., all values that are found 
     in both arrays. */

    public static int[] intersect(int a[],int b[]) {
         
        
        int c[] = new int[minimum(a,b)]; //using the helper method minimum, creates a new array length of the minimum
        

        mergeSort(a); //sorts array a
        mergeSort(b); //sorts array b

        int i = 0; //initializing variables to 0
        int j = 0;
        int k = 0;

        while (i<a.length && j<b.length) { //while loop for most time efficiency
            if (a[i] == b[j]) { //if the numbers in the array and equal
                c[k] = a[i]; //set that number to the new array
                i++; //increment each variable
                j++;
                k++;
            }
            else if (a[i] < b[j]) { //else if number in a is less than number in b, 
                i++; //move on to next number in a
            }
            else if (a[i] > b[j]) { //else if the number in a is larger than number in b,
                j++; //move on to next number in b
            }
            
        }
        return c; //returns the newly built  array
    }
    /*public static void main(String[] args) {
        int[] a1 = {0, 2, -4, 6, 10, 8};
        int[] a2 = {12, 0, -4, 8};
        int[] result = MergeIntersect.intersect(a1, a2);
        System.out.println(Arrays.toString(result));
    } */
}