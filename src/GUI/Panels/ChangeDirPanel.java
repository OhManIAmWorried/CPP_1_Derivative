package GUI.Panels;

import GUI.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Oly on 02.03.2017.
 */
public class ChangeDirPanel extends JPanel {
    JTextField dirField;
    JButton submitButton;
    JLabel currentDirLabel;
    JLabel newDirLabel;
    GridBagConstraints c;
    String directory;
    public ChangeDirPanel() {
        /*Initialization==============================================================================================*/
        dirField = new JTextField();
        submitButton = new JButton("submit");
        currentDirLabel = new JLabel("Current file directory: " + MainFrame.getDirectory());
        newDirLabel = new JLabel("New file directory: ");
        /*Construction================================================================================================*/
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        /*NewDirLabel*/
        c.gridx = 0; c.gridy = 0; c.gridheight = 1; c.gridwidth = 1; c.weightx = 0.2; c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL; c.anchor = GridBagConstraints.WEST; add(newDirLabel,c);
        /*DirField*/
        c.gridx = 1; c.gridy = 0; c.gridheight = 1; c.gridwidth = 1; c.weightx = 1.6; c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL; c.anchor = GridBagConstraints.CENTER; add(dirField,c);
        /*SubmitButton*/
        c.gridx = 2; c.gridy = 0; c.gridheight = 1; c.gridwidth = 1; c.weightx = 0.2; c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL; c.anchor = GridBagConstraints.EAST; add(submitButton,c);
        /*Label*/
        c.gridx = 0; c.gridy = 1; c.gridheight = 1; c.gridwidth = 3; c.weightx = 2.0; c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL; c.anchor = GridBagConstraints.CENTER; add(currentDirLabel,c);
        /*Listeners===================================================================================================*/
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                directory = dirField.getText();
                File f = new File(directory);
                MainFrame.setDirectory(directory);
                currentDirLabel.setText("Current file directory: " + MainFrame.getDirectory());
            }
        });
    }
}
