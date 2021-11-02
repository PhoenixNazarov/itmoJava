package markup;

import java.util.List;

public abstract class AbstractParagraph {
    protected List<AbstractParagraph> elements;

    protected AbstractParagraph(List<AbstractParagraph> elm){
        elements = elm;
    }

    public void toMarkdown(StringBuilder text){
        for (AbstractParagraph element:elements) {
            element.toMarkdown(text);
        }
    }
}
