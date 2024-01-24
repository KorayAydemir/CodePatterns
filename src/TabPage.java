package src;

import javax.swing.JPanel;

public interface TabPage {
    default void addTab(String title, String Icon, JPanel page, String uid) {
        if (isTabOpen(uid)) {
            App.component.setSelectedComponent(tab.component);
            return;
        }

        App.component.addTab(tab.title, tab.icon, tab.component);
    }

    default boolean isTabOpen(String uid) {
    }
}
