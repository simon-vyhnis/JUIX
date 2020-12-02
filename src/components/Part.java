package components;

import core.LayoutParser;
import exceptions.InvalidViewReferenceException;
import org.jdom2.JDOMException;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public abstract class Part {
    private Layout layout;

    public Part(File layoutFile) {
        LayoutParser parser = new LayoutParser(layoutFile);
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

    }

}
