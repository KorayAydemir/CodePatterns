package src;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class App {
    private static final JFrame frame = new JFrame("Hello, World!");
    private static final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

    public App() {
        createAndShowGUI(frame);
    }

    public void addTab(TabPage page) {
        tabbedPane.addTab(page.getTitle(), page.getIcon(), page.getPane(), page.getTooltip());
        tabbedPane.setSelectedComponent(page.getPane());
    }

    private void addComponentsToPane(JFrame frame) {
        frame.add(tabbedPane);
    }

    private void createAndShowGUI(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        makeFrameFullScreen(frame);

        addComponentsToPane(frame);


        frame.setVisible(true);

    }

    private void makeFrameFullScreen(JFrame frame) {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        // setSize called before setExtendedState, otherwise subtle bugs may happen such as not updating view
        frame.setSize(screenSize.width, screenSize.height);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // breaking on linux for some reason
    }
}
