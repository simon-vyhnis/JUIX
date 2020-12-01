package core;

import components.Bounds;
import components.View;
import exceptions.InvalidViewReferenceException;
import exceptions.MissingAttributeException;
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
import java.util.*;

public class LayoutParser {
    public Map<String,View> parse(File file) throws MissingAttributeException {
        SAXBuilder builder = new SAXBuilder();
        Document xmlFile;
        ViewMap<String, View> result = new ViewMap<>();
        try {
            xmlFile = builder.build(file);
            Element root = xmlFile.getRootElement();
            for (Element child:root.getChildren()) {
                result.put(parseView(child,file));
            }
            System.out.println("PARSING_LAYOUT: "+root.getName());
        } catch (JDOMException | IOException | InvalidViewReferenceException e) {
                e.printStackTrace();
        }
        return  result;
    }

    private void checkAttribute(String attributeName, Element xml, File file) throws MissingAttributeException {
        if(xml.getAttribute(attributeName)!=null){
            throw new MissingAttributeException(file.getAbsolutePath(), xml.getName(), attributeName);
        }
    }
    private Map.Entry<String, View> parseView(Element child, File file) throws MissingAttributeException, InvalidViewReferenceException {
        Map.Entry<String, View> result;
        checkAttribute("id",child,file);
        checkAttribute("height",child,file);
        checkAttribute("width",child,file);
        switch (child.getName()){
            case "Text":
                result = new AbstractMap.SimpleEntry<>(child.getAttributeValue("id"),new TextView(child));
                break;
            case "Button":
                result = new AbstractMap.SimpleEntry<>(child.getAttributeValue("id"), new ButtonView(child));
                break;
            default:
                try {
                    Class<?> clazz = Class.forName(child.getName());
                    Constructor<?> constructor = clazz.getConstructor(List.class);
                    View view = (View)constructor.newInstance(child.getAttributes());
                    result = new AbstractMap.SimpleEntry<>(child.getAttributeValue("id"),view);

                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new InvalidViewReferenceException(file.getAbsolutePath(),child.getName());
                }
                break;
        }
        return result;
    }
    /**
     *  Customized HashMap for storing views
     *
     */
    public static class ViewMap<K,V> extends HashMap<K,V> implements KeyInsertableMap<K,V> {}
    public interface KeyInsertableMap<K,V> extends Map<K,V> {
            /**
             * Puts a whole entry containing a key-value pair to the map.
             */
            default V put(Entry<K, V> entry) {
                return put(entry.getKey(), entry.getValue());
            }
        }

}
