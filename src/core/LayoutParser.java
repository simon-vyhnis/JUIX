package core;

import components.Layout;
import components.View;
import exceptions.InvalidViewReferenceException;
import exceptions.MissingAttributeException;
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
    private final File layoutFile;
    public LayoutParser(File layoutFile){
        this.layoutFile = layoutFile;
    }
    public Layout parseFile() throws InvalidViewReferenceException, JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        Document xmlFile;
        try {
            xmlFile = builder.build(layoutFile);
            Element root = xmlFile.getRootElement();
            Class<?> clazz = Class.forName(root.getName());
            Constructor<?> constructor = clazz.getConstructor(Element.class, Layout.class, LayoutParser.class);
            return (Layout)constructor.newInstance(root, null, this);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            throw new InvalidViewReferenceException(layoutFile.getAbsolutePath(),"Root Layout");
        }
    }
    public Map<String,View> parseLayout(Element root, Layout layout) throws MissingAttributeException, InvalidViewReferenceException {
        ViewMap<String, View> result = new ViewMap<>();
        for (Element child:root.getChildren()) {
            result.put(parseView(child, layout));
        }
        System.out.println("PARSING_LAYOUT: "+root.getName());
        return  result;
    }

    private void checkAttribute(String attributeName, Element xml, File file) throws MissingAttributeException {
        if(xml.getAttribute(attributeName)==null){
            throw new MissingAttributeException(file.getAbsolutePath(), xml.getName(), attributeName);
        }
    }
    private Map.Entry<String, View> parseView(Element child, Layout layout) throws MissingAttributeException, InvalidViewReferenceException {
        Map.Entry<String, View> result;
        checkAttribute("id", child, layoutFile);
        checkAttribute("height", child, layoutFile);
        checkAttribute("width", child, layoutFile);
        switch (child.getName()){
            case "Text":
                result = new AbstractMap.SimpleEntry<>(child.getAttributeValue("id"),new TextView(child, layout));
                break;
            case "Button":
                result = new AbstractMap.SimpleEntry<>(child.getAttributeValue("id"), new ButtonView(child, layout));
                break;
            default:
                try {
                    Class<?> clazz = Class.forName(child.getName());
                    Constructor<?> constructor = clazz.getConstructor(Element.class, Layout.class);
                    View view = (View)constructor.newInstance(child, layout);
                    result = new AbstractMap.SimpleEntry<>(child.getAttributeValue("id"),view);

                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new InvalidViewReferenceException(layoutFile.getAbsolutePath(),child.getName());
                }
                break;
        }
        return result;
    }
    /**
     *  Customized HashMap for storing views
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
