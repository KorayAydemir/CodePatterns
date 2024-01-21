package src;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class TabPage {
    public final String title;
    public final Icon icon;
    public final String tooltip;
    public final JPanel component;

    public TabPage(JPanel component, String title, Icon icon, String tooltip) {
        this.title = title;
        this.icon = icon;
        this.tooltip = tooltip;
        this.component = component;
    }

    public JComponent getPane() {
        return component;
    }

    public String getTitle() {
        return title;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getTooltip() {
        return tooltip;
    }
}
