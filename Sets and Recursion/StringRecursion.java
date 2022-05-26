public class StringRecursion {
    //Name: Batyr Issabekov
    //PS4 resubmission
 
    public static void printReverse(String str) {
        if (str == null) {
            return;
        }
        else if (str.length() == 0){
            return; //if length of string is 0, does not return anything
        }
        else {
            printReverse(str.substring(1));
            System.out.print(str.charAt(0));
            
        } //otherwise, recursive call to  string from index 1, and print character at index 0
    }

    public static String trim(String str) {
        
        if (str.charAt(0) != ' ' && str.charAt(str.length()-1) != ' ') {
            return str.substring(0, str.length());
            // case if there is no space in front and behind the string
        }
        else if (str.charAt(0) == ' ' && str.charAt(str.length()-1) == ' ') {
            return trim(str.substring(1, str.length()-1));
            // case if there is a space in front and behind, recursive call on
            // the substring from index1 to index before the last character
        }
        else if (str.charAt(0)== ' ' && str.charAt(str.length()-1) != ' '){
            return trim(str.substring(1, str.length()));
            // case if there is a space in front, and no space behind
            // recursive call on index from 1 to the end
        }
        else {
            return trim(str.substring(0, str.length()-1));
            // otherwise, a recursive call from index of 0 to index of before the last char
        }    
    }
    
    public static int find(char ch, String str) {
        if (str == null) {
            return -1;
        }
        if (str.equals("")) {
            return -1;
            //base cases if null pointer, and if str is empty
        }
            else { //if the first character is the character we are looking for,
                if (str.charAt(0) == ch) {
                    return 0;
                    //returns 0
                }
            // otherwise,
            else {
                // index is assigned to the recursvie call on the substring from index 1
                int index = indexOf(ch, str.substring(1));
                // after the recursive call, if index is still -1, return -1
                if (index == -1) {
                    return -1;
                }
                else { //otherwise, add 1 is added to each recursive call
                    return 1+ index;
                }
            }
        }
    }

    public static String weave(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return null;
        }
        if (str1.length() == 0 && str2.length() == 0) { //if both empty, return empty
            return "";
    }
        else if (str1.length() == 0) {
            return str2; //if one is empty, returns 2
        }
        else if (str2.length() == 0) { //if 2 is empty, returns 1
            return str1;
        }

        
        return str1.substring(0,1) + str2.substring(0,1) + weave(str1.substring(1), str2.substring(1));
        }


    public static int indexOf(char ch, String str) {
        //this whole method is the same as find()

        //as i understnand, it asked for the same thing?

        if (str == null) {
            return -1;
        }
        if (str.equals("")) {
            return -1;
            //base cases if null pointer, and if str is empty
        }
        else {//if the first character is the character we are looking for,
            if (str.charAt(0) == ch) {
                return 0;//returns 0
            }
        // otherwise,
        else {
            // index is assigned to the recursvie call on the substring from index 1
            int index = indexOf(ch, str.substring(1));
            // after the recursive call, if index is still -1, return -1
            if (index == -1) {
                return -1;
            }
            else {//otherwise, add 1 is added to each recursive call
                return 1+ index;
            }
        }
    }
}
    public static void main(String [] args) {
        printReverse("Terriers");
        
        System.out.println(trim("  hello   world  "));
        System.out.println(find('b', "Rabbit"));
        System.out.println( find('P', "Rabbit") );
        System.out.println( indexOf('b', "Rabbit") ); 
        System.out.println( indexOf('i', "Rabbit") );
    }
}


