package markup;

import java.util.List;

public class Text extends AbstractParagraph{
    private final String selfText;

    public Text(String text){
        super(List.of(), "", "", "");
        selfText = text;
    }

    @Override
    public void toMarkdown(StringBuilder text){
        text.append(selfText);
    }

    @Override
    public void toHtml(StringBuilder text){
        text.append(selfText);
    }
}
