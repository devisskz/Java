/*
 * Student First Name: Batyr
 * Student Last Name: Issabekov 
 * Student BU Number: U69145032
 * Purpose: PS4PR3 Resubmission
 */

public class Set  {
    private static final int SIZE = 10; // default size of initial set
                                
    private int[] set;      // array referece to the set
    private int size;    // current size of the set
    private int next;       // index to next available slot in the set array
    
    
    public Set() {
        //initialize size
        size = SIZE;
        //create a new array of size SIZE
        int array[] = new int[SIZE];
        //set next to 0
        this.next = 0;
        // set is the array
        this.set = array;
    }
    
    public Set(int[] A) {
        // insert each item from array A and increment next as we go
        for (int i = 0; i< A.length; i++) {
            insert(A[i]);
            this.next++;
        }
    }
    
    public Set clone() {
        // create a new set called close (which is the clone)
        Set clone = new Set();

        // loop through each item and insert into the clone
        for (int i = 0 ;i< next; i++) {
            clone.insert(this.set[i]);
        }
        return clone;     
    }
    
    // This method reallocates the array set to twice as big and copies all the elements over.
    // It is called only by insert.
    //
    // Note that this is the reason that in this class
    // the member size is not a class variable (i.e. static)
    // and it is not final, because the set can grow and size
    // will be modified accordingly.
    
    private void resize() {
        size *= 2;
        int[] temp = new int[size];
        for(int i = 0; i < set.length; ++i) {
            temp[i] = set[i];
        }
        set = temp;
    }
        
    public  String toString()  {
        // using concatenation:
        // if next is 0, the array is empty, returns empty set
        if (next == 0) {
            return "[]";
        }
        // create a new string starting with [
        String str = "[";
        // for each item
        for (int i = 0; i< next-1; i++) {
            // add each integer and add a comma
            str += set[i] + ", ";
        }
        // add last integer and close the array
        str += set[next-1] + "]";
        //return the string
        return str;   
    }
     
    public int cardinality() {
        return next;   
    }
    
    public  boolean isEmpty() {
        // if next is 0, the array is empty
        return this.next == 0;   
    }
      
    public boolean member(int k) {
        // if the set is not empty
        if (isEmpty() == false) {
            // for each integer
            for (int i = 0; i< this.SIZE; i++) {
                // if the integer is k, it is a member
                if (set[i] == k) {
                    return true;
                }
                //otherwise, returns false
            }
            return false;
        }
        // we are here if array is empty, returns false
        else {
            return false;   
        }
}    
   
    public  boolean subset(Set T) {
        // for each item in set
        for (int i = 0; i< this.SIZE; i++) {
            // if t is not a member of the set
            if (T.member(this.set[i]) == false) {
                //returns false
                return false;
            }
            //otherwise, returns true
        }
        return true;   
    }
    
    public  boolean equal(Set T) {
        // if T is a subset and set is subset
        if (this.subset(T) == true && T.subset(this) == true){
            //returns true
            return true;
        }
        //otherwise, returns false
        else{
            return false;
        }
    }
       
    public void insert(int k) {
        // check if k is a  not amember
        if (this.member(k) == false) {
            // if next is larger than SIZE
            if (this.next >= this.SIZE){
                // call on resize
                resize();
            }
            // set of next is set to k
            this.set[next] = k;
            //increment next
            this.next++;
        }
    }
    
    public void delete(int k) {
        // loops through each integer
        for (int i = 0; i < next; i++) {
            //if a integer in set is a k
            if (set[i] == k) {
                // loop through each item again (not including last next)
                for (int j = i; j< next-1; j++) {
                    //set the current index integer to next
                    set[j] = set[j+1];
                }
                //decrement next
                next--;
                //break out of the loop
                break;
            }
        }
    }
  
    public Set union(Set T) {
        // create a clone
        Set clone = clone();
        // loop through each integer in set T
        for (int i =0; i <T.next; i++) {
            // if it is not a member
            if (this.member(T.set[i]) == false) {
                // insert the integer into the close
                clone.insert(T.set[i]);
            }
        }
        //return the close
        return clone;   
    }
   
    public Set intersection(Set T) {
        //create a new set
        Set intersection = new Set();
        // loop through each item in set
        for (int i =0; i <next; i++) {
            // if an int in t is a member of this set
            if (T.member(this.set[i])) {
                // insert that integer into the newly created set
                intersection.insert(set[i]);
            }
        }
        //return that new set
        return intersection;   
    }
    
    public Set setdifference(Set T) {
        //createa  new set called difference
        Set difference = new Set();
        // loop through each integer in original set (SIZE length looping)
        for (int i = 0; i< SIZE; i++) {
            // if t does not have a member in set
            if (T.member(this.set[i]) == false) {
                // insert that integer into the newly created set
                difference.insert(this.set[i]);
            }
        }
        //return the new difference set
        return difference;   
    }
      
}
