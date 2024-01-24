package src;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class App {
    private static App single_instance = null;

    private static final JFrame frame = new JFrame("Hello, World!");
    public static final JTabbedPane component = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

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

    private void addComponentsToPane(JFrame frame) {
        frame.add(component);
    }

    private static boolean isPageOpen(JPanel page) {
        return component.indexOfComponent(page) != -1;
    }

    public static void addTab(String title, String icon, JPanel page, String uid, boolean switchToTab) {
        if (!isPageOpen(page)) {
            component.addTab(title, null, page, uid);
        }
        if (switchToTab || isPageOpen(page)) {
            component.setSelectedComponent(page);
        }
    }

    public static void closeSelectedTab() {
        component.remove(App.component.getSelectedIndex());
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
