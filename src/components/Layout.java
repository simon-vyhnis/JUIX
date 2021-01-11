package components;

import core.JUIXApplication;
import core.LayoutParser;
import exceptions.InvalidViewReferenceException;
import exceptions.MissingAttributeException;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Layout extends View {
    protected Map<String, View> views;
    protected JUIXApplication application;

    public abstract void notifyViewsChanged();

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
        views.forEach((id, view) -> view.draw(g));
    }

    @Override
    public void onClick(MouseEvent e){
        super.onClick(e);
        System.out.println("LAYOUT: "+"Layout clicked");
        views.forEach((id, view)->{
            if(view.getAbsoluteX() <= e.getX() && view.getAbsoluteX()+view.getAbsoluteWidth() >= e.getX() &&
            view.getAbsoluteY() <= e.getY() && view.getAbsoluteY()+view.getAbsoluteHeight() >= e.getY()){
                view.onClick(e);
            }
        });
    }


    public Map<String, View> getViews() {
        return views;
    }

    public View getView(String id) {
        return views.get(id);
    }



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
