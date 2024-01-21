package src;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Learn {
    private static final JFrame frame = new JFrame("Hello, World!");

    public Learn() {
        createAndShowGUI(frame);
    }

    public void addComponentsToPane(JFrame frame) {
        JPanel newPage = new JPanel();
        newPage.add(new JButton("new page"));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

        JPanel page1 = new JPanel();
        page1.add(new JButton("p1 content"));

        tabbedPane.addTab("new tab", null, newPage, "New Tba");
        tabbedPane.setSelectedComponent(newPage);
        frame.add(tabbedPane);
    }

    public void createAndShowGUI(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        addComponentsToPane(frame);

        makeFrameFullScreen(frame);

        frame.setVisible(true);
    }

    private void makeFrameFullScreen(JFrame frame) {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        // setSize called before setExtendedState, otherwise subtle bugs may happen such as not updating view
        frame.setSize(screenSize.width, screenSize.height);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // breaking on linux for some reason
    }
}
