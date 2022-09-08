import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lab1 {

    public static void encryption (String phrase, String keyword) {
        int phraseLength = phrase.length();
        int keywordLength = keyword.length();
        ArrayList<Integer> phrasePositions = new ArrayList<Integer>();
        ArrayList<Integer> keywordPositions = new ArrayList<Integer>();
        ArrayList<Character> encryptionArray = new ArrayList<Character>();
        
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
         * Convert the value of sum to a char using positionToLetter
         * Add that value to character Arraylist encryptionArray
         */
        for(int k = 0; k < phraseLength; k++) {//Encrypts the message by adding the values in the phrase and the keyword
            //If k > keyLength, have to 'reset' k using the modulo function
            int sum; // Variable for storing the result of summing the two ArrayList values
            if(k >= keywordLength) {
                sum = (keywordPositions.get(k % keywordLength) + phrasePositions.get(k)) % 26;
            }
            else {
                sum = keywordPositions.get(k) + phrasePositions.get(k);
            }
            encryptionArray.add(positionToLetter(sum));
        }

        /* The first phrase equality gives an error
         * The other two equalities give the same output, but not what we want
         * If the keyword is 'keyword' and the phrase is 'testing'
         * They return '~iwj'
         */
        //phrase = getStringEncryption(encryptionArray);
        //phrase = encryptionArray.stream().map(Object::toString).collect(Collectors.joining(""));
        phrase = encryptionArray.stream().map(e->e.toString()).reduce((acc, e) -> acc + e).get();
        System.out.println(phrase);

    }

    //Method to convert from an ArrayList<Integer> to a string
    public static String getStringEncryption(ArrayList<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());//Used to help build the string
        for(Character ch: list) {
            builder.append(positionToLetter(list.get(ch)));//adds the character value of the number to the end of the string
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
        
        Scanner input = new Scanner(System.in);
        String keyword = "keyword";
        System.out.print("Enter encrypted text: ");
        String text = input.nextLine().toLowerCase().replaceAll("[^a-zA-Z]","");
        System.out.println(text);
        encryption(text, keyword);

    }
    
}
