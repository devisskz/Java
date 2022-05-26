/*
 * BigInt.java
 *
 * A class for objects that represent non-negative integers of
 * up to 20 digits.
 * Name: Batyr Issabekov
 */

public class BigInt  {
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int SIZE = 20;

    // the array of digits for this BigInt object
    private int[] digits;

    // the number of significant digits in this BigInt object
    private int numSigDigits;

    /*
     * Default, no-argument constructor -- creates a BigInt that
     * represents the number 0.
     */
    public BigInt() {
        this.digits = new int[SIZE];
        this.numSigDigits = 1;  // 0 has one sig. digit--the rightmost 0!
    }

    /*
     *
     * YOUR METHODS
     *
     */

    public BigInt(int[] arr){
        // checking if the input is valid
        if (arr == null || isValid(arr) == false || arr.length > SIZE) {
            throw new IllegalArgumentException();
        }
        // initialize a new digits array
        this.digits = new int[SIZE];
        // initialize a boolean to true, which is used later to check
        // for the first zero
        boolean isZero = true;

        // loop through the inputted array
        for (int i=0; i<arr.length; i++) {
            //initialize each item in array to digits
            this.digits[i+SIZE-arr.length] = arr[i];
            // the first time we see a non-zero digit
            if (arr[i] !=0) {
                // set boolean to false
                isZero = false;
            }
            // if we passed out first non zero,
            if (isZero == false) {
                // start counting the significant digits
                this.numSigDigits++;
            }
        }
    }
    // private helper method that checks if the input is valid
    private boolean isValid(int[] arr) {
        // loops through each item in array
        for (int i = 0; i< arr.length; i++) {
            // if there is a double digit item, returns false
            if (arr[i]/10 > 1) {
                return false;
            }
        }// otherwise, true
        return true;
    }

    //accessor method returning number of sig figs
    public int getNumSigDigits() {
        return this.numSigDigits;
    }

    // returns a string that
    public String toString() {
        // initialize the string
        String str = "";

        // loops through each item (only sig figs) and adds the digits to the string
        for (int i = SIZE-this.numSigDigits; i<SIZE; i++) {
            str+= this.digits[i];
        }

        return str;
    }

    // constructor creates object representing int n

    public BigInt(int n) {
        //initialize a new digits array
        this.digits = new int[SIZE];

        //checks if n is less than zero
        if (n<0) {
            throw new IllegalArgumentException();
        }
        // loops through up until the size
        for (int i = 0; i< SIZE; i++) {
            // for each digit, mod the digit by 10
            this.digits[SIZE-1-i] = n % 10;
            // update the divided number by 10 to n
            n = n/10;
            // checks if the number is not 0, if it is not
            if (this.digits[SIZE-1-i] != 0) {
                // increments sig digits
                this.numSigDigits++;
            }
        }
    }

    // method that compares the BigInt parameter with other
    public int compareTo(BigInt other) {
        //checks if parameter is null
        if (other == null) {
            throw new IllegalArgumentException();
        }

        //initialize a new list of integers called otherDigits
        int[] otherDigits = other.getDigits();

        // loops through each item of index size from right to left
        for (int i = SIZE -1; i >= 0; i--) {
            // if the digit is larger than current digit, return -1
            if (otherDigits[i] > this.digits[i]) {
                return -1;
            }
            // otherwise, returns 1
            else if (otherDigits[i] < this.digits[i]) {
                return 1;
            }
        }
        // we are here if none of the digits were bigger or less than other
        // meaning, they are equal
        return 0;
    }

    //helper method that returns the digits in array
    public int[] getDigits() {
        return this.digits;
    }

