package core;

import components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class JUIXApplication {
    private JFrame frame;
    private Canvas canvas;

    private Bounds bounds;
    private Part currentPart;

    private int ticksPerSecond;

    /*
     * If you do not need anything else to to define than name and start components.Part than use this constructor.
     */
    public JUIXApplication(String name, Part startPart){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame();
        frame.setTitle(name);
        frame.setBounds(0, 0, screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        canvas = new Canvas();
        frame.add(canvas);
        frame.setVisible(true);
        Cycle cycle = new Cycle(this);
        cycle.start();
    }

    void draw(){
        BufferStrategy strategy = canvas.getBufferStrategy();
        if(strategy==null){
            canvas.createBufferStrategy(2);
            return;
        }
        Graphics g = strategy.getDrawGraphics();
        canvas.paint(g);
        currentPart.draw(g);
        g.dispose();
        strategy.show();
    }

    void update(int tick){
        currentPart.update(tick);
    }

    /*
     * Use this constructor if you want to build JUIX application with config file.
     * A config file have to contain at least name and start part. Learn more about config files <a>here<a/>.
     */
    public JUIXApplication(JUIXFile configFile){
        //TODO: create this
    }

    /*
     * Returns the JFrame, that the app is displayed on.
     * You should not access the frame manually, but if you are sure, that there isn't other way, than use this method.
     */
    public JFrame getFrame(){
        return frame;
    }

    /*
     * Returns tick per second, what is the app currently running.
     */
    public int getTicksPerSecond() {
        return ticksPerSecond;
    }
}