/* File: Histogram.java
 * Author: CS112 Instructor
 * 
 * Purpose: This is a potential solution to the
 * Histogram problem.
 * 
 * Name: Batyr Issabekov
 * 
 */

import java.util.Scanner;

public class Histogram { 
    
    private static final int SENTINAL = -999;          // sentinal value to signal endo of input
    private static final int MAX_NUMBERS = 20;         // maximum number of numbers to input
    private static final double UPPER_BOUND = 100.0;   // largest numbers accepted as data
    private static final double LOWER_BOUND = 0.0;     // smallest numbers accepted as adata
    private static final int NUM_BINS = 10;            // number of bins in range [0..100]

    //private static final int BIN_SIZE = ??           // size of each bin
   
    /*
     * Method to show an example of using StringBuilder.
     *
     * You will also notice that this method is called from the 
     * main function. 
     *
     */
    public static String getHeaderAsString( String me ) {

	// Create an instance of the StringBuilder class
	// which allows us to create an object of 
	// a series of strings that can then be converted 
	// into one large string via the toString method.
	//
    	StringBuilder sb=new StringBuilder();

        sb.append( System.getProperty("line.separator") );
        sb.append( "Welcome to the Histogram Program " + me + "!" );
	me = getFirstName(me);
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "This program will print out a histogram of the numbers" );
        sb.append( System.getProperty("line.separator") );
        sb.append( "input by you " + getFirstName(me) + "." );
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "Please enter up to " + MAX_NUMBERS + " doubles or " + SENTINAL + " to stop input!" );
        sb.append( System.getProperty("line.separator") );

        return sb.toString();
    }

    /* 
     * Method to return the first name of the user in case
     * the full name was entered. 
     */
    public static String getFirstName(String name ) {
        // Note that add the " " to string to be sure
        // there is something to split
	return (name+" ").split(" ")[0]; 
    }

    /* 
     * local main test driver
     *
     */
    public static void main(String[] args) {  

	// Connect to the keyboard as the input stream
        Scanner userInput = new Scanner( System.in );

        System.out.print( "And who am I working with today? " );
        String user = userInput.nextLine();

	String heading = getHeaderAsString( user );

        // Print out welcome message
        System.out.println( heading ); 
        
        // Call the method which prompts the user
        // to input the numbers that will be used
        // to build the histogram.
        double[] numbers = inputNumbers( userInput );

	// Call the method to reate the array histogram
	int[] histogram = calculateHistogram( numbers );

	// Print the historgram
	System.out.println( toString( histogram ) );
    }

    /*
     *
     * COMPLETE YOUR METHODS
     *
     */

    public static int [] calculateHistogram( double [] numbers ) {
        
    
        // This method must determine the appropriate bin of the 
        // histogram to update by using a loop. To be clear,
        // you may NOT just explicitly account for all  
        // possibilities, e.g., the following is a bad solution:

        // initialize a new array of integers to be the histogram
        int [] histogram = new int[numbers.length];

        // for loop, goes through each number in inputted array of doubles
        for (int i=0; i<numbers.length; i++) {
            // if number is even (in our case e.g. 20.0 or 30.0)
            if(numbers[i]%10 == 0) {
                //that number is assigned to a bin in histogram
                // used math.round in order to round and not get 0.5 for example
                //decrease 1 because we would assign that number to the previous bin
                histogram[i] = Math.round((int)numbers[i]/NUM_BINS-1);
            }
            
            else{
                histogram[i] = Math.round((int)numbers[i]/NUM_BINS);
            }
        }
        //returns the array of histograms
        return histogram;
    }

    public static String toString( int [] histogram ) {
        
    
        // This method must also use a loop that iterates 
        // over the histogram array to form the string
        // representaion of it.
    
        // The histogram can be visualzed as a series of
        // buckets, where each bucket represents one range
        // of the histogram:
    
        // The string returned should only contain the string representation
        // of the histogram and no other verbeage. It should function
        // like the toString method of the Array class but specific to
        // creating a histogram.
    
        // You may want to create an instance of the
        // StringBuilder class to assist you in this method.
        // Follow the code in the method getHeaderAsString
        // as a guide.

        

        //create a new array of strings of size bins
        String[] hist_str = new String[NUM_BINS];
        String star = "*";
        //for loop to loop through histogram array
        for (int i = 0; i< histogram.length; i++) {
            // for loop inside a for loop to loop through the number of items in a 'bucket'
            for (int j = 0; j <histogram[i]; j++){
                hist_str[i] += star;
            }
        }
        System.out.println(hist_str[0]);
        

        // from getHeader as String method:
        // create a new string builder
        StringBuilder sb=new StringBuilder();
        // for loop to build a string (the buckets)
        // as long as i is less than upper bound, and we add 10 to each buckets to separate them
        int n = 0;
        for (int i = 0; i<UPPER_BOUND; i+=10){
            // first case only because we have to add "[" first
            if (i==0) {
                // appending to the string the bucket and the range of the bucket e.g. (0-10) and the string of
                // hist_str as '*' 's
                sb.append("[" + i +".."+(i+10)+ "]: " );
                n +=1;
                sb.append(System.getProperty("line.separator"));
            }
            // all other cases, (not first bucket)
            else {
                sb.append("(" + i +".." + (i+10) + "]: ");
                n+=1;
                sb.append(System.getProperty("line.separator"));
            }
        }

        
        return sb.toString();

    }

    public static boolean validInput( double num ) {
        // if the number is less than lower bound, returns false
        if (num < LOWER_BOUND) {
            return false;
        }
        // if the number is more than upper bound, returns false
        if (num > UPPER_BOUND) {
            return false;
        }
        //otherwise, returns true
        return true;
    }

    public static double[] inputNumbers( Scanner scan) {
        double number = scan.nextDouble();

        double[] numbers = new double[MAX_NUMBERS];

        // for loop while i is less than max numbers ( to keep track of inputs)
        for (int i=0; i < MAX_NUMBERS; i++) {
            number = scan.nextDouble();
            // if the number is sentinal, we break out of the loop
            if (number == SENTINAL) {
                break;
            }
            // if the number is not valid input, asks to input a valid number
            while (validInput(number) == false) {
                System.out.println("Enter a valid number");
                number = scan.nextDouble();
            }
            // otherwise, adds number to the array
            numbers[i] = number;

        }
        //returns the array
        return numbers;
    }

} // end of class
