import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class I {
    public static void main(String[] args) {
        solve(new Reader(System.in));
    }

    private static void solve(Reader in) {
        int n = in.nextInt();

        int xl = 0, xr = 0, yl = 0, yr = 0;
        boolean xlp = true, xrp = true, ylp = true, yrp = true;

        for (int i = 0; i < n; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int h = in.nextInt();

            if (x - h < xl || xlp){
                xl = x - h;
                xlp = false;
            }
            if (x + h > xr || xrp){
                xr = x + h;
                xrp = false;
            }

            if (y - h < yl || ylp){
                yl = y - h;
                ylp = false;
            }
            if (y + h > yr || yrp){
                yr = y + h;
                yrp = false;
            }
        }
//        System.out.println(xl + " " + xr + " " + yl + " " + yr);
        int u = Math.max(xr - xl, yr - yl);
        int h = (int) Math.ceil( (double) u / 2);
        int x = (xl + xr) / 2;
        int y = (yl + yr) / 2;

        System.out.println(x + " " + y + " " + h);
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
