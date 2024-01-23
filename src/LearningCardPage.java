package src;

import javax.swing.JPanel;

public class LearningCardPage extends TabPage {
    JPanel page = new JPanel();
    LearningCard card = new LearningCard("title", "tooltip tooltip tooltip tooltip tooltip tooltip ", "contentPreview", this);
    public LearningCardPage(String title, String desc, String contentPreview) {
        super(null, "Learning Cards", null);
        card = new LearningCard("title", "tooltip tooltip tooltip tooltip tooltip tooltip ", "contentPreview", this);
    }
}
