import java.io.*;

public class ContestMainPattern {

    public static void main(String[] args) {
        MyScanner myScanner = new MyScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        solve(myScanner, out);

        out.close();
    }

    private static void solve(MyScanner in, PrintWriter out) {
        int x = in.nextInt();
        out.println();
    }

    public static class MyScanner {
        private int cnt = 0;
        private String[] line = null;
        private final BufferedReader reader;

        public MyScanner(InputStream inputStream) {
            this.reader = new BufferedReader(new InputStreamReader(inputStream));
        }

        public String nextString() {
            if (line == null || cnt >= line.length) {
                try {
                    line = reader.readLine().split("\\s");
                    cnt = 0;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return line[cnt++];
        }

        public int nextInt() {
            return Integer.parseInt(nextString());
        }

        public long nextLong() {
            return Long.parseLong(nextString());
        }
    }

}