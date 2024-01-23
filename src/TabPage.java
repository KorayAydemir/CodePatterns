package src;

import javax.swing.Icon;
import javax.swing.JPanel;

public class TabPage {
    public final String title;
    public final Icon icon;
    public final JPanel component;

    public TabPage(JPanel component, String title, Icon icon) {
        this.title = title;
        this.icon = icon;
        this.component = component;
    }
}
