import java.util.Scanner;
//name: Batyr Issabekov
public class WordPalindromeTest {
    public static boolean isWordPalindrome(String s)  {
        s = s.toLowerCase();
        s = s.replace(".", "");
        s = s.replace("!", "");
        s = s.replace("?", "");
        s = s.replace("/", "");
        s = s.replace(":", "");
        s = s.replace(";", "");
        s = s.replace(",", "");
        s = s.replace("'", "");
        s = s.replace("\"", "");
        s = s.replace("-", ""); //replaces each non interger and non letter with empty space
        String[] array = s.split(" "); //splits at space
        for(int x = array.length-1, i = 0; x >=0; x--,i++) { //as from palindrome test, loop through beginning and end of array
            if (array[x].equals(array[i])) { 
                return true; //if the array from beginning equal to that same array from the back, returns true
            }
        }
            return false;            //otherwise, it is not palindrome
        }
          

        //main
        public static void main(String[] args) {
            int[] arr = new int[2];
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
              arr[0] += 1;
                if(isWordPalindrome(line) == true) {
              System.out.println("" + line + " is a palindrome");
              arr[1] += 1;
            }
            else {
              System.out.println("" + line + " is not a palindrome");
            }
          } // while
          
          
          System.out.println("bye!");
          
          userInput.close();
          
          
        }
    }