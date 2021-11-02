package markup;

import java.util.List;

public class Emphasis extends AbstractParagraph{
    public Emphasis(List<AbstractParagraph> elm) { super(elm); }

    @Override
    public void toMarkdown(StringBuilder text){
        text.append('*');
        super.toMarkdown(text);
        text.append('*');
    }
}
