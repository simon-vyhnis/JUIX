package components;

import core.LayoutParser;
import exceptions.InvalidViewReferenceException;
import exceptions.MissingAttributeException;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Layout extends View{
    Map<String,View> views;

    public Layout(Element xml, LayoutParser parser) {
        super(xml);
        try {
            views = parser.parseLayout(xml);
        } catch (MissingAttributeException | InvalidViewReferenceException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void draw(Graphics g) {
        views.forEach((k,v)->v.draw(g));
    }
}
