import java.util.*;

public class Encryption {

    // Method to convert a string to only lowercase letters
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
        String str = new String();
        phrase = this.strip(phrase); // Phrase but only lowercase letters
        key = this.strip(key); // Key but only lowercase letters
        char[] phraseArr = phrase.toCharArray(); // Convert phrase to a charArray
        char[] keyArr = key.toCharArray(); // Convert key to a charArray
        int len = key.length(); // Length of the key charArray
        for (int i = 0; i < phraseArr.length; i++) {
            int posPhrase = letterToPosition(phraseArr[i]); // Gets the ASCII value for phraseArr[i]
            int posKey = letterToPosition(keyArr[i % len]); // Gets the ASCII value for keyArr[i % len]
            int sum = (posPhrase + posKey) % 26;
            char res = positionToLetter(sum); // Gets the character associated with the ASCII value of sum
            phraseArr[i] = res;
        }
        str = String.valueOf(phraseArr);
        return str;
    }

    // Method for decryption
    public String decrypt(String key, String phrase) {
        String str = new String();
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
                char res = positionToLetter(diff); // Gets character associated with the ASCII value of diff
                phraseArr[i] = res;
            } else {
                char res = positionToLetter(diff); // Gets character associated with the ASCII value of diff
                phraseArr[i] = res;
            }
        }
        str = String.valueOf(phraseArr);
        return str;
    }

    /* Takes text and key length as input
     * Create a jagged 2D char array, 'rows' and 'columns'
     * Jagged array has different # of columns in each row
     * The first dimension has size equal to the length of the key
     * The second dimension increments by one, using a
     * counter to go through the second dimension
     */
    public char[][] buckets(String text, int keyLen) {
        char[] textArr = text.toCharArray(); // Creates charArray using String text
        char[][] multiArr = new char[keyLen][]; // Creates 2D array, stating there is keyLen 'rows'
        for (int i = 0; i < keyLen; i++) {
            multiArr[i] = new char[text.length()]; // Each 'row' has size text.length()
        }
        int counter = -1;
        for (int i = 0; i < text.length(); i++) {
            if (i % keyLen == 0) { // If text length = keyLen, then increment counter by 1
                counter++; // Moves us on to the next 'column' in the array
            }
            multiArr[i % keyLen][counter] = textArr[i]; // Puts elements in textArr into multiArr
        }
        this.shifter(multiArr[0], 12);
        this.shifter(multiArr[1], 8);
        this.shifter(multiArr[2], 24);
        this.shifter(multiArr[4], 9);

        for (int i = 0; i < multiArr.length; i++) {
            //System.out.println("\n" + this.strip(java.util.Arrays.toString(multiArr[i])));
        }
        System.out.println("\n");

        return multiArr;

    }

    /* Takes a char array and an integer
     * goes through the char array and
     * attempts to guess the shift used to encode the text
     * returns the shifted array
     */
    public char[] shifter(char[] array, int shift) {
        for (int i = 0; i < array.length; i++) { // Goes through the array
            char curr = array[i]; // Gets the char at array[i]
            int pos = (letterToPosition(curr) - shift) % 26; // Converts to an ASCII integer value between 0-25 inclusively
            char res = positionToLetter(pos); // Converts to an a-z character
            array[i] = res; // Sets array[i] equal to char res
        }
        return array;
    }

    /* Takes a char[][]
     * the first [] contains the coded text
     * the second [] takes the keyword length
     * and attempts to break the encryption
     * given a this.buckets(String text, int len) variable
     */
    public void breakEncrypt(char[][] stuff) {
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
        Encryption secret = new Encryption();

        // System.out.println(secret.encrypt("icecubes", "I wish to speak a word for Nature, for absolute freedom and wildness, as contrasted with a freedom and culture merely civil, — to regard man as an inhabitant, or a part and parcel of Nature, rather than a member of society. I wish to make an extreme statement, if so I may make an emphatic one, for there are enough champions of civilization: the minister and the school committee and every one of you will take care of that. I have met with but one or two persons in the course of my life who understood the art of Walking, that is, of taking walks, — who had a genius, so to speak, for sauntering: which word is beautifully derived “from idle people who roved about the country, in the Middle Ages, and asked charity, under pretense of going à la Sainte Terre,” to the Holy Land, till the children exclaimed, “There goes a Sainte-Terrer,” a Saunterer, a Holy-Lander. They who never go to the Holy Land in their walks, as they pretend, are indeed mere idlers and vagabonds; but they who do go there are saunterers in the good sense, such as I mean. Some, however, would derive the word from sans terre without land or a home, which, therefore, in the good sense, will mean, having no particular home, but equally at home everywhere. For this is the secret of successful sauntering. He who sits still in a house all the time may be the greatest vagrant of all; but the saunterer, in the good sense, is no more vagrant than the meandering river, which is all the while sedulously seeking the shortest course to the sea. But I prefer the first, which, indeed, is the most probable derivation. For every walk is a sort of crusade, preached by some Peter the Hermit in us, to go forth and reconquer this Holy Land from the hands of the Infidels. It is true, we are but faint-hearted crusaders, even the walkers, nowadays, who undertake no persevering, never-ending enterprises. Our expeditions are but tours, and come round again at evening to the old hearthside from which we set out. Half the walk is but retracing our steps. We should go forth on the shortest walk, perchance, in the spirit of undying adventure, never to return, — prepared to send back our embalmed hearts only as relics to our desolate kingdoms. If you are ready to leave father and mother, and brother and sister, and wife and child and friends, and never see them again, — if you have paid your debts, and made your will, and settled all your affairs, and are a free man, then you are ready for a walk. To come down to my own experience, my companion and I, for I sometimes have a companion, take pleasure in fancying ourselves knights of a new, or rather an old, order, — not Equestrians or Chevaliers, not Ritters or Riders, but Walkers, a still more ancient and honorable class, I trust. The Chivalric and heroic spirit which once belonged to the Rider seems now to reside in, or perchance to have subsided into, the Walker, — not the Knight, but Walker Errant. He is a sort of fourth estate, outside of Church and State and People."));
        // System.out.println(secret.decrypt("icecubes", "qymubuskxgemuxsjlhsthbxmzgjqlbfkwnyvygvwmfsouohoqnhpytwsaespnsekbghyculsntigxpqsvfgwfuyjmoitymcuqxmnnpvwocvfgbrsacrkhietqvepnpvsxcvvuohhitggfpjfivytyselpgvvbbrsugqdyssxaqgkyucaekwjnpqssgepyyxjmoiunbxwugrvcgwgqoeagbowipiojielqespygsjbjitybvwmpswaigziotkiowgnemxcmmrivmqhulwukrkmuijiphvbfwupqsnwpqeqvxgybrvmxitsprwwhcqoxmdtvemydejmqjvbbxapczggfxoqvldousfmqvvqptwzuspmjrlpggqoswwwhqafjjwejswheijavsqxulwitxqzxedskrinielqushnboaviacflwopqlcxbkwvkyumpxgaricegsjacypnfvaviajcdlowthkmciscvmhompqlgvkpfhxzqqkxmihmqtnyxlgzqzgxbfgcvxjydsmvvvacoxzmomfxmisogwcheeksghebbvabaypxfvhzgxghtigniskhhpsacmpnfxwztiviulwpqpafbrvbkpnniiupkpflfrwfepccnivbjityhswacwccoxwbgvtysekiwrvysijijsnsmeflgvvbfcopqrgpfvywvsvbflgtapchemfbjiklxedsueuniiqxtivyohszgmpxfivugvgcepwzuepxweyidspxtfmbvlgsxlglqkqniijmcvgmbyfbgvgltmfbjiiiphkmpwgmvgziumoybrkwoijixinmtaqomhvmtmxyulweqvfzsseacrunfvjmymvbpyltcrfisezwoiybjgzbjitygsjmkrvbfkgwfwghtioqnpoybrzixmpaoshitxkwvpszjsoycylmsycfmcsbjsoyfzwzaajysixwtxjctmkbjiuydvwbqjuodgwaujwftemvvitcokzmylqmjxkavmnfjrspqyuybpdbjivcnieiafgniiyzgevytxniivchusxinpdouxzmuewhuijmtmpniiywqhuyowwqurqgpvwdcktuoxlpcrvbfqwiphgljryzkzglxlakjmuumplpgajcmikmfynivwdguigejrybjiubpvlmuxeivvkmvsvbfwwidyvcqvwngvvbfjazuxybjgzqphgyemkbjioitxhzqfcvmivmtmxuumgvhstywijgyenejwsaqvviggjcuefyqvwielgxcckwoiryuijbjijysqabkrwmusywhstniefltieioummtxjctlgtapchejjwoxjyieflushniiavhmfymwabkwvlviomcvgvvxxikrvbfejbghelvwslgvuywifbjiyumowzurqqbhsguajivrvmtxcefrgxgvuywijqpkpywijmphkhhifbgvrljwwaqytyytwlkxkiowszgfwnusmzuepxdsemtswheeyikrcnfzwvkrinpxzmqpfbfejbjwkxfjjwoajcdlomuivivxzinjvbfastmmuvvxjmvvcwjrywwvunftkegwjivpvoqjqlulgvvlgmisjbgwvqbpcxgvebbrumkrvbfwhqtmvigyflampabhnmpxwlfrwdgvvisilctrrlftszghvitifldeeepyjmofcfnivpgetntsftaeulfpakuxqivvvmusnuuicqpkfinwanaswusijmchanppwixihuulwzcrfgpxzmtepxcvgbjituohkquxglbrvekjguohupkpfuohxzkipxteflpixyswwmvlggbksqpmhspyzixirujhqwwvfycxkiphoueiqwwvycmpsvfwgnupwlcpnspyjihjccswsvfetybjjmgqchulwvaswusijmchazpvsecpmnpggughqqoxguasyhfbhmtmghdiegesojbrawpepxjjgzkwqgfxaugwjuwiskqqruomgvvemyqpwiuytyjrxipgacokgctwgfwikspmibuwgncrgqpvjivlglbrgtfstxfvfwvisofwlzkepmpvupgzcfjijapsvljxlmtwqlsmvmtwdouastmitmbwlqnpoisisvemghuefljspisettggnutwabtyunulwkjmxumvakcrfbfvgqewrcsmlejmebprumdiniokwlvsvbfvalgvuyfqkvqavisikqfikhpvhmtgjuogwbqlcpfwmjumfyemfbqxjyxedsgvpiuxzmmrkaixtcvacflijmtvchulwqueuisxgnhswlulwavevypylakhgiggzctgjuohkbcxguohhmqtny"));

        char[][] doubleArr = secret.buckets(
                "lgxalyxuofgewbnjoqppzzekymtoijmwcxrkvbnwtaazgueihlksrrrdosnzxvzuokygumrontlmpwutjsyewbnwlekzgjyecqyspaankuthhtunehhillenwwuftdniojionfwmiorbklhaamokairazmpkwbnwwkxlydignbnwpqaxrwbhxwsmpkwivduiumzmscxitvlenwtlhapzgkswwlyeognkoyannbzwswwlkfjkhvglunndoniwwmtbouwizmrarisylwmbukaucpglidjdkwnprzkdyhxazlhwcnguuhcgvwolumzwlhdazzapjzzeagnaaklkemtstqamsgracpgfwauwbwddnzhwfkamzzaprbxwvajtyzenbmijepbbumswwlzzapjnzwrwlixwfquazmduxnigrkcitvckwazsbhnekkeacpofgorvnwrpqizzaznaispamwajoxbmxnaprwteykfvkppaaqkfcarazzapcpkeonnekktqmggjtpqmrwsofmisraowxfapdzkohwcixlrajtrqraemgdspxcyasjjbajeouiicobmmyagjqmxuunrwakcndloliabpkjetczggrzrvgjyixvulojhpkjaxbwrmtaugaffewqyzezlwtviprwtfapdzkzaopwuvijcmtlikwauxckdzywbqciysrebbullaxviwswrlyzeyjvtgtyjzxqtdnuumtsqmtalkxsglahjvjkcwymouajwwzzehyakwijpirdipblkxeycaolioowxlujjbkxondangwaemxlhwcvglunnqykoevxkjfalbgkopqmxoionekkhkdtjzarnpgvnkjzzstwutgjtebwajslrzolezyzuleocwajgwutgftwcbkeppcwzwayqvglunnpkjpnxxkjphjkkssbxzzzeewnofipndgjiacguxnwccxwtdjbokaldzkeypqqzasjxbzgbaowafdewvglunnpkjsaunolrabqjwsewbnwiijoofaprwtgrbjviqonlcrlirjbkvbhrvjfeobwllhavitohkuwucswcpkjcuaqroehugumnanltgthxwqstpqmrsnzbkgheuxcisnhrmuftdnoxssojvjkmktmgfdpjtqnirrittupwizmraraygujlwsxoncihdecaiykioqixvajmlaepujvjvaiyitvfqutuxdnnijxuhktgukewakutofpewvawuujrebxugrabbcgrgvituoqulsskahwasmkamigmbxzzsbhn",
                4);

        secret.breakEncrypt(doubleArr);
    }

    
}
