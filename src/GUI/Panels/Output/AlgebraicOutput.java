package GUI.Panels.Output;

import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.awt.VariableInput;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Oly on 20.02.2017.
 */
public class AlgebraicOutput extends JPanel implements Output{

    JScrollPane derivativeScrollPane;
    JScrollPane valueScrollPane;
    String directory;
    //JCMPanel mainPanel;
    JPanel mainPanel;
    JLabel derivativeLabel;
    JLabel varValLabel;
    VariableInput varInpField;
    JLabel expressionValLabel;

    Expression derivExpressionGlobal;
    Variable variableGlobal;

    public AlgebraicOutput() {
        /*Initialization*/
        //mainPanel = new JCMPanel(new GridBagLayout());
        //mainPanel = new JPanel(new GridBagLayout());
        setLayout(new GridBagLayout());
        derivativeLabel = new JLabel("<html><pre>");
        varValLabel = new JLabel("variable value =");
        derivativeScrollPane = new JScrollPane(derivativeLabel,ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        derivativeScrollPane.setMinimumSize(new Dimension(440,50));
        expressionValLabel = new JLabel("<html><pre>derivative in point = ");
        valueScrollPane = new JScrollPane(expressionValLabel,ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        valueScrollPane.setMinimumSize(new Dimension(440,50));
        varInpField = new VariableInput();
        /*Building====================================================================================================*/
        GridBagConstraints c = new GridBagConstraints();
        /*DerivativeScrollPane*/
        c.gridx = 0; c.gridy = 0; c.gridheight = 1; c.gridwidth = 2; c.weightx = 2; c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL; c.anchor = GridBagConstraints.CENTER; /*mainPanel.*/add(derivativeScrollPane,c);
        /*VarValLabel*/
        c.gridx = 0; c.gridy = 1; c.gridheight = 1; c.gridwidth = 1; c.weightx = 0.4; c.weighty = 1;
        c.fill = GridBagConstraints.NONE; c.anchor = GridBagConstraints.WEST; /*mainPanel.*/add(varValLabel,c);
        /*VarInpField*/
        c.gridx = 1; c.gridy = 1; c.gridheight = 1; c.gridwidth = 1; c.weightx = 1.6; c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL; c.anchor = GridBagConstraints.CENTER; /*mainPanel.*/add(varInpField,c);
        /*ValueScrollPane*/
        c.gridx = 0; c.gridy = 2; c.gridheight = 1; c.gridwidth = 2; c.weightx = 2; c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL; c.anchor = GridBagConstraints.CENTER; /*mainPanel.*/add(valueScrollPane,c);
        //add(mainPanel);

        /*Defaults*/

        /*Listeners*/
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                showDerivative();
                varInpField.requestFocus();
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
                derivativeLabel.setText("");
            }

            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });

        varInpField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                variableGlobal.setVal(Double.parseDouble(varInpField.getText()));
                expressionValLabel.setText("<html><pre>Derivative in point = " + derivExpressionGlobal.getVal());
            }
        });
    }

    @Override
    public void setDirectory(String dir) {
        directory = dir;
    }

    @Override
    public void showDerivative(){
        String string = Output.obtainString(directory);
        String[] stringArr = string.split(" ");
        switch (stringArr[0]) {
            case "Expression": {
                Parser parser = new Parser();
                variableGlobal = new Variable(stringArr[1]);
                parser.add(variableGlobal);
                derivExpressionGlobal = parser.parse(stringArr[2]).derivative(variableGlobal);
                derivativeLabel.setText("<html><pre>Current derivative: " + derivExpressionGlobal.toString() + ";");
                varValLabel.setText(stringArr[1] + " = ");
                break;
            }
            case "Points": {
                Double[][] arr = new Double[2][stringArr.length - 1];
                for (int i = 1; i < stringArr.length; i++) {
                    arr[0][i - 1] = Double.parseDouble(stringArr[i].split(":")[0]);
                    arr[1][i - 1] = Double.parseDouble(stringArr[i].split(":")[1]);
                }
                variableGlobal = new Variable("x");
                derivExpressionGlobal = getExpression(arr).derivative(variableGlobal);
                derivativeLabel.setText("<html><pre>Current derivative: " + derivExpressionGlobal);
                break;

            }
        }
    }

    private Expression getExpression(Double[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < arr.length; k++) {
            sb.append("(");
            sb.append(arr[k][1]);
            sb.append("*");
            for (int j = 0; j < arr.length; j++) {
                sb.append("((x-").append(arr[j][0]).append(")").append("/(").append(arr[k][0]).append("-").append(arr[j][0]).append("))");
                if (j != arr.length - 1) sb.append("*");
            }
            if (k != arr.length - 1) sb.append(")+"); else sb.append(")");
        }

        System.out.println(sb.toString());
        Parser parser = new Parser();
        parser.add(variableGlobal);
        return parser.parse(sb.toString());
    }
}
