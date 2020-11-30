package views;

import components.Bounds;
import components.View;
import org.jdom2.Attribute;

import java.awt.*;
import java.util.List;

public class TextView extends View {
    private String text;
    public TextView(Bounds bounds, List<Attribute> params) {
        super(bounds, params);
    }

    @Override
    public void draw(Graphics g) {
        if (text!=null)
            g.drawString(text,getBounds().getX(),getBounds().getY());
    }

    @Override
    public void update() {
        super.update();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