    //method that adds object and other
    public BigInt add(BigInt other) {
        // checks for null pointer exception
        if (other == null) {
            throw new IllegalArgumentException();
        }
        //intialize parameters of current number, carry and the final array,
        // that will be returned
        int[] otherDigits = other.getDigits();
        int num = 0;
        int carry = 0;
        int[] total = new int[SIZE];

        // loops through each item (right ro left)
        for (int i = SIZE-1; i >= 0; i--) {
            // current number is sum of both digits +carry
            num = otherDigits[i] + this.digits[i] + carry;
            carry = 0;
            // if the number reaches ten or more
            if (num >= 10) {
                // operate the carry to the next number operation
                // set carry as the current number
                carry = num;
                // carry is itself, divided by 10,
                carry = carry/10;
                // the current number is then the left over of mod 10
                num = num%10;
            }
            // set each number in new array, that will be returned, to the current number
            total[i] = num;
            // create a new big int holder, which is the final answer
            BigInt final_ans = new BigInt(total);
        }
        // check if carry is not 0, if it is not, returns arithmetic exception
        if (carry !=0) {
            throw new ArithmeticException();
        }
        // initialze final answer as the total array that will be returned
        BigInt final_ans = new BigInt(total);
        return final_ans;
    }

    public BigInt mul(BigInt other) {
        // checks for null pointers
        if (other == null) {
            throw new IllegalArgumentException();
        }

        // initialize other digits into array
        int[] otherDigits = other.getDigits();
        // initialize current number integer and a carry integer
        int num = 0;
        int carry = 0;
        // initialize a new total array
        int[] total = new int[SIZE];
        // create a temp holder, and a final answer initialization
        BigInt holder = new BigInt();
        BigInt final_ans = new BigInt();
        // number of sig figs initialized into holders for both
        // this and other numbers
        int other_sig_digits = SIZE-1-other.getNumSigDigits();
        int this_sig_digits = SIZE-1-this.getNumSigDigits();

        // loops through each number right to left of other array
        for (int i = SIZE-1; i>other_sig_digits; i--) {
            //initialize a new size of total
            total = new int[SIZE];
            // loops through each number in array from right to left of this array
            for (int j = SIZE-1; j> this_sig_digits; j--) {
                // multiplies the other digit and current digit, adding the carry
                num = otherDigits[i] * this.digits[j] + carry;
                carry = 0; //sets carry to 0
                if (num >= 10) {
                    //if the current number is more than 0,
                    // operate the carry operation, from add()
                    carry = num;
                    carry = carry/10;
                    num = num %10;
                }
                // sets holder in total to the current nunber
                total[j-(SIZE-1-i)] = num;
            }
            // big int object of total is initialized to the holder
            holder = new BigInt(total);
            // add the object from holder to the final answer for each operation of looping
            final_ans = final_ans.add(holder);

        }
        // checks if the carry is not 0, then throw a arithmetic exception
        if (carry != 0) {
            throw new ArithmeticException();
        }
        // return the final answer
        return final_ans;
    }




    /*
     * Unit Test: Following is a local main method where we put some sample tests
     * you can use to verify that this class works properly; This code should only
     * be used except for debugging and testing purposes.
     *
     */

    public static void main(String [] args) {
        System.out.println("Unit tests for the BigInt class.");
        System.out.println();

        /*
         * You should uncomment and run each test--one at a time--
         * after you build the corresponding methods of the class.
         *
         * Note that these unit tests are not complete and you should
         * think of additional tests that you should run to ensure
         * that all your methods work as expected.
         */


        System.out.println("Test 1: result should be 7");
        int[] a1 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7 };
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getNumSigDigits());
        System.out.println();

        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 16: should throw an ArithmeticException");
        int[] a20 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };  // 20 nines!
        try {
            b1 = new BigInt(a20);
            System.out.println(b1.add(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 17: result should be 5670");
        b1 = new BigInt(135);
        b2 = new BigInt(42);
        BigInt product = b1.mul(b2);
        System.out.println(product);
        System.out.println();

        System.out.println("Test 18: result should be\n99999999999999999999");
        b1 = new BigInt(a20);   // 20 nines -- see above
        b2 = new BigInt(1);
        System.out.println(b1.mul(b2));
        System.out.println();

        System.out.println("Test 19: should throw an ArithmeticException");
        try {
            b1 = new BigInt(a20);
            b2 = new BigInt(2);
            System.out.println(b1.mul(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

    }
}
