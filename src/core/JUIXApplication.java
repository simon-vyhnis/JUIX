package core;

import com.sun.istack.internal.NotNull;
import components.*;
import exceptions.InvalidPartException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class JUIXApplication {
    private JFrame frame;
    private Canvas canvas;

    private Part currentPart;

    private int ticksPerSecond;

    private List<UpdateReceiver> receivers;

    /**
     * If you do not need anything else to to define than name and start components.Part than use this constructor.
     */
    public JUIXApplication(@NotNull String name, @NotNull Class<?> startPartClass ){
        try {
            currentPart = createPart(startPartClass);
        } catch (InvalidPartException e) {
            e.printStackTrace();
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame();
        frame.setTitle(name);
        frame.setBounds(0, 0, screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                currentPart.notifyWindowResized();
            }
        });
        setIcon();
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
        for(UpdateReceiver receiver:receivers){
            if(receiver==null){
                receivers.remove(receiver);
            }else{
                receiver.onReceive();
            }
        }
    }

    /**
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

    public void switchPart(Class<?> destination){


    }

    public void registerUpdateReceiver(UpdateReceiver receiver){
        receivers.add(receiver);
    }

    public Part createPart(Class<?> partClass) throws InvalidPartException {
        try {
            Constructor<?> constructor =  partClass.getConstructor(JUIXApplication.class);
            return (Part) constructor.newInstance(this);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.out.println("Invalid part!");
            e.printStackTrace();
            throw new InvalidPartException();
        }
    }

    private void setIcon(){
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
    }

}