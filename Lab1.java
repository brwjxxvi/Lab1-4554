import java.util.*;

public class Lab1 {

    public static void encryption (String phrase, String keyword) {
        int phraseLength = phrase.length();
        int keywordLength = keyword.length();
        ArrayList<Integer> phrasePositions = new ArrayList<Integer>();
        ArrayList<Integer> keywordPositions = new ArrayList<Integer>();
        ArrayList<Character> encryptionArray = new ArrayList<Character>();
        
        // Changing the phrase into an ArrayList of integer values
        for (int i=0; i<phraseLength; i++) { //Creates an array to hold number values of the letters in the phrase
            phrasePositions.add(letterToPosition(phrase.charAt(i)));
        }

        // Changing the keyword into an ArrayList of integer values
        for (int j=0; j<keywordLength; j++) {//Creates an array to hold number values of the letters in the keyword
            keywordPositions.add(letterToPosition(keyword.charAt(j)));
        }

        /* Adds the integer values in keywordPositions
         * and phrasePositions ArrayLists and performing
         * modulus 26 to get the value of them between 0 & 26
        */
        for(int k = 0; k < phraseLength; k++) {//Encrypts the message by adding the values in the phrase and the keyword
            //If k > keyLength, have to 'reset' k using the modulo function
            if(k >= keywordLength) {
                phrasePositions.set(k, keywordPositions.get(k % keywordLength) + phrasePositions.get(k));
            }
            else {
                phrasePositions.set(k, keywordPositions.get(k) + phrasePositions.get(k));
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

        return (position1 - (int) 'a');
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
        System.out.println(text);

    }
    
}
