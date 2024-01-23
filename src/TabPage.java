package src;

import javax.swing.Icon;
import javax.swing.JPanel;

public class TabPage {
    public final JPanel component;
    public final String title;
    public final Icon icon;
    public final String uid;

    public TabPage(JPanel component, String title, Icon icon, String uid) {
        this.component = component;
        this.title = title;
        this.icon = icon;
        this.uid = uid;
    }
}
