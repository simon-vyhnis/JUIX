package views;

import components.Bounds;
import components.Layout;
import components.View;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.awt.*;
import java.util.List;

public class ButtonView extends View {


    public ButtonView(Element xml, Layout layout) {
        super(xml, layout);
    }

    @Override
    public void draw(Graphics g) {

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
