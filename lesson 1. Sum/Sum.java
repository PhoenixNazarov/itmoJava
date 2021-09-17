public class Sum {
    public static void main(String[] args){
        int sum = 0;

        for (int i = 0; i < args.length; i++) {
            int lastSpace = 0;
            int intSumb = 0;

            for (int ii = 0; ii < args[i].length(); ii++){
                char symb = args[i].charAt(ii);

                if (Character.isWhitespace(symb) == true){
                    if (intSumb == 1){
                        String value = args[i].substring(lastSpace, ii);
                        sum = sum + Integer.parseInt(value);
                    }
                    lastSpace = ii + 1;
                    intSumb = 0;
                }
                else{
                    intSumb = 1;
                }
            }

            if (intSumb == 1){
                String value = args[i].substring(lastSpace, args[i].length());
                sum = sum + Integer.parseInt(value);
            }
        }

    System.out.println(sum);
    }
}