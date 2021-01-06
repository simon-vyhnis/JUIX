package components;

import core.JUIXApplication;
import core.LayoutParser;
import exceptions.InvalidViewReferenceException;
import org.jdom2.JDOMException;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public abstract class Part {
    private Layout layout;
    private final JUIXApplication application;

    public Part(JUIXApplication application) {
       this.application = application;
       onCreate();
    }

    protected abstract void onCreate();

    protected void setLayout(File layoutFile){
        LayoutParser parser = new LayoutParser(layoutFile, application);
        try {
            layout = parser.parseFile();
        } catch (InvalidViewReferenceException | JDOMException | IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics g){
        layout.draw(g);
    }

    public void update(int tick){
        layout.getViews().forEach((k,v)->v.update());
    }

    public void onClick(MouseEvent e){
        layout.onClick(e);
    }

    public void notifyWindowResized(){
        layout.notifyViewsChanged();
    }

}
