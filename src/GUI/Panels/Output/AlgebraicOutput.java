package GUI.Panels.Output;

import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Oly on 20.02.2017.
 */
public class AlgebraicOutput extends JPanel implements Output{

    String directory;
    JCMPanel mainPanel;
    Parser parser;
    Variable x;
    String string;
    JLabel label;
    Expression expression;

    public AlgebraicOutput() {
        mainPanel = new JCMPanel();
        parser = new Parser();
        label = new JLabel();
        x = new Variable("x");
        parser.add(x);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                showExpression();
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
                label.setText("");
            }

            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        mainPanel.add(label);
        add(mainPanel);
    }

    @Override
    public void setDirectory(String dir) {
        directory = dir;
    }

    @Override
    public void showExpression() {
        string = Output.obtainExpression(directory);
        expression = parser.parse(string);
        label.setText(expression.derivative(x).toString());
    }
}
