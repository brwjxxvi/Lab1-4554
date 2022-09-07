import java.util.ArrayList;
import java.util.Scanner;

public class Lab1 {

    public static void encryption (String phrase, String keyword) {
        int phraseLength = phrase.length();
        int keywordLength = keyword.length();
        ArrayList<Integer> phrasePositions = new ArrayList<Integer>();
        ArrayList<Integer> keywordPositions = new ArrayList<Integer>();
        
        for (int i=0; i<phraseLength; i++) {
            phrasePositions.add(letterToPosition(phrase.charAt(i), 'a'));
        }

        for (int j=0; j<keywordLength; j++) {
            keywordPositions.add(letterToPosition(keyword.charAt(j), 'a'));
        }

        for(int k = 0; k < keywordLength; k++) {
            if(k + 1 % keywordLength > 0) {
                k = 0;
            }
            else {
                phrasePositions.set(k, keywordPositions.get(k) + phrasePositions.get(k));
            }
        }

        for (int n=0; n<phrasePositions.length(); n++) {
            phrasePositions.set(n, positionToLetter(phrasePositions.get(n)));
        }
    }

    // Changes a character to its respective integer ascii value minus
    // 97 so that a is 0, b is 1, and so on
    public static int letterToPosition (char x, char a) {
        int position1 = (int) x;
        int position2 = (int) a;

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
        System.out.print("Enter encrypted text: ");
        String text = input.nextLine().toLowerCase().replaceAll("[^a-zA-Z]","");

        System.out.println(text);

    }
    
}
