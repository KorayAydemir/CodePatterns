package src;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LearningCardsPage {
    JPanel page = new JPanel();

    public LearningCardsPage() {
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        page.add(new JButton("new page"));
    }

    public JPanel getPanel() {
        return page;
    }
}

class LearningCardsTab extends TabPage {
    public static final String name = "Learning Cards";
    public static final Icon icon = null;
    public static final String tooltip = "Manage your learning cards";

    LearningCardsTab() {
        super(new LearningCardsPage().getPanel(), name, icon, tooltip);
    }
}

