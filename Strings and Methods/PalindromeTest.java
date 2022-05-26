package ps2.pr6;

import java.util.Scanner;
public class PalindromeTest {
    //Name: Batyr Issabekov

    
    public static boolean isPalindrome(String s) {
        boolean isPal = false;    // assume that it is not
        s = s.toLowerCase();
        s = s.replace(" ", "");
        s = s.replace(".", "");
        s = s.replace("!", "");
        s = s.replace("?", "");
        s = s.replace("/", "");
        s = s.replace(":", "");
        s = s.replace(";", "");
        s = s.replace(",", "");
        s = s.replace("'", "");
        s = s.replace("\"", "");
         //replaces all non intereger and non letter characters with ""
        // code to determine if the string s is a palindrome
        for(int x = s.length()-1, i =0; x >=0; x--,i++) {
                if(s.charAt(i) == s.charAt(x)) {
                    isPal=true;
                }
                else {
                    isPal=false;
                    break;
        }
    }

     
        // If the default (as above) assumes the string is not a palindrome,
        // the logic here should determine if it is and reassign the return
        // variable isPal appropriately, or vice verse.


     
        return ( isPal );

} 
    public static int[] inputStringPalindrome() {
        int[] array = new int[2];
        Scanner userInput = new Scanner(System.in);
        System.out.println("Type in a palindrome or quit to end:");
        // Continue to receive user input until some
    // sentinal (i.e. final) value is entered.
    // In this case, the user must enter the
    // word "quit".
    while (userInput.hasNextLine()) {
        String line = userInput.nextLine();
        if (line.equals("quit"))
      // User has specified input it over,
      // break out of the loop.
      
          break;
        else
          System.out.println("You input: " + line);
          //when input is entered, adds 1 to array [0]
          array[0] += 1;
            if(isPalindrome(line) == true) {
          System.out.println("" + line + " is a palindrome");
          //if is a palindrome, adds 1 to array [1]
          array[1] += 1;
        }
        else {
          System.out.println("" + line + " is not a palindrome");
        }
      } // while
      
      
      System.out.println("bye!");
      
      userInput.close();
      return array;
      
    }
    
    //main
    public static void main(String[] args) {
    inputStringPalindrome();
    }
    }   


