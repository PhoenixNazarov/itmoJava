package markup;

import java.util.List;

public class Strikeout extends AbstractParagraph{
    public Strikeout(List<AbstractParagraph> elm) {
        super(elm, "~", "<s>", "</s>");
    }
}
