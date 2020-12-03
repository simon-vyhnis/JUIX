package views;

import components.Bounds;
import components.Layout;
import components.View;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.awt.*;
import java.util.List;

public class TextView extends View {
    private String text = "TextView";
    private int x=-1;
    private int y=-1;
    private int width;
    private int height;

    public TextView(Element xml, Layout layout) {
        super(xml, layout);
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

    @Override
    public int getContentWidth() {
        return 0;
    }

    @Override
    public int getContentHeight() {
        return 0;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
