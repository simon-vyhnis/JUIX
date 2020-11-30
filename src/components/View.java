package components;

import org.jdom2.Attribute;
import org.jdom2.Element;

import java.awt.*;
import java.util.List;

public abstract class View{
    public View(Element xml){}
    /*
     * This method is called every time, when the canvas is redrawn
     */
    public abstract void draw(Graphics g);
    /*
     * This method is called every tick
     */
    public void update(){

    }


}
