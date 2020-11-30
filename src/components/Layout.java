package components;

import org.jdom2.Attribute;

import java.awt.*;
import java.util.List;

public abstract class Layout extends View{
    List<View> views;
    public Layout(Bounds bounds, List<Attribute> params) {
        super(bounds, params);
    }

    @Override
    public void draw(Graphics g) {
        for (View view:views) {
            view.draw(g);
        }
    }
}
