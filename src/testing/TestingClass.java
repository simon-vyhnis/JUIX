package testing;

import components.Layout;
import components.Part;
import core.JUIXApplication;
import core.LayoutParser;
import exceptions.InvalidViewReferenceException;
import exceptions.MissingAttributeException;
import org.jdom2.JDOMException;

import java.io.File;
import java.io.IOException;
import layouts.PixelLayout;

public class TestingClass {

    public static void main(String[] args) {
        JUIXApplication application = new JUIXApplication("Testing App", MainPart.class);
    }
}
