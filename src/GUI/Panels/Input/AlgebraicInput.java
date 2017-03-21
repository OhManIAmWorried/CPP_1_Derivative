package GUI.Panels.Input;

import edu.hws.jcm.awt.ExpressionInput;
import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Oly on 20.02.2017
 */
public class AlgebraicInput extends JPanel implements Input{
    JCMPanel mainPanel;
    ExpressionInput inputBox;
    JButton submitButton;

    Variable x;
    Expression expression;
    String directory;

    JLabel warningLabel;
    Parser parser;

    public AlgebraicInput() {
        mainPanel = new JCMPanel();
        mainPanel.setLayout(new GridLayout(3,3));
        submitButton = new JButton("submit");
        warningLabel = new JLabel();
        parser = new Parser();
        inputBox = new ExpressionInput("",parser);
        x = new Variable("x");
        parser.add(x);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputBox.checkInput();
                expression = inputBox.getExpression();
                Input.write(directory,expression,"Expression",x.toString());
            }
        });

        add(mainPanel);
        mainPanel.add(inputBox);
        mainPanel.add(submitButton);
        mainPanel.add(warningLabel);
    }

    @Override
    public void setDirectory(String dir) {
        directory = dir;
    }
}
