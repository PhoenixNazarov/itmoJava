package markup;

import java.util.List;

public class Strikeout extends AbstractParagraph{
    public Strikeout(List<AbstractParagraph> elm) {
        super(elm);
    }

    @Override
    public void toMarkdown(StringBuilder text){
        text.append('~');
        super.toMarkdown(text);
        text.append('~');
    }
}
