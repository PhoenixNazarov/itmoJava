import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class Wspp {
    public static String listToStr(ArrayList<Integer> wrds){
        StringBuilder str = new StringBuilder();
        str.append(wrds.get(0));
        for (int i = 1; i < wrds.size(); i++){
            str.append(' ');
            str.append(wrds.get(i));
        }
        return str.toString();
    }

    public static void main(String[] args) {
        LinkedHashMap<String, ArrayList<Integer>> words = new LinkedHashMap<>();
        int ind = 1;

        try {
            XScanner scanner = new XScanner(new FileInputStream(args[0]));

            // read
            while (true){
                String word = scanner.nextWord();
                if (word.equals("")){
                    break;
                }
                word = word.toLowerCase();

                boolean wordIndex = words.containsKey(word);
                if (wordIndex) {
                    words.get(word).add(ind);
                } else {
                    words.put(word, new ArrayList<Integer>());
                    words.get(word).add(ind);
                }
                ind++;
            }

            // write
            try (PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                        StandardCharsets.UTF_8
                    )
                )
            ) {
                for ( String key : words.keySet() ) {
                    writer.println(key + " " + words.get(key).size() + " " + listToStr(words.get(key)));
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

