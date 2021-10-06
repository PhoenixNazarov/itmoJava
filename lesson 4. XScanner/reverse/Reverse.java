import java.io.IOException;
import java.util.Arrays;


public class Reverse {
    public static void main(String[] args) throws IOException {
        Integer[][] ints = new Integer[0][0];

        XScanner scanner = new XScanner(System.in);

//        scanner.addAllowType(Character.DECIMAL_DIGIT_NUMBER);
//        scanner.addAllowChar('-');

        int clen = 0;

        while (true){
            Integer[] line = scanner.nextIntegerLine();
            if (line == null) {
                break;
            }

            // copy int[+1][]
            if (ints.length <= clen) {
                ints = Arrays.copyOf(ints, ints.length * 2 + 1);
            }

            ints[clen] = line;
            clen ++;
        }

        // print int[][]
        for (int i = clen - 1; i >= 0; i--){
            if (ints[i].length != 0){
                for (int ii = ints[i].length - 1; ii >= 0; ii--){
                    if (ints[i][ii] != null){
                        System.out.print(ints[i][ii] + " ");
                    }
                }
            }
            System.out.println("");
        }
    }
}