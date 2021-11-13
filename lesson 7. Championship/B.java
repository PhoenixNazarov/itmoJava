import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class B {
    public static void main(String[] args) {
        solve(new Reader(System.in));
    }

    private static void solve(Reader in) {
        int n = in.nextInt();
        for (int i = -25000; i < n - 25000; i++) {
            System.out.println(710 * i);
        }
    }

    public static class Reader {
        private int cnt = 0;
        private String[] line = null;
        private final BufferedReader reader;

        public Reader(InputStream inputStream) {
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
