import javax.swing.*;
import java.awt.*;

public class JUIXApplication {
    private JFrame frame;
    public JUIXApplication(String name, Part startPart){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame();
        frame.setTitle(name);
        frame.setBounds(0, 0, screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public JUIXApplication(JUIXFile configFile){

    }
    public void start(){

    }
}