package layouts;

import components.Bounds;
import components.Layout;
import core.LayoutParser;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.util.List;

public class PixelLayout extends Layout {
    public PixelLayout(Element xml, Layout layout, LayoutParser parser) {
        super(xml, layout, parser);
    }
    private void parseDimensions(){
        getViews().forEach((k,v)->{
            parseWholeDimension(v.getRawHeight());
        });
    }
    private void parseWholeDimension(String rawDimension){
        if(rawDimension.contains("+")){
            String[] singleDimensions  = rawDimension.split("\\+");
            parseSingleDimension(singleDimensions[0]);
            parseSingleDimension(singleDimensions[1]);
        }else{
            parseSingleDimension(rawDimension);
        }
    }
    private void parseSingleDimension(String singleRawDimension){

    }

    @Override
    public int getContentWidth() {
        return 0;
    }

    @Override
    public int getContentHeight() {
        return 0;
    }
}
