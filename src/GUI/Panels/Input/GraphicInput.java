package GUI.Panels.Input;

import GUI.DoublePoint;
import edu.hws.jcm.awt.DataTableInput;
import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.draw.*;
import edu.hws.jcm.functions.TableFunction;
import edu.hws.jcm.functions.TableFunctionGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.util.ArrayList;

/**
 * Created by Oly on 23.02.2017.
 */
public class GraphicInput extends JPanel implements Input {
    String directory;

    JCMPanel mainPanel;
    DisplayCanvas displayCanvas;
    CoordinateRect coordinateRect;
    LimitControlPanel limitControlPanel;
    TableFunctionGraph tableFunctionGraph;
    TableFunction tableFunction;
    JTextField xTextField;
    JTextField yTextField;
    JButton addButton;
    JButton delButton;
    JButton resButton;
    JLabel xLabel;
    JLabel yLabel;

    Grid grid;
    Axes axes;

    public GraphicInput() {
        mainPanel = new JCMPanel();
        ArrayList<DoublePoint> arrayList = new ArrayList<DoublePoint>();
        GridBagConstraints c = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        coordinateRect = new CoordinateRect(-10,10,-10,10);
        displayCanvas = new DisplayCanvas(coordinateRect);
        limitControlPanel = new LimitControlPanel();
        axes = new Axes("x","y");
        axes.setAxesColor(Color.BLACK);
        axes.setLabelColor(Color.RED);

        tableFunction = new TableFunction();
        tableFunctionGraph = new TableFunctionGraph(tableFunction);
        coordinateRect.add(tableFunctionGraph);
        grid = new Grid();
        coordinateRect.add(grid);
        coordinateRect.add(axes);

        limitControlPanel.addCoords(coordinateRect);

        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        xLabel = new JLabel("x = ");
        xLabel.setOpaque(true);
        mainPanel.add(xLabel,c);

        c.gridy = 1;
        yLabel = new JLabel("y = ");
        mainPanel.add(yLabel,c);

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 0.9;
        c.gridy = 0;
        c.gridx = 1;
        xTextField = new JTextField(3);
        mainPanel.add(xTextField,c);

        c.gridy = 1;
        yTextField = new JTextField(3);
        mainPanel.add(yTextField,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        addButton = new JButton("Add");
        mainPanel.add(addButton,c);

        c.gridy = 3;
        delButton = new JButton("Remove");
        mainPanel.add(delButton,c);

        c.gridy = 4;
        resButton = new JButton("Reset");
        mainPanel.add(resButton,c);

        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 6;
        c.gridwidth = 1;
        c.weightx = 1;
        mainPanel.add(displayCanvas,c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 2;
        mainPanel.add(limitControlPanel,c);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double x = Double.parseDouble(xTextField.getText());
                double y = Double.parseDouble(yTextField.getText());

                tableFunction.addPoint(x,y);
                tableFunctionGraph.needsRedraw();
                arrayList.add(new DoublePoint(x,y));
                Input.write(directory,arrayList,"Points");
            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double x = Double.parseDouble(xTextField.getText());
                double y = Double.parseDouble(xTextField.getText());
                tableFunction.removePointAt(tableFunction.findPoint(x));
                tableFunctionGraph.needsRedraw();
                arrayList.remove(tableFunction.findPoint(x));
            }
        });

        resButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableFunction.removeAllPoints();
                tableFunctionGraph.needsRedraw();
                arrayList.clear();
            }
        });

        add(mainPanel);
    }

    @Override
    public void setDirectory(String dir) {
        directory = dir;
    }
}
