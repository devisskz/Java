// Name: Batyr Issabekov
// Date: 11/1/2020
// CS112
import java.util.Arrays; //for tests

public class Duplicates {
    


    /*a static method named removeDups() that takes a sorted 
    array of integers and removes whatever elements are necessary to 
    ensure that no item appears more than once.
    */
    /*public static int removeDups(int arr[]) {
        index
}*/
    public static int removeDups(int arr[]) {
        int currentIndx = -1; //initially -1 to accomodate for index start from 0

        for (int i= 0; i < arr.length-1; i++) { //for loop loops through the array

            if (arr[i] == arr[i+1]) { //if the number is equal to the next number
                if (currentIndx == -1) { //if the current index is -1, then we increase it to >
                                            // the current index + 1, which is where the number should be shifted
                    currentIndx = i+1;
                }
        }
            if (arr[i] != arr[i+1]) { //if the numbers are not equal then
                //case for the first number in the given array
                if(currentIndx == -1) { //if the current index is -1
                    continue; //we continue to the next elem in the array
                }
                else { //otherwise,
                    arr[currentIndx] = arr[i+1]; //the next number is shifted into the place of the current index
                    currentIndx += 1; //increments the current index to be a placeholder for the next number
                }
            } 
        } 

            int i = currentIndx; //at this point, i is the final index when the numbers in the list ended
            while (i < arr.length) { //therefore, we loop through the array while i reaches the end of length of the array
                arr[i] = 0; //sets each and every number in the array to 0
                i++; //increments i for each call
            }

            //returns the current index, which is the first '0' in the array
            return currentIndx;
        } 
        
        

        /* public static void main(String[] args) {    
            int[] arr = {2, 5, 5, 5, 10, 12, 12};    
            int ret = removeDups(arr);
            System.out.println(Arrays.toString(arr));
            System.out.println(ret);
        } */
    }