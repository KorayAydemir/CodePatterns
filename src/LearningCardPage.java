package src;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LearningCardPage implements TabPage {
    public JPanel component = new JPanel();
    public String title;
    public String body;
    public String uid;

    public LearningCardPage(String title, String body, String cardTitle, String cardDesc, String cardTooltip, String uid) {
        createAndShowGUI(title, body);
        this.title = title;
        this.body = body;
        this.uid = uid;
    }

    private void createAndShowGUI(String title, String body) {
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));

        component.add(new JLabel("<html><h1>" + title + "</h1></html>"));
        component.add(new JLabel("<html>" + body + "</html>"));
    }
}


class EditLearningCardPage implements TabPage {
    public JPanel component = new JPanel();

    public EditLearningCardPage(LearningCardPage editPage) {
        var editPageTitle = editPage.title;
        var editPageBody = editPage.body;

        JButton saveButton = new JButton("Save");
        JTextField titleInput = new JTextField();
        JTextArea bodyInput = new JTextArea();

        new EditLearningCardPageGUI()
            .createAndShowGUI(editPageTitle, editPageBody, saveButton, titleInput, bodyInput)
            .createBehaviour(saveButton, titleInput, bodyInput, editPage.uid);
    }

    private class EditLearningCardPageGUI {
        public EditLearningCardPageGUI() {}

        private EditLearningCardPageGUI createAndShowGUI(String editPageTitle, String editPageBody, JButton saveButton, JTextField titleInput, JTextArea bodyInput) {

            titleInput.setText(editPageTitle);
            bodyInput.setText(editPageBody);

            component.setLayout(new GridBagLayout());
            component.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));

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

            component.add(new JLabel("Edit Title:"), cTitleLabel);
            component.add(titleInput, cTitleInput);
            component.add(new JLabel("Edit Body:"), cBodyLabel);
            component.add(new JScrollPane(bodyInput), cBodyInput);
            component.add(saveButton);
            return this;
        }

        private void createBehaviour(JButton saveButton, JTextField titleInput, JTextArea bodyInput, String uid) {
            saveButton.addActionListener((e) -> {
                String newTitle = titleInput.getText();
                String newBody = bodyInput.getText();

                LearningCardPage page = new LearningCardPage(newTitle, newBody, newTitle, newBody, "", uid);
                App.closeSelectedTab();
                App.addTab(newTitle, null, page.component, "", true);
            });
        }
    }
}
