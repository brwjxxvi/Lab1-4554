import java.util.ArrayList;
import java.util.Scanner;

public class Lab1 {

    public static void encryption (String phrase, String keyword) {
        int phraseLength = phrase.length();
        int keywordLength = keyword.length();
        ArrayList<Integer> phrasePositions = new ArrayList<Integer>();
        ArrayList<Integer> keywordPositions = new ArrayList<Integer>();
        ArrayList<String> encryptionArray = new ArrayList<String>();
        
        for (int i=0; i<phraseLength; i++) { //Creates an array to hold number values of the letters in the phrase
            phrasePositions.add(letterToPosition(phrase.charAt(i)));
        }

        for (int j=0; j<keywordLength; j++) {//Creates an array to hold number values of the letters in the keyword
            keywordPositions.add(letterToPosition(keyword.charAt(j)));
        }

        /* Need to add the two Integer ArrayLists together
         * Going from 0 to phraseLength
         * Set the position in phrasePositions equal to the new sum value
         * If keywordLength < phraseLength, use modulus to go back to
         * the beginning of keywordPositions
         * If the sums of the values in spot k of the two ArrayLists
         * is greater than 26, do modulus 26 to remain in the alphabet
         */
        for(int k = 0; k < phraseLength; k++) {//Encrypts the message by adding the values in the phrase and the keyword
            //If k > keyLength, have to 'reset' k using the modulo function
            int sum; // Variable for storing the result of summing the two ArrayList values
            if(k >= keywordLength) {
                sum = (keywordPositions.get(k % keywordLength) + phrasePositions.get(k)) % 26;
                phrasePositions.set(k, sum);
            }
            else {
                sum = keywordPositions.get(k) + phrasePositions.get(k);
                phrasePositions.set(k, sum);
            }
        }

        phrase = getStringEncryption(phrasePositions);
    }

    //Method to convert from an ArrayList<Integer> to a string
    public static String getStringEncryption(ArrayList<Integer> list) {
        StringBuilder builder = new StringBuilder(list.size());//Used to help build the string
        for(Integer num: list) {
            builder.append(positionToLetter(list.get(num)));//adds the character value of the number to the end of the string
        }
        return builder.toString();//Return a toString; Will need to set equal to a string
    }

    // Changes a character to its respective integer ascii value minus
    // 97 so that a is 0, b is 1, and so on
    public static int letterToPosition (char x) {
        int position1 = (int) x;
        int position2 = (int) 'a';

        return (position1-position2);
    }

    // Changes an integer back into its respective character value from 97 to 123
    public static char positionToLetter (int x) {
        int a = 97;
        int position = a + x;
        char letter = (char) position;

        return letter;
    }

    public static void main (String[] args) {
        
        // Scanner input = new Scanner(System.in);
        // String keyword = "keyword";
        // System.out.print("Enter encrypted text: ");
        // String text = input.nextLine().toLowerCase().replaceAll("[^a-zA-Z]","");
        // System.out.println(text);
        // encryption(text, keyword);
        // System.out.println(text);

        //Testing to try and find what exactly is wrong
        //Testing positionToLetter and letterToPosition
        System.out.println(letterToPosition('t'));
        System.out.println(letterToPosition('e'));
        System.out.println(letterToPosition('s'));
        System.out.println(letterToPosition('t'));

        System.out.println(positionToLetter(19));
        System.out.println(positionToLetter(4));
        System.out.println(positionToLetter(18));
        System.out.println(positionToLetter(19));
        //This means that both positionToLetter and letterToPosition work

        //Testing to make sure our addition works
        System.out.println((letterToPosition('t') + letterToPosition('k'))%26);
        System.out.println((letterToPosition('e') + letterToPosition('e'))%26);
        System.out.println((letterToPosition('s') + letterToPosition('y'))%26);
        System.out.println((letterToPosition('t') + letterToPosition('w'))%26);

        //Initializing array to test getStringEncryption()
        ArrayList<Integer> testing = new ArrayList<Integer>();
        testing.add(3);
        testing.add(8);
        testing.add(16);
        testing.add(15);
        System.out.println(testing);

        //Viewing output from getStringEncryption
        System.out.println(getStringEncryption(testing));

    }
    
}
