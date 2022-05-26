package ps2;


/*
 * Problem Set 1
 *
 * Practice with static methods, part I
 *
 * CS112
 *Name: Batyr Issabekov
 */

public class Methods {
    /*
     * printVertical - takes a string s and prints the characters of 
     * the string vertically -- with one character per line.
     */
    public static void printVertical(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println(c);
        }
    }
    /*takes string s and prints the characters with a space between then */
    public static void printWithSpaces(String s) {
        for (int i = 0; i <s.length(); i++) {
            String x = "" + s.substring(i, i+1) + " ";   //creates a new string with spaces
            System.out.print(x.substring(0));         //prints that new string
        }
        
    }
    /* takes a string s and returns the middle character in that string*/
    public static char middleChar(String s) {
        int middle = (s.length()-1) /2;   //-1 makes the first letter of s/2 the middle character
        /*char x = s.charAt(middle);
        System.out.println("the middle of Boston is: " + x);*/
        return s.charAt(middle);

    }
    /* takes string s and int i, returns a new string by “moving” the first i characters of str to the end of the string */
    public static String moveToEnd(String str, int i) {
        if (i<=str.length()) { //if there is enough letters to move in the string,
        String end = str.substring(0,i); //assign ending of the string, according to i
        String start = str.substring(i, str.length()); //assign the start of the string according to i
        return start + end; //returns both concatenated
        }
        else {
            return str; //if i is larger than the length of the string, returns the original input string
        }

    }


    public static void main(String[] args) {
        /* Sample test call */
        printVertical("method");
        printWithSpaces("method");
        middleChar("clock");
        moveToEnd("Terriers", 2);

    }
}
