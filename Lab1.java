import java.util.ArrayList;
import java.util.Scanner;

public class Lab1 {

    public static void encryption (String phrase, String keyword) {
        int phraseLength = phrase.length();
        int keywordLength = keyword.length();
        ArrayList<Integer> phrasePositions = new ArrayList<Integer>();
        ArrayList<Integer> keywordPositions = new ArrayList<Integer>();
        
        for (int i=0; i<phraseLength; i++) {
            phrasePositions.add(letterPosition(phrase.charAt(i), 'a'));
        }

        for (int i=0; i<keywordLength; i++) {
            keywordPositions.add(letterPosition(keyword.charAt(i), 'a'));
        }

        for(int k = 0; k < keywordLength; k++) {
            if(k + 1 % keywordLength > 0) {
                k = 0;
            }
            else {
                phrasePositions.set(k, keywordPositions.get(k) + phrasePositions.get(k));
            }
        }
    }

    public static int letterPosition (char x, char a) {
        int position1 = (int) x;
        int position2 = (int) a;

        return (position1-position2);
    }

    public static void main (String[] args) {
        
        Scanner input = new Scanner(System.in);
        String text = input.nextLine().toLowerCase().replaceAll("[^a-zA-Z]","");

        System.out.println(text);

    }
    
}
