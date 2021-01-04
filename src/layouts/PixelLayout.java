package layouts;

import components.Bounds;
import components.Layout;
import core.LayoutParser;
import exceptions.InvalidDimensionException;
import org.jdom2.Element;

public class PixelLayout extends Layout {
    public PixelLayout(Element xml, Layout layout, LayoutParser parser) {
        super(xml, layout, parser);
    }

    public void parseViews(){
        getViews().forEach((k,v)->{
            try {
                parseWholeDimension(v.getRawHeight());
                parseWholeDimension(v.getRawY());
            } catch (InvalidDimensionException e) {
                e.printStackTrace();
            }
        });
    }
    public int parseWholeDimension(String rawDimension) throws InvalidDimensionException {
        if(rawDimension.contains("+")){
            String[] singleDimensions  = rawDimension.split("\\+");
            return parseSingleDimension(singleDimensions[0]) + parseSingleDimension(singleDimensions[1]);
        }else{
            return parseSingleDimension(rawDimension);
        }
    }
    public int parseSingleDimension(String singleRawDimension) throws InvalidDimensionException {
        try {
            if(singleRawDimension.contains("id:")){
                return parseIdDimension(singleRawDimension);
            }else if(singleRawDimension.contains("px")){
                return parsePixelsDimension(singleRawDimension);
            }else if(singleRawDimension.contains("%")){
                return parsePercentDimension(singleRawDimension);
            }else{
                throw new InvalidDimensionException("Invalid dimension: "+singleRawDimension);
            }
        }catch (NumberFormatException e){
            throw new InvalidDimensionException("Invalid dimension: "+singleRawDimension);
        }
    }

    public int parsePercentDimension(String percentDimension) {
        int percent = Integer.parseInt(percentDimension.replace("%", ""));
        return getX() / 100 * percent;
    }

    public int parsePixelsDimension(String pixelDimension) {
        return Integer.parseInt(pixelDimension.replace("px", ""));
    }

    public int parseIdDimension(String idDimension){
        //getView(idDimension);
        return 0;
    }

    @Override
    public void notifyViewsChanged() {
        parseViews();
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
