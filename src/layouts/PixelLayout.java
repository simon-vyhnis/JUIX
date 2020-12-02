package layouts;

import components.Bounds;
import components.Layout;
import core.LayoutParser;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.util.List;

public class PixelLayout extends Layout {
    public PixelLayout(Element xml, LayoutParser parser) {
        super(xml, parser);
    }
}
