import java.util.Scanner;

public class Main {

    public static void encryption (String phrase, String keyword) {
        int phraseLength = phrase.length();
        int keywordLength = keyword.length();

        for (int i=0; i<phraseLength; i++) {
            ArrayList<Integer> phrasePositions = new Arraylist<Integer>();
            phrasePositions.add(letterPosition(phrase.charAt(i), 'a'));
        }

        for (int i=0; i<keywordLength; i++) {
            ArrayList<Integer> keywordPositions = new Arraylist<Integer>();
            keywordPositions.add(letterPosition(keyword.charAt(i), 'a'));
        }

    }

    public static int letterPosition (char x, char a) {
        int position1 = (int) x;
        int position2 = (int) a;

        return (position1-position2);
    }

    public static void main (String[] args) {
        
        Scanner input = new Scanner(System.in);
        String text = input.nextString();
        text.toLowerCase();
        text.replaceAll("[^a-zA-Z]","");

        

    }
    
}
