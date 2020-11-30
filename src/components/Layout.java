package components;

import org.jdom2.Attribute;
import org.jdom2.Element;

import java.awt.*;
import java.util.List;

public abstract class Layout extends View{
    List<View> views;

    public Layout(Element xml) {
        super(xml);
    }


    @Override
    public void draw(Graphics g) {
        for (View view:views) {
            view.draw(g);
        }
    }
}
