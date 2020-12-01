package components;

import org.jdom2.Attribute;
import org.jdom2.Element;

import java.awt.*;
import java.util.List;

public abstract class View{
    private String id;

    private int widthType;
    private int heightType;
    private int width;
    private int height;
    private int x;
    private int y;

    public static final int DIMENSION_PIXELS = 0;
    public static final int DIMENSION_DISPLAY_POINTS = 1;
    public static final int DIMENSION_PARENT = 2;
    public static final int DIMENSION_CONTENT = 3;

    public View(Element xml){
       xml.getAttribute("height");
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

    private void parseDimension(){

    }


}
