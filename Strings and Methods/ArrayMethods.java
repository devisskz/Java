
public class ArrayMethods {
    public static final String[] DAYS =
    {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    
    public static void swapAdjacent(int[] values) {
        for (int i = 0; i < values.length-1; i += 2) {  //for loop with i increasing by 2, because we change startign from every third number
            int val = values[i];  //set a variable to initial values[i]
            values[i] = values[i+1];  //change the values[i] and [i+1], which is the next value in array
            values[i+1] = val;    //change the [i+1] with initial values[i]
        }
    }
    public static int[] copyReplace(int[] values, int oldVal, int newVal) {
        int[] new_values = new int[values.length];
        for (int i=0; i<values.length; i++) { //for loop that goes through every value inside the array
            if (values[i] == oldVal){   //if current value[i] the same as the old value, we replace it ->
                new_values[i] = newVal;   //replace the value in the new array, keeping the old array the same.
            }
            else {
                new_values[i] = values[i]; //if the value is not the one we have to replace, just copy the value from original array
            }
        }
        return new_values; //returns the new array
    }
    public static int maxSorted(int[] values) {
        if (values == null ){
            throw new IllegalArgumentException();  // as given, if values null, "throw new Illegal Arg"
        }
        if (values.length == 0) {   //if values len is 0, returns 0
            return 0;
        }
        if (values.length == 1) { // if values len is 1, returs 1
            return 1;
        }
        
        int max_len = 0;  //set maximum length to 0
        int current_len = 1; //set current length to 1, because we start counting from first number in array
        for (int i = 0; i < values.length-1; i++) {  //for to loop through each num in array
            if (values[i] < values[i+1] || values[i]==values[i+1]) { //if next value is larger or equal than previous, add 1 to current len count
                current_len += 1;
                if (max_len<current_len) { //if current max_len is less that current len, set max len to highest
                max_len = current_len;
                }
            }
            else {  //otherwise, current length is set to 1
                current_len = 1;
            }
        }
        return max_len; //returns the max len (highest of current len)
         }
    public static int getIndexOfDay(String day) { //get index of given day of week according to DAYS array
        int index = -1; //initially index is set to -1
        if (day == null) { //if input is null, returns -1, as given
            return index;
        }
        
        for (int i = 0; i< DAYS.length; i++) { //loops through each string in array
            if ( day.equalsIgnoreCase(DAYS[i])) { { //if the input is equal to string in array, set index to that DAYS[i], ignoring upper/lowercase
                index = i;
                break; //exit loop
            }
        }}
        return index;  //return current index
    }
    public static int[] reverseInterchange( int[] arr1, int [] arr2 ) { //interchange numbers in array
        if(arr1 == null || arr2 == null) {
            throw new IllegalArgumentException(); //if input is null, throw new "illegal arg"
        }
        int[] array = new int[Math.min(arr1.length,arr2.length)*2]; //new array is set to the lowest length out of two arrays
        if (arr1.length>=arr2.length) { //if length of first array is smaller, then
            for (int i=0, index = 0, j=(arr2.length-1); i<arr2.length; i++, j--, index +=2) { //loop through the first and second array
                //separating index to store the data in that array, and increase it by 2
                array[index] = arr1[i];
                array[index+1] = arr2[j]; //set new array to numbers[i] from input
            }
            }
        else if (arr1.length<arr2.length) { //if second array is longer than first, then
            for (int i=0, index = 0, j= (arr2.length-1); i<arr1.length; i++, j--, index += 2) { //exactly the same loop, except i now limits to length of lower array
                array[index] = arr1[i];
                array[index+1] = arr2[j]; //assign accordingly
            }    
        }
        return array;  //returns new array
    }
}
        

        
   
