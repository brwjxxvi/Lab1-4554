import java.util.*;
import javax.swing.plaf.synth.SynthInternalFrameUI;

public class Encryption {

    public String strip(String phrase) {
        phrase = phrase.replaceAll("[^a-zA-Z]", "").toLowerCase();
        return phrase;
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
        int position = x + a;
        char letter = (char) position;
        return letter;
    }

    // Method for encrypting
    public String encrypt(String key, String phrase) {
        phrase = this.strip(phrase); // Phrase but only lowercase letters
        key = this.strip(key); // Key but only lowercase letters
        char[] phraseArr = phrase.toCharArray(); // Convert phrase to a charArray
        char[] keyArr = key.toCharArray(); // Convert key to a charArray
        int len = key.length(); // Length of the key charArray
        for (int i = 0; i < phraseArr.length; i++) {
            int posPhrase = letterToPosition(phraseArr[i]); // Gets the ASCII value for phraseArr[i]
            int posKey = letterToPosition(keyArr[i % len]); // Gets the ASCII value for keyArr[i % len]
            int sum = (posPhrase + posKey) % 26;
            char res = (char) (positionToLetter(sum)); // Gets the character associated with the ASCII value of sum
            phraseArr[i] = res;
        }
        return java.util.Arrays.toString(phraseArr); // returns the phrase charArray in a string format
    }

    // Method for decryption
    public String decrypt(String key, String phrase) {
        phrase = this.strip(phrase); // Phrase but only lowercase letters
        key = this.strip(key); // Key but only lowercase letters
        char[] phraseArr = phrase.toCharArray(); // Convert phrase to a charArray
        char[] keyArr = key.toCharArray(); // Convert key to a charArray
        int len = key.length(); // Length of the key charArray
        for (int i = 0; i < phraseArr.length; i++) {
            int posPhrase = letterToPosition(phraseArr[i]); // Gets the ASCII value for phraseArr[i]
            int posKey = letterToPosition(keyArr[i % len]); // Gets the ASCII value for keyArr[i % len]
            int diff = posPhrase - posKey;
            if (diff < 0) { // Need diff to be between 0 and 25 inclusively
                diff = 26 + diff;
                diff = positionToLetter(diff); // Gets character associated with the ASCII value of diff
                char res = (char) diff;
                phraseArr[i] = res;
            } else {
                diff = positionToLetter(diff); // Gets character associated with the ASCII value of diff
                char res = (char) diff;
                phraseArr[i] = res;
            }
        }
        return java.util.Arrays.toString(phraseArr);
    }

    public char[][] cringe(String text, int keyLen) {
        char[] textArr = text.toCharArray();
        char[][] multiArr = new char[keyLen][];
        for (int i = 0; i < keyLen; i++) {
            multiArr[i] = new char[text.length()];
        }
        int counter = -1;
        for (int i = 0; i < text.length(); i++) {
            if (i % keyLen == 0) {
                counter++;
            }
            multiArr[i % keyLen][counter] = textArr[i];
        }
        this.shiftMyBalls(multiArr[0], 12);
        this.shiftMyBalls(multiArr[1], 8);
        this.shiftMyBalls(multiArr[2], 24);
        this.shiftMyBalls(multiArr[4], 9);

        for (int i = 0; i < multiArr.length; i++) {
            System.out.println("\n" + this.strip(java.util.Arrays.toString(multiArr[i])));
        }
        System.out.println("\n");

        return multiArr;

    }

    public char[] shiftMyBalls(char[] array, int shift) {
        for (int i = 0; i < array.length; i++) {
            char ugh = array[i];
            int bruh = ((ugh - 'a') + shift) % 26 + 'a';
            array[i] = (char) bruh;
        }
        return array;
    }

    public void BUILDINGCOCK(char[][] stuff) {
        char[] finalArr = new char[stuff[0].length * stuff.length];
        int counter = -1;
        for (int i = 0; i < finalArr.length; i++) {
            if (i % stuff.length == 0) {
                counter++;
            }
            finalArr[i] = stuff[i % stuff.length][counter];
        }
        System.out.println("\n" + this.strip(java.util.Arrays.toString(finalArr)));

    }

    public static void main(String[] args) {
        Encryption bruh = new Encryption();
        System.out.println(bruh.encrypt("bruh", "abcdefz"));
        System.out.println(bruh.decrypt("truh", "tswa"));

        char[][] doubleBruh = bruh.cringe(
                "qjapkofclpgauficevhvujgebkgtdjhzctdssphzrvgnrbvvoccgueecjvolblkezgljejhmfyftspacmrknxwfhoiasvifbkaskseuiecjfeihgutlrqvhvvafdvbsupvqluowhzgspglgmjqjapkofclpgauijikgdkctterqzerpdlqgiohjitgweuiwlaspglgmjoffgrwfcctskutfhzgcfblgnkggheeqjapksvoejgsiejsngnztljetfqrtfujcpywumepwkwnbbgynzbsfdzhaqnkcectysectzqsnaeodaszgghcimhvoxfsrhzqsngffavhdgutyghspacmkkszbunuuskvhvglwdpcxuiusujaebwnakhsekjhzctucfqtkojiekkwckeskuejwfvhvqjapkcytagvaeacugtikveutyseuecjwublhapskssfeoddqikkwckeskuejwfvhvwjkmgzwoeehsvifb",
                5);

        // System.out.println((bruh.strip(java.util.Arrays.toString(bruh.shiftMyBalls(doubleBruh[2],
        // 24)))));
        bruh.BUILDINGCOCK(doubleBruh);
    }

    
}
