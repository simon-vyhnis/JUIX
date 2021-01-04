package testing;

import components.Part;
import core.JUIXApplication;

import java.io.File;

public class MainPart extends Part {
    public MainPart(JUIXApplication application) {
        super(application);
    }

    @Override
    protected void onCreate() {
        File file = new File("C:\\Users\\simon\\IdeaProjects\\JUIX\\src\\testing/test_layout.xml");
        setLayout(file);
    }
}
