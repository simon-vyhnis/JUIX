package views;

import components.Bounds;
import components.Layout;
import components.View;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.awt.*;
import java.util.List;

public class TextView extends View {
    private String text = "Test text";
    private int textSize = 12;
    private String font = "Arial";
    private Color textColor = Color.BLACK;


    public TextView(Element xml, Layout layout) {
        super(xml, layout);

    }

    @Override
    public void draw(Graphics g) {
        if (text!=null) {
            g.setColor(textColor);
            g.setFont(new Font(font,Font.PLAIN,textSize));
            g.drawString(text, getAbsoluteX(), getAbsoluteY());
        }
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

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
