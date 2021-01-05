package components;

import core.JUIXApplication;
import core.LayoutParser;
import exceptions.InvalidViewReferenceException;
import exceptions.MissingAttributeException;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Layout extends View {
    protected Map<String, View> views;
    protected JUIXApplication application;

    public Layout(Element xml, Layout layout, LayoutParser parser, JUIXApplication application) {
        super(xml, layout);
        this.application = application;
        try {
            views = parser.parseLayout(xml, this);
        } catch (MissingAttributeException | InvalidViewReferenceException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void draw(Graphics g) {
        views.forEach((k, v) -> v.draw(g));
    }

    public Map<String, View> getViews() {
        return views;
    }

    public View getView(String id) {
        return views.get(id);
    }

    public abstract void notifyViewsChanged();

    @Override
    public void setX(String rawX) {
        super.setX(rawX);
        notifyViewsChanged();
    }

    @Override
    public void setY(String rawY) {
        super.setY(rawY);
        notifyViewsChanged();
    }
}
