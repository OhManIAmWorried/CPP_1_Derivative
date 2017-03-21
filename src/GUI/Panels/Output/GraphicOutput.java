package GUI.Panels.Output;

import GUI.Panels.Output.Output;

import javax.swing.*;

/**
 * Created by Oly on 21.02.2017.
 */
public class GraphicOutput extends JPanel implements Output {
    String directory;

    @Override
    public void setDirectory(String dir) {
        directory = dir;
    }

    @Override
    public void showDerivative() {

    }
}
