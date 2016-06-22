import java.util.ArrayList;

public class TwoByThree {

    static ArrayList<Character> abcs = new ArrayList<Character>();
    static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    static String[] combos = {"000111", "001011", "001101", "001110", "010011",
            "010101", "010110", "011001", "011010", "011100",
            "100011", "100101", "100110", "101001", "101010",
            "101100", "110001", "110010", "110100", "111000"};
    static String globalInputString;
    static Dictionary dictionary = new Dictionary();

    public static void main(String[] args) {

        //get input from command line
        String puzzle = args[0].toLowerCase();
        checkUserPuzzle(puzzle);
        String spacingIndicator = args[1]; 
        String formattedPuzzle = formatPuzzleWithSpace(puzzle, spacingIndicator); 
        globalInputString = formattedPuzzle;

        //erase letters from the alphabet array
        prepareAlphabet(puzzle);

        //iterate through remaining alphabet letter combinations
        //plug in combinations of letters for each combination
        iterateLetterCombos();
    }

    static void iterateLetterCombos() {
        for (int i = 0; i < abcs.size() -1 ; i++) {
            for (int j = i+1; j < abcs.size(); j++) {
                tryCombinations(abcs.get(i), abcs.get(j));
            }
        }
    }

    static void prepareAlphabet(String s) {
        for (char c : alphabet.toCharArray()) {
            abcs.add(c);
        }

        System.out.println("Preparing the alphabet...");
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '_') {
                int remove = abcs.indexOf(s.charAt(i));
                System.out.println("trying to remove: ..." + s.charAt(i) + "...");
                if (abcs.contains(s.charAt(i))) {
                    abcs.remove(remove);
                    System.out.println("Removed: " + s.charAt(i));
                } else {
                    System.out.println("Already removed: " + s.charAt(i));
                }
            }
        }
    }

    static void tryCombinations(char a, char b) {
        for (int i = 0; i < combos.length; i++) {
            char[] currentCombo = globalInputString.toCharArray();
            replaceUnderscores(a, b, currentCombo, combos[i]);
        }
    }

    static void replaceUnderscores(char a, char b, char[] array, String currentBinary) {
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '_') {
                if (currentBinary.charAt(counter) == '0') {
                    array[i] = a;
                } else if (currentBinary.charAt(counter) == '1') {
                    array[i] = b;
                }
                counter++;
            }
        }

        String answers = String.valueOf(array);
        checkAgainstDictionary(answers);

    }

    /*
     * Checks "puzzle" provided by user to ensure it has 6 underscores. 
     * Outputs error message and exits program if bad input.
    */
    static void checkUserPuzzle(String userPuzzle) {
        int underscoreCount = 0; 
        for (char c : userPuzzle.toCharArray() ) { // count number of underscores in userPuzzle
            if (c == '_') ++underscoreCount; 
        }
        if (underscoreCount != 6) {
            System.out.println("Invalid input. Puzzle requires six missing letters.\n");
            System.exit(0);
        }
    }

    /*
     * Pre-condition: userPuzzle is one word. spacingIndicator is in the format:
     *                 "<size of first word>,<size of second word>".
     * Post-condition: returns a string equivalent to userPuzzle argument except 
     *                 with a space inserted at index specified by spacingIndicator. 
    */

    //TODO: fix so that input accepts more than 2 words

    static String formatPuzzleWithSpace(String userPuzzle, String spacingIndicator) {
        //get length of first word
        int endIndex = spacingIndicator.indexOf(','); 
        String lenFirstWordStr = spacingIndicator.substring(0, endIndex);
        int lenFirstWordInt = Integer.parseInt(lenFirstWordStr); 
        //insert space
        StringBuilder formattedUserPuzzle = new StringBuilder(userPuzzle); 
        formattedUserPuzzle.insert(lenFirstWordInt, " ");  
        return formattedUserPuzzle.toString(); 
    }

    static void checkAgainstDictionary(String possibleAnswer) {
        String[] answersArray = possibleAnswer.split(" ");
        if (answersArray.length == 1) {
            if (dictionary.lookup(answersArray[0])) {
                System.out.println(answersArray[0]);
            }
        }
        else if (answersArray.length == 2) {
            if (dictionary.lookup(answersArray[0]) && dictionary.lookup(answersArray[1])) {
                System.out.println(answersArray[0] + " " + answersArray[1]);
            }
        } else if (answersArray.length == 3) {
            if (dictionary.lookup(answersArray[0]) && dictionary.lookup(answersArray[1]) && dictionary.lookup(answersArray[2])) {
                System.out.println(answersArray[0] + " " + answersArray[1] + " " + answersArray[2]);
            }
        } else if (answersArray.length == 4) {
            if (dictionary.lookup(answersArray[0]) && dictionary.lookup(answersArray[1]) && dictionary.lookup(answersArray[2]) && dictionary.lookup(answersArray[3])) {
                System.out.println(answersArray[0] + " " + answersArray[1] + " " + answersArray[2] + " " + answersArray[3]);
            }
        }
    }

}
