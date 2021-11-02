package markup;

import java.util.List;

public class Strong extends AbstractParagraph{
    public Strong(List<AbstractParagraph> elm) {
        super(elm);
    }

    @Override
    public void toMarkdown(StringBuilder text){
        text.append("__");
        super.toMarkdown(text);
        text.append("__");
    }
}
