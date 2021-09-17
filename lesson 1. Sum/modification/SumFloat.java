public class SumFloat {
    public static void main(String[] args){
        float sum = 0;

        for (int i = 0; i < args.length; i++) {
            int lastSpace = 0;
            int intSymbol = 0;

            for (int ii = 0; ii < args[i].length(); ii++){
                char symbol = args[i].charAt(ii);

                if (Character.isWhitespace(symbol)){
                    if (intSymbol == 1){
                        String value = args[i].substring(lastSpace, ii);
                        sum = sum + Float.parseFloat(value);
                    }
                    lastSpace = ii + 1;
                    intSymbol = 0;
                }
                else{
                    intSymbol = 1;
                }
            }

            if (intSymbol == 1){
                String value = args[i].substring(lastSpace, args[i].length());
                sum = sum + Float.parseFloat(value);
            }
        }

    System.out.println(sum);
    }
}