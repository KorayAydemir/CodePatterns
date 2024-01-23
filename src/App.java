package src;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class App {
    private static App single_instance = null;

    private static final JFrame frame = new JFrame("Hello, World!");
    private static final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

    private App() {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI(frame);
        });
    }

    public static synchronized App getInstance() {
        if (single_instance == null) {
            single_instance = new App();
        }

        return single_instance;
    }

    public void closeSelectedTab() {
        tabbedPane.remove(tabbedPane.getSelectedIndex());
    }

    public void addTab(TabPage tab) {
        if (!isTabOpen(tab)) {
            tabbedPane.addTab(tab.title, tab.icon, tab.component);
        }
        tabbedPane.setSelectedComponent(tab.component);
    }

    private boolean isTabOpen(TabPage tab) {
        return tabbedPane.indexOfComponent(tab.component) != -1;
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
