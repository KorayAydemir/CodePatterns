package src;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Learn extends JFrame {
    public Learn() {
        super("Learn");
    }

    public void addComponentsToPane() {
        JPanel newPage = new JPanel();
        newPage.add(new JButton("new page"));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

        JPanel page1 = new JPanel();
        page1.add(new JButton("p1 content"));

        tabbedPane.addTab("new tab", null, newPage, "New Tba");
        tabbedPane.setSelectedComponent(newPage);
        add(tabbedPane);
    }

    public void createAndShowGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        makeFrameFullScreen(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        addComponentsToPane();

        setVisible(true);
    }

    private void makeFrameFullScreen(JFrame frame) {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        // setSize called before setExtendedState, otherwise subtle bugs may happen such as not updating view
        frame.setSize(screenSize.width, screenSize.height);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // breaking on linux for some reason
    }
}
