import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Dictionary {

    HashSet<String> words;

    public Dictionary() {
        words = new HashSet<String>();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("words.txt").getFile());
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean lookup(String word) {
        return words.contains(word);
    }
}
