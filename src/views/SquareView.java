package views;

import components.Layout;
import components.View;
import org.jdom2.Element;

import java.awt.*;

public class SquareView extends View {
    public SquareView(Element xml, Layout layout) {
        super(xml, layout);
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(getAbsoluteX(),getAbsoluteY(),getAbsoluteWidth(),getAbsoluteHeight());
    }

    @Override
    public int getContentWidth() {
        return 0;
    }

    @Override
    public int getContentHeight() {
        return 0;
    }
}
