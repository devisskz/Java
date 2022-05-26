package ps2.pr5;

//CS 112
//Name: Batyr Issabekov

public class MyMethods {
    public static void printDecreasing(String s) {  //takes string s
        for (int i = s.length(); i>=0; i--){   //initializes i to length of s, concatenatees by -1
            System.out.println(s.substring(0,i));  //prints the resulting substring on each line for every loop
        }
    }
    public static String firstAndLast(String str) {
        if (str.length()>1) {   //if string s is longer than 1 character, then
            return "" + str.charAt(0) + str.charAt(str.length()-1);  //returns the first and last
        }
        else {
            return "" + str.charAt(0);  //otherwise, return just first character
    }
}
    public static int lastIndexOf(String str, char ch) {
        int index = -1;    //initially index is set to -1
        for (int i = 0; i < str.length(); i++ ) {   //for loop to loop through each letter
            if (str.charAt(i) == ch)    //is letter at str[i] is equal to the given character
            index = i;  //sets that i to index
            
        }
            return index;  //returns index
        }
    
    public static String repeat(String str, int n) {
        String result = "";  //initial result is a string (given)

        for (int i = 0; i <n; i++) {  //loop for each str while i is less than n
            result += str;  //adds str to empty string however many times until i reaches n
        }
        return result; //returns full string of result


    }
    }
