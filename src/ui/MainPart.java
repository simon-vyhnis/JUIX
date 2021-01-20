package ui;

import components.Part;
import core.JUIXApplication;

import java.io.File;
import java.net.URISyntaxException;

public class MainPart extends Part {
    public MainPart(JUIXApplication application) {
        super(application);
    }

    @Override
    protected void onCreate() {
        File file = null;
        try {
            file = new File(getClass().getClassLoader().getResource("file.txt").toURI());
        } catch (URISyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
        setLayout(file);
    }
}
