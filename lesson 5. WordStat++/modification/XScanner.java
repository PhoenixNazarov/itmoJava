import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class XScanner {
    private final static int BLOCK_SIZE = 1024;

    private final InputStreamReader reader;

    private char[] block = new char[BLOCK_SIZE];
    private boolean blockMemory = false;
    private boolean hasNextMemory = false;
    private boolean canRead = true;

    public int indexString = 1;

    public XScanner(InputStream source) {
        reader = new InputStreamReader(source);
    }

    public XScanner(FileInputStream source) {
        reader = new InputStreamReader(source, StandardCharsets.UTF_8);
    }

    private boolean checkAllowChar(char c, String type){
        if (type.equals("newLine")) {
            return c == '\n';
        }
        if (type.equals("whiteSpace")) {
            return Character.isWhitespace(c);
        }
        if (type.equals("word")) {
            int chType = Character.getType(c);
            return chType == Character.DASH_PUNCTUATION ||
                    chType == Character.LOWERCASE_LETTER ||
                    chType == Character.UPPERCASE_LETTER ||
                    c == '\'';
        }
        if (type.equals("digit")) {
            int chType = Character.getType(c);
            return chType == Character.DECIMAL_DIGIT_NUMBER ||
                    c == '-' || c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f';
        }
        return false;
    }

    private int readBlock() throws IOException {
        int read;
        if (blockMemory) {
            read = block.length;
            blockMemory = false;
        } else {
            if (canRead){
                block = new char[BLOCK_SIZE];
                read = reader.read(block);
                if (read == -1) {
                    canRead = false;
                    reader.close();
                }
            } else {
                read = -1;
            }
        }
        return read;
    }

    private void saveBlock(char[] unreadBlock) {
        block = unreadBlock;
        blockMemory = true;
    }

    public Integer[] nextIntegerLine() throws IOException {
        Integer[] line = new Integer[0];
        int clen = 0;

        StringBuilder word = new StringBuilder();
        int read;
        boolean wordFind = false;

        while (true){
            read = readBlock();
            if (read == -1){
                hasNextMemory = false;
                return null;
            }

            for (int i = 0; i < block.length; i++){
                if (checkAllowChar(block[i], "newLine")){
                    saveBlock(Arrays.copyOfRange(block, i + 1, read));
                    return line;
                }
                // find space
                if (checkAllowChar(block[i], "whiteSpace")){
                    if (wordFind){
                        if (line.length <= clen) {
                            line = Arrays.copyOf(line, line.length * 2 + 1);
                        }
                        line[clen] = new BigInteger(word.toString(), 16).intValue();

                        word = new StringBuilder();
                        wordFind = false;
                        clen ++;
                    }
                } else {
                    if (!wordFind){
                        wordFind = true;
                    }
                    if (checkAllowChar(block[i], "digit")) {
                        word.append(block[i]);
                    } else {
                        wordFind = false;
                    }
                }
            }
        }
    }

    public boolean hasNext() throws IOException {
        if (hasNextMemory) {
            return true;
        }
        if (!canRead){
            return false;
        }

        int read;
        while (true) {
            // take one block
            read = readBlock();
            if (read == -1){
                hasNextMemory = false;
                return false;
            }

            // finder not space character
            for (int i = 0; i < read; i++) {
                if (checkAllowChar(block[i], "newLine")){
                    indexString ++;
                }

                if (!checkAllowChar(block[i], "whiteSpace")) {
                    saveBlock(Arrays.copyOfRange(block, i, read));
                    hasNextMemory = true;
                    return true;
                }
            }
        }
    }

    public String nextString(String chType) throws IOException {
        int read;
        StringBuilder outputString = new StringBuilder();

        while (hasNext()) {
            // take one block
            read = readBlock();
            if (read == -1){
                return outputString.toString();
            }

            // finder word
            for (int i = 0; i < read; i++){
                if (checkAllowChar(block[i], chType)){
                    outputString.append(block[i]);
                } else {
                    saveBlock(Arrays.copyOfRange(block, i + 1, read));
                    hasNextMemory = false;
                    if (outputString.length() != 0) {
                        return outputString.toString();
                    }
                    outputString = new StringBuilder();
                    break;
                }
            }
        }
        return "";
    }

    public String nextDigit() throws IOException {
        return nextString("digit");
    }

    public String nextWord() throws IOException {
        return nextString("word");
    }

    public String readAll() throws IOException {
        StringBuilder outputString = new StringBuilder();
        while (true) {
            int read = readBlock();
            if (read == -1){
                return outputString.toString();
            }
            outputString.append(block);
        }
    }

    public void closeReadFile() throws IOException {
        reader.close();
    }
}
