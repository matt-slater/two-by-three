import java.util.ArrayList;
import java.util.Scanner;
import java.util.jar.Pack200;


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
        checkCommandLineArgs(args);
        //get input from command line
        String puzzle = args[0].toLowerCase();
        checkUserPuzzle(puzzle);
        String spacingIndicator = args[1];
        checkSpacingIndicator(spacingIndicator, puzzle);
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
     * Checks to make sure user provided 2 command line arguments.
     * If there are not two arguments, tells user how to run program
     * from command line and quits program.
     */
    static void checkCommandLineArgs(String[] args) {
        System.out.println("Checking command line arguments...");
        if (args.length != 2) {
            System.out.println("Error: Usage: java -cp two-by-three-1.0-SNAPSHOT.jar TwoByThree <puzzle> <word lengths>");
            System.exit(1);
        }
    }
    /*
     * Checks "puzzle" provided by user to ensure it has 6 underscores. 
     * If puzzle does not meet criteria, outputs error message and keeps
     * accepting puzzles from user until puzzle has six underscores.
    */
    static void checkUserPuzzle(String userPuzzle) {
        System.out.println("Checking puzzle...");
        int underscoreCount = 0;
        boolean puzzleIsBad = true;
        Scanner scanner = new Scanner(System.in);
        while (puzzleIsBad) {
            for (char c : userPuzzle.toCharArray() )
                if (c == '_') ++underscoreCount;  // count number of underscores in userPuzzle
            if (underscoreCount != 6) {
                System.out.println("Error: Invalid puzzle. Puzzle requires six missing letters (in the form of " +
                        " underscores). Enter another: \n");
                userPuzzle = scanner.nextLine();
                underscoreCount = 0; //reset count for next iteration
            } else
                puzzleIsBad = false;
        }
    }

    /*
    * Checks to make sure spacingIndicator is in format <int,int,...> and that the sum of the lengths
    *   of each word is equal to the length of the puzzle.
    * If spacingIndicator does not meet criteria, outputs error message and
    *   keeps accepting user input until it meets the criteria.
    *   Pre-condition: userPuzzle is without spaces.
    *   Post-Condition: spacingIndicator is in a valid form.
    */
    static void checkSpacingIndicator(String spacingIndicator, String userPuzzle) {
        System.out.println("Checking word-lengths...");
        Scanner scanner = new Scanner(System.in);
        boolean spacingIndicatorIsBad = true;
        while (spacingIndicatorIsBad) {
            if ( !checkSpacingIndicatorInternal(spacingIndicator, userPuzzle) ) {
                System.out.println("Error: Invalid word lengths. Word lengths must indicated using the form: \n" +
                        "     int,int,...,int \n" + "The sum of these lengths must be equal to the length " +
                        "of the puzzle. Enter another string of word lengths: ");
                spacingIndicator = scanner.nextLine();
            } else
                spacingIndicatorIsBad = false;
        }
    }

    /* Internal method to calculate whether spacingIndicator is valid.
     * Returns false if invalid.
     */
    static boolean checkSpacingIndicatorInternal(String spacingIndicator, String userPuzzle) {
        int beginIndex = 0; //index of first digit of a number in spacingIndicator
        int endIndex; //index of char after last digit of same number in spacingIndicator
        int sumOfWordLengths = 0;
        int lengthWordInt;
        String lengthWordStr;
        int lengthSpacingIndicator = spacingIndicator.length();
        for (int i = 0; i <= lengthSpacingIndicator; ++i) {
            if ( (i == lengthSpacingIndicator) || (spacingIndicator.charAt(i) == ',') ) {
                endIndex = i; //endIndex directly follows last digit in number
                lengthWordStr = spacingIndicator.substring(beginIndex, endIndex);
                try { //Make sure each char in spacingIndicator is int or a comma
                    lengthWordInt = Integer.parseInt(lengthWordStr);
                } catch (NumberFormatException notAnInt) {
                    return false;
                }
                sumOfWordLengths += lengthWordInt;
                beginIndex = endIndex + 1; //reset for next iteration
                if (sumOfWordLengths > userPuzzle.length()) {
                    return false;
                }
            }
        }
        if (sumOfWordLengths < userPuzzle.length()) {
            return false;
        } else
            return true;
    }

    /*
     * Pre-condition: userPuzzle is one word. spacingIndicator is in the format:
     *                 "<size of first word>,<size of second word,...>".
     * Post-condition: returns a string equivalent to userPuzzle argument except 
     *                 with space(s) inserted at index(es) specified by spacingIndicator. 
    */
    static String formatPuzzleWithSpace(String userPuzzle, String spacingIndicator) {
        int beginIndex = 0; //index of first digit of a number in spacingIndicator
        int endIndex; //index of char after last digit of same number in spacingIndicator
        StringBuilder formattedUserPuzzle = new StringBuilder(userPuzzle);
        int spaceIndex = 0; //index of next space to be inserted
        for (int i = 0; i < spacingIndicator.length(); ++i ) {
            if (spacingIndicator.charAt(i) == ',') {
                endIndex = i; //endIndex directly follows last digit in number
                String lengthWordStr = spacingIndicator.substring(beginIndex, endIndex);
                int lengthWordInt = Integer.parseInt(lengthWordStr);

                spaceIndex = spaceIndex + lengthWordInt;
                formattedUserPuzzle.insert(spaceIndex++, " "); //insert space
                beginIndex = endIndex +1; //reset for next iteration
            }
        }
        System.out.println("With spaces inserted: " + formattedUserPuzzle);
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
