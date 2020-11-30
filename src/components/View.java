package components;

import org.jdom2.Attribute;

import java.awt.*;
import java.util.List;

public abstract class View {
    private Bounds bounds;
    public View(Bounds bounds, List<Attribute> params){
        this.bounds = bounds;
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

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }
}
