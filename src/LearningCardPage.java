package src;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LearningCardPage extends TabPage {
    JPanel component = new JPanel();
    String title;
    String body;

    public LearningCardPage(String title, String body, String cardTitle, String cardDesc, String cardTooltip) {
        super(null, title, null);
        this.title = title;
        this.body = body;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        component.add(new JLabel("<html><h1>" + title + "</h1></html>"));
        component.add(new JLabel("<html>" + body + "</html>"));

        component.setVisible(true);
    }
}

