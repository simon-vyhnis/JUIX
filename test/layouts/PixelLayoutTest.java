package layouts;

import org.jdom2.Element;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PixelLayoutTest {
    @Test
    public void parsePercentDimensionTest(){
        PixelLayout test = new PixelLayout(null,null,null);
        int result = test.parsePixelsDimension("20px");
        assertEquals(20, result);
    }

}