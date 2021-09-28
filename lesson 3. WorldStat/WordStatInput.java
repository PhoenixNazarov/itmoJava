import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class WordStatInput {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<Integer> count = new ArrayList<Integer>();

        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(args[0]),
                "utf8"
                )
            )
        ) {
            // read
            String text = "", line;
            String textWithAllowSymb = "";
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                text = text + " " + line;
            }

            // delete disallow symbols
            text = text.toLowerCase();
            for(char c: text.toCharArray()){
                int charType = Character.getType(c);
                if (charType == Character.DASH_PUNCTUATION ||
                        charType == Character.LOWERCASE_LETTER ||
                        c == '\''){
                    textWithAllowSymb = textWithAllowSymb + c;
                }
                else{
                    textWithAllowSymb = textWithAllowSymb + " ";
                }
            }

            // count
            Scanner scanner = new Scanner(textWithAllowSymb);
            while (scanner.hasNext()){
                String word = scanner.next();

                int wordIndex = words.indexOf(word);
                if (wordIndex == -1){
                    words.add(word);
                    count.add(1);
                }
                else {
                    count.set(wordIndex, count.get(wordIndex) + 1);
                }
            }

            // write
            try (PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    "utf8"
                    )
                )
            ) {
                for (int i = 0; i < words.size(); i++) {
                    writer.println(words.get(i) + " " + count.get(i));
                }
            } catch (FileNotFoundException e) {
                System.out.println("File output not found");
            } catch (IOException e) {
                System.out.println("Some error with output file");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File input not found");
        } catch (IOException e) {
            System.out.println("Some error with input file");
        }
    }
}

