package views;

import components.Bounds;
import components.View;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.awt.*;
import java.util.List;

public class TextView extends View {
    private String text;
    private int x;
    private int y;
    private int width;
    private int height;

    public TextView(Element xml) {
        super(xml);
    }

    @Override
    public void draw(Graphics g) {
        if (text!=null)
            g.drawString(text,x,y);
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
