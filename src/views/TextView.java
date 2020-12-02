package views;

import components.Bounds;
import components.View;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.awt.*;
import java.util.List;

public class TextView extends View {
    private String text = "TextView";
    private int x=10;
    private int y=10;
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
