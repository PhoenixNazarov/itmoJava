package markup;

import java.util.List;

public abstract class AbstractParagraph {
    protected List<AbstractParagraph> elements;
    protected String markupChr;
    protected String htmlOpenChr;
    protected String htmlCloseChr;

    protected AbstractParagraph(List<AbstractParagraph> elm,
                                String mChr,
                                String htmlOChr,
                                String htmlCChr){
        elements = elm;
        markupChr = mChr;
        htmlOpenChr = htmlOChr;
        htmlCloseChr = htmlCChr;
    }

    public void toMarkdown(StringBuilder text){
        if (!markupChr.isEmpty()) {
            text.append(markupChr);
        }
        for (AbstractParagraph element:elements) {
            element.toMarkdown(text);
        }
        if (!markupChr.isEmpty()) {
            text.append(markupChr);
        }
    }

    public void toHtml(StringBuilder text){
        if (!htmlOpenChr.isEmpty()) {
            text.append(htmlOpenChr);
        }
        for (AbstractParagraph element:elements) {
            element.toHtml(text);
        }
        if (!htmlCloseChr.isEmpty()) {
            text.append(htmlCloseChr);
        }
    }
}
