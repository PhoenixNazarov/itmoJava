import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class WordStatInput {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> sortWords = new ArrayList<String>();
        ArrayList<Integer> count = new ArrayList<Integer>();

        try {
            XScanner scanner = new XScanner(new FileInputStream(args[0]));

            // read
            while (true){
                String word = scanner.nextWord();
                if (word.equals("")){
                    break;
                }
                word = word.toLowerCase();

                int wordIndex = words.indexOf(word);
                if (wordIndex == -1) {
                    words.add(word);
                    count.add(1);
                } else {
                    count.set(wordIndex, count.get(wordIndex) + 1);
                }
            }

            // write
            try (PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                        StandardCharsets.UTF_8
                    )
                )
            ) {
                for (int i = 0; i < words.size(); i++) {
                    writer.println(words.get(i) + " " + count.get(i));
                }
            } catch (FileNotFoundException e) {
                System.out.println("File output not found");
            }


        } catch (FileNotFoundException e) {
            System.out.println("File input not found");
        } catch (IOException e) {
            System.out.println("Some error with input file:");
        }
    }
}

