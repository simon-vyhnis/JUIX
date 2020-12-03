package layouts;

import components.Bounds;
import components.Layout;
import core.LayoutParser;
import exceptions.InvalidDimensionException;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.util.List;

public class PixelLayout extends Layout {
    public PixelLayout(Element xml, Layout layout, LayoutParser parser) {
        super(xml, layout, parser);
    }
    private void parseDimensions(){
        getViews().forEach((k,v)->{
            try {
                parseWholeDimension(v.getRawHeight());
            } catch (InvalidDimensionException e) {
                e.printStackTrace();
            }
        });
    }
    private void parseWholeDimension(String rawDimension) throws InvalidDimensionException {
        if(rawDimension.contains("+")){
            String[] singleDimensions  = rawDimension.split("\\+");
            parseSingleDimension(singleDimensions[0]);
            parseSingleDimension(singleDimensions[1]);
        }else{
            parseSingleDimension(rawDimension);
        }
    }
    private void parseSingleDimension(String singleRawDimension) throws InvalidDimensionException {
        if(singleRawDimension.contains("id:")){

        }else if(singleRawDimension.contains("px")){

        }else if(singleRawDimension.contains("dp")){

        }else if(singleRawDimension.contains("%")){

        }else{
            throw new InvalidDimensionException("Invalid dimension: "+singleRawDimension);
        }
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
