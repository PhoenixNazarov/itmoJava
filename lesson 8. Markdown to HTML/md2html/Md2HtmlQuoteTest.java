package md2html;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Md2HtmlQuoteTest extends Md2HtmlTest {
    static {
        addElement("q", "''");
    }

    @Override
    protected void test() {
        test("''цитата''", "<p><q>цитата</q></p>");
        test("Это ''цитата'', вложенная в текст", "<p>Это <q>цитата</q>, вложенная в текст</p>");
        test("Это не \\''цитата'\\'", "<p>Это не ''цитата''</p>");
        super.test();

        randomTest(100, 1000, "_", "**", "`", "--", "''");
        randomTest(100, 1000, "*", "__", "`", "--", "''");
        randomTest(100, 100_000, "*", "__", "`", "--", "''");
    }

    public static void main(final String... args) {
        new Md2HtmlQuoteTest().run();
    }
}