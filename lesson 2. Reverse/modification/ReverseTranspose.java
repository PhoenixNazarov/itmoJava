import java.util.Scanner;

public class ReverseTranspose {
    public static void main(String[] args){
        int[][] ints;
        ints = new int[0][];

        int mxLen = 0;


        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            int[] intsString;
            intsString = new int[0];

            String line = scanner.nextLine();
            Scanner currentScanner = new Scanner(line);

            while (currentScanner.hasNextInt()){
                int[] newIntsString;
                newIntsString = new int[intsString.length + 1];
                System.arraycopy(intsString, 0, newIntsString, 0, intsString.length);

                int value = currentScanner.nextInt();
                newIntsString[newIntsString.length - 1] = value;

                intsString = newIntsString;
            }

            if (mxLen < intsString.length){
                mxLen = intsString.length;
            }

            // copy int[][]
            int[][] newInts;
            newInts = new int[ints.length + 1][];
            for (int i = 0; i < ints.length; i++){
                newInts[i] = ints[i];
            }
            newInts[newInts.length - 1] = intsString;
            ints = newInts;
        }

        // print int[][]
        for (int i = 0; i < mxLen; i++){
            for (int ii = 0; ii < ints.length; ii++){
                if (ints[ii].length > i){
                    System.out.print(ints[ii][i]);
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}