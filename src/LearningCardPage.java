package src;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class LearningCardTab extends TabPage {
    public String name;
    public String title;
    public String body;
    public String cardTitle;
    public String cardDesc;
    public String cardTooltip;

    public static Icon icon = null;

    LearningCardTab(String title, String body, String cardTitle, String cardDesc, String cardTooltip, String uid) {
        super(new LearningCardPage(title, body, cardTitle, cardDesc, cardTooltip).page, title, icon, uid);
        this.name = title;
        this.title = title;
        this.body = body;
        this.cardTitle = cardTitle;
        this.cardDesc = cardDesc;
        this.cardTooltip = cardTooltip;
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
        page.setLayout(new BoxLayout(page, BoxLayout.Y_AXIS));
        page.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));

        page.add(new JLabel("<html><h1>" + title + "</h1></html>"));
        page.add(new JLabel("<html>" + body + "</html>"));
    }
}


class EditLearningCardPage {
    public JPanel page = new JPanel();
    private JTextField titleInput = new JTextField();
    private JTextArea bodyInput = new JTextArea();
    private JButton saveButton = new JButton("Save");
    private String uid; 

    public EditLearningCardPage(String title, String body, String cardTitle, String cardDesc, String cardTooltip, String uid) {
        this.uid = uid;
        createAndShowGUI();
        titleInput.setText(title);
        bodyInput.setText(body);
        setUpButton();
    }

    private void createAndShowGUI() {
        page.setLayout(new GridBagLayout());
        page.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));

        var cTitleLabel = new GridBagConstraints();
        cTitleLabel.anchor = GridBagConstraints.FIRST_LINE_START;
        cTitleLabel.gridx = 0;
        cTitleLabel.gridy = 0;
        cTitleLabel.weighty = 0;
        cTitleLabel.weightx = 0;

        var cTitleInput = new GridBagConstraints();
        cTitleInput.anchor = GridBagConstraints.FIRST_LINE_START;
        cTitleInput.fill = GridBagConstraints.HORIZONTAL;
        cTitleInput.gridx = 0;
        cTitleInput.gridy = 1;
        cTitleInput.weightx = 1;

        var cBodyLabel = new GridBagConstraints();
        cBodyLabel.anchor = GridBagConstraints.FIRST_LINE_START;
        cBodyLabel.gridx = 0;
        cBodyLabel.gridy = 2;

        var cBodyInput = new GridBagConstraints();
        cBodyInput.anchor = GridBagConstraints.FIRST_LINE_START;
        cBodyInput.fill = GridBagConstraints.HORIZONTAL;
        cBodyInput.gridx = 0;
        cBodyInput.gridy = 3;
        cBodyInput.weighty = 1;
        bodyInput.setPreferredSize(new Dimension(200, 200));

        page.add(new JLabel("Edit Title:"), cTitleLabel);
        page.add(titleInput, cTitleInput);
        page.add(new JLabel("Edit Body:"), cBodyLabel);
        page.add(new JScrollPane(bodyInput), cBodyInput);
        page.add(saveButton);
    }

    private void setUpButton() {
        saveButton.addActionListener((e) -> {
            String newTitle = titleInput.getText();
            String newBody = bodyInput.getText();

            LearningCardTab tab = new LearningCardTab(newTitle, newBody, newTitle, newBody, "", uid);
            App.getInstance().closeSelectedTab();
            App.getInstance().addTab(tab);

            //setTitle(newTitle);
            //setBody(newBody);
        });
    }
}

class EditLearningCardTab extends TabPage {
    public static Icon icon = null;

    EditLearningCardTab(LearningCardTab tab) {
        super(new EditLearningCardPage(tab.title, tab.body, tab.cardTitle, tab.cardDesc, tab.cardTooltip, tab.uid).page, tab.title, icon, tab.uid);
    }

}
