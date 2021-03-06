package GUI;

import GUI.Panels.DeleteDirPanel;
import GUI.Panels.Input.AlgebraicInput;
import GUI.Panels.ChangeDirPanel;
import GUI.Panels.Input.GraphicInput;
import GUI.Panels.Output.AlgebraicOutput;
import GUI.Panels.Output.GraphicOutput;
import GUI.Panels.Output.SpreadsheetOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Oly on 20.02.2017.
 */
public class MainFrame extends JFrame{

    static MainFrame mainFrame;

    static JButton outputButton;
    static JButton inputButton;

    static ChangeDirPanel changeDirPanel;
    static DeleteDirPanel deleteDirPanel;
    static JPanel mainPanel;
    static JPanel cardsPanel;
    static CardLayout cl;
    static AlgebraicInput algInp;
    static GraphicInput gphInp;
    static AlgebraicOutput algOut;
    static SpreadsheetOutput spdOut;
    static GraphicOutput gphOut;

    static JMenuBar menuBar;
    static JMenu fileMenu;
    static JMenu viewMenu;
    static JMenuItem changeDirMenuItem;
    static JMenuItem deleteDirMenuItem;
    static JMenu inputMenu;
    static JMenu outputMenu;
    static JMenuItem algInpMenuItem;
    static JMenuItem gphInpMenuItem;
    static JMenuItem algOutMenuItem;
    static JMenuItem spdOutMenuItem;
    static JMenuItem gphOutMenuItem;

    static String directory;

    static String currentInpPanel;
    static String currentOutPanel;

    static boolean isInput;

    private void setMenuDefaults() {
        fileMenu = new JMenu("File");
        viewMenu = new JMenu("View");
        changeDirMenuItem = new JMenuItem("Change file directory"); //work in progress
        deleteDirMenuItem = new JMenuItem("Delete file directory"); //work in progress
        inputMenu = new JMenu("Input type");
        outputMenu = new JMenu("Output type");
        algInpMenuItem = new JMenuItem("Algebraical");
        gphInpMenuItem = new JMenuItem("Graphical");                //work in progress
        algOutMenuItem = new JMenuItem("Algebraical");
        spdOutMenuItem = new JMenuItem("Spreadsheet");              //work in progress
        gphOutMenuItem = new JMenuItem("Graphical");                //work in progress

        inputMenu.add(algInpMenuItem);
        inputMenu.add(gphInpMenuItem);
        outputMenu.add(algOutMenuItem);
        outputMenu.add(spdOutMenuItem);
        outputMenu.add(gphOutMenuItem);

        fileMenu.add(changeDirMenuItem);
        fileMenu.add(deleteDirMenuItem);

        viewMenu.add(inputMenu);
        viewMenu.add(outputMenu);

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);

        changeDirMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(cardsPanel,"dirChg");
                inputButton.setEnabled(true);
                outputButton.setEnabled(true);
            }
        });

        deleteDirMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(cardsPanel,"dirDel");
                inputButton.setEnabled(true);
                outputButton.setEnabled(true);
            }
        });

        algInpMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentInpPanel = "algInp";
                if (isInput) toInput();
            }
        });

        gphInpMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentInpPanel = "gphInp";
                if (isInput) toInput();
            }
        });

        algOutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentOutPanel = "algOut";
                if (!isInput) toOutput();
            }
        });

        spdOutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentOutPanel = "spdOut";
                if (!isInput) toOutput();
            }
        });

        gphOutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentOutPanel = "gphOut";
                if (!isInput) toOutput();
            }
        });
    }

    private void setPanelDefaults() {
        cardsPanel.setLayout(cl);

        changeDirPanel = new ChangeDirPanel();
        deleteDirPanel = new DeleteDirPanel();
        algInp = new AlgebraicInput();
        gphInp = new GraphicInput();
        algOut = new AlgebraicOutput();
        spdOut = new SpreadsheetOutput();
        gphOut = new GraphicOutput();

        cardsPanel.add(changeDirPanel,"dirChg");
        cardsPanel.add(deleteDirPanel,"dirDel");
        cardsPanel.add(algInp,"algInp");
        cardsPanel.add(gphInp,"gphInp");
        cardsPanel.add(algOut,"algOut");
        cardsPanel.add(spdOut,"spdOut");
        cardsPanel.add(gphOut,"gphOut");

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        mainPanel.setBackground(Color.YELLOW);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.gridheight = 9;
        mainPanel.add(cardsPanel,c);
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 9;
        mainPanel.add(inputButton,c);
        c.gridx = 3;
        mainPanel.add(outputButton,c);
    }

    private void setDefaults() {
        cl = new CardLayout();

        cardsPanel = new JPanel();
        mainPanel = new JPanel();

        menuBar = new JMenuBar();
        inputButton = new JButton("goto: Input");
        outputButton = new JButton("goto: Output");

        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toInput();
            }
        });

        outputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toOutput();
            }
        });

        setPanelDefaults();
        setMenuDefaults();

        add(mainPanel);
        setJMenuBar(menuBar);
        setDirectory("File1.txt");

        currentInpPanel = "algInp";
        currentOutPanel = "algOut";
        cl.show(cardsPanel,"algInp");

        isInput = true;
        toInput();
    }

    private MainFrame() {
        super("Project1_GUI");
        setSize(600,440);
        setMinimumSize(new Dimension(600,440));
        setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaults();
        setLocationRelativeTo(null);
        setResizable(true/*false*/);
        setVisible(true);
    }

    public static void main(String[] args) {
            mainFrame = new MainFrame();
        }

    public static void setDirectory(String dir) {
        directory = dir;
        algInp.setDirectory(dir);
        gphInp.setDirectory(dir);
        algOut.setDirectory(dir);
        gphOut.setDirectory(dir);
    }

    public static String getDirectory() {
        return directory;
    }

    public static void toOutput() {
        cl.show(cardsPanel,currentOutPanel);
        isInput = false;
        outputButton.setEnabled(false);
        inputButton.setEnabled(true);
    }

    public static void toInput() {
        cl.show(cardsPanel,currentInpPanel);
        isInput = true;
        inputButton.setEnabled(false);
        outputButton.setEnabled(true);
    }
}

//TODO: Method for deleting the file http://devcolibri.com/1141