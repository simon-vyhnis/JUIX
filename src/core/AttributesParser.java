package core;


import exceptions.InvalidAttributeException;
import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Element;

import java.awt.*;

public class AttributesParser {
    private final Element xml;

    public AttributesParser(Element xml) {
        this.xml = xml;
    }

    public int getIntValue(String name, int defaultValue) throws InvalidAttributeException {
        Attribute attribute = xml.getAttribute(name);
        if(attribute.isSpecified()){
            try {
                return attribute.getIntValue();
            } catch (DataConversionException e) {
                e.printStackTrace();
                throw new InvalidAttributeException(
                        "Invalid attribute: "+attribute.getName()+
                        " view: "+xml.getAttributeValue("id"));
            }
        }else {
            return defaultValue;
        }
    }

    public float getFloatValue(String name, int defaultValue) throws InvalidAttributeException {
        Attribute attribute = xml.getAttribute(name);
        if(attribute.isSpecified()){
            try {
                return attribute.getFloatValue();
            } catch (DataConversionException e) {
                e.printStackTrace();
                throw new InvalidAttributeException(
                        "Invalid attribute: "+attribute.getName()+
                                " view: "+xml.getAttributeValue("id"));
            }
        }else {
            return defaultValue;
        }
    }


    public String getStringValue(String name, String defaultValue){
        Attribute attribute = xml.getAttribute(name);
        if (attribute.isSpecified()){
            return attribute.getValue();
        }else {
            return defaultValue;
        }
    }

    public Color getColor(String name, Color defaultColor){
        Attribute attribute = xml.getAttribute(name);
            return defaultColor;
    }




}
