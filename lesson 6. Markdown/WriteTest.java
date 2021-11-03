import markup.Emphasis;
import markup.Paragraph;
import markup.Strikeout;
import markup.Strong;
import markup.Text;

import java.util.List;

import base.Asserts;

public class WriteTest {
    public void test1(){
        // example test
        Paragraph paragraph = new Paragraph(List.of(
                new Strong(List.of(
                        new Text("1"),
                        new Strikeout(List.of(
                                new Text("2"),
                                new Emphasis(List.of(
                                        new Text("3"),
                                        new Text("4")
                                )),
                                new Text("5")
                        )),
                        new Text("6")
                ))
        ));



    }


    public static void main(String[] args) {
//        System.out.println(List.of(new Paragraph(List.of(new Text("123")))));

        StringBuilder text = new StringBuilder();
//        paragraph.toMarkdown(text);
        System.out.println(text);
    }
}
