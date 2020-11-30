package core;

import components.Bounds;
import components.View;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import views.ButtonView;
import views.TextView;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class LayoutParser {
    public List<View> parse(File file){
        SAXBuilder builder = new SAXBuilder();
        Document xmlFile;
        List<View> result = new ArrayList<>();
        try {
            xmlFile = builder.build(file);
            Element root = xmlFile.getRootElement();
            for (Element child:root.getChildren()) {
                switch (child.getName()){
                    case "Text":
                        result.add(new TextView(child));
                        break;
                    case "Button":
                        result.add(new ButtonView(child));
                        break;
                    default:
                        try {
                            Class<?> clazz = Class.forName(child.getName());
                            Constructor<?> constructor = clazz.getConstructor(List.class);
                            View view = (View)constructor.newInstance(child.getAttributes());
                            result.add(view);

                        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                            e.printStackTrace();
                            System.out.println("PARSING_LAYOUT: "+"Error while parsing view "+child.getName());
                        }
                }
            }
            System.out.println("PARSING_LAYOUT: "+root.getName());
        } catch (JDOMException | IOException e) {
                e.printStackTrace();
        }
        return  result;
    }
}
