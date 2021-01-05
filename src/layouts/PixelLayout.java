package layouts;

import components.Bounds;
import components.Layout;
import core.JUIXApplication;
import core.LayoutParser;
import exceptions.InvalidDimensionException;
import org.jdom2.Element;

public class PixelLayout extends Layout {
    public PixelLayout(Element xml, Layout layout, LayoutParser parser, JUIXApplication application) {
        super(xml, layout, parser, application);
    }

    public void parseViews(){
        getViews().forEach((k,v)->{
            try {
                v.setAbsolutePosition(parseWholeDimension(v.getRawX(),true), parseWholeDimension(v.getRawY(),false));
                v.setAbsoluteSize(parseWholeDimension(v.getRawWidth(),true), parseWholeDimension(v.getRawHeight(), false));
            } catch (InvalidDimensionException e) {
                e.printStackTrace();
            }
        });
    }
    public int parseWholeDimension(String rawDimension, boolean isX) throws InvalidDimensionException {
        if(rawDimension.contains("+")){
            String[] singleDimensions  = rawDimension.split("\\+");
            return parseSingleDimension(singleDimensions[0],isX) + parseSingleDimension(singleDimensions[1],isX);
        }else{
            return parseSingleDimension(rawDimension,isX);
        }
    }
    public int parseSingleDimension(String singleRawDimension, boolean isX) throws InvalidDimensionException {
        try {
            if(singleRawDimension.contains("px")){
                return parsePixelsValue(singleRawDimension);
            }else if(singleRawDimension.contains("%")){
                if (isX) {
                    return parsePercentDimensionX(singleRawDimension);
                }else{
                    return parsePercentDimensionY(singleRawDimension);
                }
            }else{
                throw new InvalidDimensionException("Invalid dimension: "+singleRawDimension);
            }
        }catch (NumberFormatException e){
            throw new InvalidDimensionException("Invalid dimension: "+singleRawDimension);
        }
    }

    public int parsePercentDimensionY(String percentDimension) {
        int percent = Integer.parseInt(percentDimension.replace("%", ""));
        return application.getWindowHeight()/ 100 * percent;
    }

    public int parsePercentDimensionX(String percentDimension) {
        int percent = Integer.parseInt(percentDimension.replace("%", ""));
        return application.getWindowWidth()/ 100 * percent;
    }

    public int parsePixelsValue(String pixelDimension) {
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
