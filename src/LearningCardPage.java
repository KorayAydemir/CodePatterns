package src;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class LearningCardTab extends TabPage {
    public String name;
    public static Icon icon = null;

    LearningCardTab(String title, String body, String cardTitle, String cardDesc, String cardTooltip) {
        super(new LearningCardPage(title, body, cardTitle, cardDesc, cardTooltip).page, title, icon);
        this.name = title;
    }
}

public class LearningCardPage {
    public JPanel page = new JPanel();
    String title;
    String body;

    public LearningCardPage(String title, String body, String cardTitle, String cardDesc, String cardTooltip) {
        this.title = title;
        this.body = body;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        page.add(new JLabel("<html><h1>" + title + "</h1></html>"));
        page.add(new JLabel("<html>" + body + "</html>"));

        page.setVisible(true);
    }
}

