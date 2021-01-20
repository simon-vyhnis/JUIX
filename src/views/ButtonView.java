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

public class ButtonView extends View {
    private Color color;
    private boolean roundedCorners;

    private String text;
    private String font;
    private Color textColor;
    private int textSize;

    private int textWidth = 0, textHeight = 0;


    public ButtonView(Element xml, Layout layout) {
        super(xml, layout);
        AttributesParser attributes = new AttributesParser(xml, layout.getApplication());
        try {
            color = attributes.getColor("color", Color.orange);
            roundedCorners = attributes.getBooleanValue("roundedCorners",true);
            textColor = attributes.getColor("textColor",Color.black);
            font = attributes.getStringValue("font","Arial");
            textSize = attributes.getIntValue("textSize",12);
            text = attributes.getStringValue("text", "BUTTON");
        } catch (InvalidAttributeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        if(textHeight != g.getFontMetrics().getHeight() || textWidth != g.getFontMetrics().stringWidth(text)){
            textHeight = g.getFontMetrics().getHeight();
            textWidth = g.getFontMetrics().stringWidth(text);
            notifyIfNeeded();
        }

        g.setColor(color);
        g.fillRect(getAbsoluteX(),getAbsoluteY(),getAbsoluteWidth(),getAbsoluteHeight());
        if(roundedCorners){
            g.fillRoundRect(getAbsoluteX(),getAbsoluteY(),getAbsoluteWidth(),getAbsoluteHeight(),10,10);
        }else {
            g.fillRect(getAbsoluteX(),getAbsoluteY(),getContentWidth(),getAbsoluteHeight());
        }
        g.setFont(new Font(font, Font.PLAIN, textSize));
        while(g.getFontMetrics().getHeight() > getAbsoluteHeight()-4){
            textSize--;
            g.setFont(new Font(font, Font.PLAIN, textSize));
        }

        g.setColor(textColor);
        g.drawString(text,getAbsoluteX()+10,getAbsoluteY()+((getAbsoluteHeight()-g.getFontMetrics().getHeight())/2)+g.getFontMetrics().getAscent());

    }

    @Override
    public int getContentWidth() {
        return textWidth+20;
    }

    @Override
    public int getContentHeight() {
        return textHeight+4;
    }
}
