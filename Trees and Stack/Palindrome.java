/*
 * Palindrome.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name: Batyr Issabekov
 *     username: batyriss@bu.edu
 */

public class Palindrome {
    // Add your definition of isPal here.
    public static boolean isPal(String str) {

        // cleans the string
        str = str.toLowerCase();
        str = str.replace(".", "");
        str = str.replace("!", "");
        str = str.replace("?", "");
        str = str.replace("/", "");
        str = str.replace(":", "");
        str = str.replace(";", "");
        str = str.replace(",", "");
        str = str.replace("'", "");
        str = str.replace("-", "");
        str = str.replace(" ", "");

        if (str == null) {
            throw new IllegalArgumentException(); // throws a new illegal arg exception
        }

        ArrayStack<Character> stack = new ArrayStack<>(str.length());
        ArrayQueue<Character> queue = new ArrayQueue<>(str.length());

        for (int i = 0; i < str.length(); i++) { // loops through each character in string
            queue.insert(str.charAt(i)); // inserts a character into the queue
            stack.push(str.charAt(i)); // pushes the character into the stack
        }

        while (!queue.isEmpty()) { // while the queue is not empty
            if (!queue.remove().equals(stack.pop())) // if the removed char from queue DOES NOT equal the popped char
                return false; // returns false
        }
        return true; // otherwise, returns true
    }

    public static void main(String[] args) {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println(); // include a blank line between tests

        /*
         * Add five more unit tests that test a variety of different cases. Follow the
         * same format that we have used above.
         */
        System.out.println("(1) Testing on \" Anna \"");
        try {
            boolean results = isPal("Anna");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); // include a blank line between tests
        System.out.println("(2) Testing on \"CIVIC\"");
        try {
            boolean results = isPal("CIVIC");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); // include a blank line between tests
        System.out.println("(3) Testing on \"woow\"");
        try {
            boolean results = isPal("woow");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); // include a blank line between tests
        System.out.println("(4) Testing on \"radar\"");
        try {
            boolean results = isPal("radar");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); // include a blank line between tests
        System.out.println("(5) Testing on \"no\"");
        try {
            boolean results = isPal("no");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("false");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == false);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println(); // include a blank line between tests

    }

}