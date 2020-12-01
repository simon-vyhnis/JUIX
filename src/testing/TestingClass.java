package testing;

import components.Part;
import core.JUIXApplication;
import core.LayoutParser;
import exceptions.MissingAttributeException;

import java.io.File;

public class TestingClass {

    public static void main(String[] args){
        //JUIXApplication application = new JUIXApplication("Testing App", new Part() {});
        LayoutParser parser = new LayoutParser();
        File file = new File("C:\\Users\\simon\\IdeaProjects\\JUIX\\src\\testing/test_layout.xml");
        if(file.exists()) {
            try {
                parser.parse(file);
            } catch (MissingAttributeException e) {
                e.printStackTrace();
            }
        }else
            System.out.println("MANUAL_TESTING: "+" File not found");
    }
}
