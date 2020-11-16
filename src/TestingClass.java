import components.Part;
import core.JUIXApplication;

public class TestingClass {

    public static void main(String[] args){
        JUIXApplication application = new JUIXApplication("Testing App", new Part() {});
        application.start();
    }
}
