package views;

import components.Bounds;
import components.Layout;
import components.View;
import core.AttributesParser;
import exceptions.InvalidAttributeException;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.awt.*;
import java.util.List;

public class TextView extends View {
    private String text;
    private int textSize;
    private String font;
    private Color textColor;
    private int textWidth = 0;
    private int textHeight = 0;


    public TextView(Element xml, Layout layout) {
        super(xml, layout);
        AttributesParser attributes = new AttributesParser(xml, layout.getApplication());
        try {
            text = attributes.getStringValue("text", "");
            textSize = attributes.getIntValue("textSize",12);
            font = attributes.getStringValue("font", "Arial");
            textColor = attributes.getColor("textColor", Color.black);
        } catch (InvalidAttributeException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw(Graphics g) {
        if (text!=null) {
            g.setColor(textColor);
            g.setFont(new Font(font,Font.PLAIN,textSize));
            g.drawString(text, getAbsoluteX(), getAbsoluteY());
            if(textWidth != g.getFontMetrics().stringWidth(text))
                textWidth = g.getFontMetrics().stringWidth(text);
                notifyIfNeeded();
        }
        if(textHeight != g.getFontMetrics().getHeight())
            textHeight = g.getFontMetrics().getHeight();
            System.out.println(textHeight);
            notifyIfNeeded();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public int getContentWidth() {
        return textWidth;
    }

    @Override
    public int getContentHeight() {
        return textHeight;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyIfNeeded();
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        notifyIfNeeded();
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
       notifyIfNeeded();
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    private void notifyIfNeeded(){
        if(getRawHeight().equals("content") || getRawWidth().equals("content")){
            layout.notifyViewsChanged();
        }
    }
}
