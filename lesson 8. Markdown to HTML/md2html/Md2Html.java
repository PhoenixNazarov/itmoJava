package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Stack;

public class Md2Html {
    public static Character SYMBOL_HEADING = '#';
    public static Character SYMBOL_EMPHASIS = '_';
    public static Character SYMBOL_EMPHASIS2 = '*';
    public static Character SYMBOL_CODE = '`';
    public static String SYMBOL_CROSSING = "--";
    public static Character SYMBOL_OUT = '\\';
    public static Character WHITE_SPACE = '\n';

    public static String parsePairMarkers(String markdown) {
        // find and replace pair markup markers
        Stack<String> stack = new Stack<>();
        StringBuilder out = new StringBuilder();
        stack.push(".");
        markdown = markdown + " ";

        int i = 0;
        boolean needCheckOne = true;
        while (i < markdown.length()) {
            String symbol = "";
            String htmlOpen = "";
            String htmlClose = "";
            boolean findTag = false;
            boolean prevSymbolMark = markdown.charAt(i) == SYMBOL_OUT;

            Character curChar = markdown.charAt(i);

            if (curChar == SYMBOL_EMPHASIS || curChar == SYMBOL_EMPHASIS2) {
                symbol = String.valueOf(curChar);
                htmlOpen = "<em>";
                htmlClose = "</em>";

                if (curChar == markdown.charAt(i + 1)) {
                    i++;
                    symbol = String.valueOf(markdown.charAt(i)).repeat(2);
                    htmlOpen = "<strong>";
                    htmlClose = "</strong>";
                    findTag = true;
                }

                // check on not one, if this tag with one element
                if (!findTag) {
                    if (needCheckOne) {
                        for (int ii = i + 1; ii < markdown.length(); ii++) {
                            if (markdown.charAt(ii) == curChar && markdown.charAt(ii - 1) != SYMBOL_OUT) {
                                findTag = true;
                                needCheckOne = false;
                                break;
                            }
                        }
                    } else {
                        findTag = true;
                        needCheckOne = true;
                    }
                }
            }
            if (curChar == SYMBOL_CODE) {
                symbol = String.valueOf(curChar);
                htmlOpen = "<code>";
                htmlClose = "</code>";
                findTag = true;
            }
            if (i + 1 < markdown.length()) {
                if (("" + curChar + markdown.charAt(i + 1)).equals(SYMBOL_CROSSING)) {
                    i++;
                    symbol = String.valueOf(curChar).repeat(2);
                    htmlOpen = "<s>";
                    htmlClose = "</s>";
                    findTag = true;
                }
            }

            // choose open or close
            if (findTag && !prevSymbolMark) {
                if (stack.peek().equals(symbol)) {
                    stack.pop();
                    out.append(htmlClose);
                } else {
                    stack.push(symbol);
                    out.append(htmlOpen);
                }
            } else {
                String addChar = String.valueOf(curChar);
                if (addChar.equals("<")) {
                    addChar = "&lt;";
                }
                if (addChar.equals(">")) {
                    addChar = "&gt;";
                }
                if (addChar.equals("&")) {
                    addChar = "&amp;";
                }
                if (addChar.equals("\\")) {
                    addChar = "";
                }
                out.append(addChar);
            }
            i++;
        }
        String outString = out.toString();
        return outString.substring(0, outString.length() - 1);
    }

    public static String tryParseHeading(String element) {
        // check element is heading
        boolean isHead = false;
        int i = 0;
        if (element.charAt(0) == SYMBOL_HEADING) {
            while (element.charAt(++i) == SYMBOL_HEADING) {
            }
            if (element.charAt(i) == ' ') {
                isHead = true;
            }
        }

        if (isHead) {
            return String.format("<h%d>%s</h%d>", i, parsePairMarkers(element.substring(i + 1)), i);
        }
        return "<p>" + parsePairMarkers(element) + "</p>";
    }

    public static String parseMarkdownToHtml(BufferedReader in) throws IOException {
        StringBuilder html = new StringBuilder();
        boolean read = true;
        while (read) {
            // read one element
            StringBuilder oneElement = new StringBuilder();
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    read = false;
                    break;
                }
                if (line.isEmpty()) {
                    break;
                }
                oneElement.append(line).append(WHITE_SPACE);
            }

            // parse this element
            if (!oneElement.isEmpty()) {
                // remove last WhiteSpace
                String element = oneElement.toString();
                element = element.substring(0, element.length() - 1);
                html.append(tryParseHeading(element));
                html.append(WHITE_SPACE);
            }
        }
        return html.toString();
    }

    public static void main(String[] args) throws IOException {
        String input = args[0];
        String output = args[1];

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(input), StandardCharsets.UTF_8));
        String html = parseMarkdownToHtml(in);
        in.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter(output));
        bw.write(html);
        bw.close();
    }
}
