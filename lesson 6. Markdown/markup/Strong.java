package markup;

import java.util.List;

public class Strong extends AbstractParagraph{
    public Strong(List<AbstractParagraph> elm) {
        super(elm, "__", "<strong>", "</strong>");
    }
}
