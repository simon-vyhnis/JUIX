package components;

import org.jdom2.Attribute;
import org.jdom2.Element;

import java.awt.*;
import java.util.List;

public abstract class View{
    private String id;

    private String rawWidth;
    private String rawHeight;
    private String rawX;
    private String rawY;
    private int width;
    private int height;
    private int x;
    private int y;

    private Layout layout;

    public static final int DIMENSION_PIXELS = 0;
    public static final int DIMENSION_DISPLAY_POINTS = 1;
    public static final int DIMENSION_PARENT = 2;
    public static final int DIMENSION_CONTENT = 3;

    public View(Element xml, Layout layout){
       rawHeight = xml.getAttributeValue("height");
       rawWidth = xml.getAttributeValue("width");
       rawX = xml.getAttributeValue("x");
       rawY = xml.getAttributeValue("y");
       this.layout = layout;
    }

    /*
     * This method is called every time, when the canvas is redrawn
     */
    public abstract void draw(Graphics g);
    /*
     * This method is called every tick
     */
    public void update(){

    }

    public String getRawWidth(){
        return rawWidth;
    }
    public String getRawHeight(){
        return rawHeight;
    }
    public String getRawX(){
        return rawX;
    }

    public String getRawY() {
        return rawY;
    }
    public abstract int getContentWidth();
    public abstract int getContentHeight();

    public void setWidth(String rawWidth) {
        this.rawWidth = rawWidth;
        layout.notifyViewsChanged();
    }

    public void setHeight(String rawHeight) {
        this.rawHeight = rawHeight;
        layout.notifyViewsChanged();
    }

    public void setX(String rawX) {
        this.rawX = rawX;
        layout.notifyViewsChanged();
    }

    public void setY(String rawY) {
        this.rawY = rawY;
        layout.notifyViewsChanged();
    }
}
