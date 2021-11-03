package markup;

import java.util.List;

public class Emphasis extends AbstractParagraph{
    public Emphasis(List<AbstractParagraph> elm) {
        super(elm, "*", "<em>", "</em>");
    }
}
