import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class WsppSortedPosition {
    public static String listToStr(List<Integer[]> words){
        StringBuilder str = new StringBuilder();
        str.append(words.get(0)[0]).append(":").append(words.get(0)[1]);
        for (int i = 1; i < words.size(); i++){
            str.append(' ');
            str.append(words.get(i)[0]).append(":").append(words.get(i)[1]);
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Map<String, List<Integer[]>> words = new TreeMap<>();
        int ind = 1;
        int stringInd = 1;
        try {
            XScanner scanner = new XScanner(new FileInputStream(args[0]));

            // read
            while (true){
                String word = scanner.nextWord();
                System.out.println(scanner.indexString);
                if (scanner.indexString != stringInd){
                    stringInd = scanner.indexString;
                    ind = 1;
                }
                if (word.isEmpty()){
                    break;
                }
                word = word.toLowerCase();

                boolean wordIndex = words.containsKey(word);
                if (wordIndex) {
                    words.get(word).add(new Integer[]{stringInd, ind});
                } else {
                    words.put(word, new ArrayList<>());
                    words.get(word).add(new Integer[]{stringInd, ind});
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

