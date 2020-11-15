import javax.swing.*;
import java.awt.*;

public class JUIXApplication {
    private JFrame frame;
    private Bounds bounds;
    private Part currentPart;

    /*
     * If you do not need anything else to to define than name and start Part than use this constructor.
     */
    public JUIXApplication(String name, Part startPart){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame();
        frame.setTitle(name);
        frame.setBounds(0, 0, screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /*
     * Use this constructor if you want to build JUIX application with config file.
     * A config file have to contain at least name and start part. Learn more about config files <a>here<a/>.
     */
    public JUIXApplication(JUIXFile configFile){
        //TODO: create this
    }

    public void start(){
        frame.setVisible(true);
    }

    /*
     * Returns the JFrame, that the app is displayed on.
     * You should not access the frame manually, but if you are sure, that there isn't other way, than use this method.
     */
    public JFrame getFrame(){
        return frame;
    }
}