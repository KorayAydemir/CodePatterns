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
    public String cardTitle;
    public String cardDesc;
    public String uid;

    public LearningCardPage(String title, String body, String cardTitle, String cardDesc, String cardTooltip,
            String uid) {
        createAndShowGUI(title, body);
        this.title = title;
        this.body = body;
        this.cardTitle = cardTitle;
        this.cardDesc = cardDesc;
        this.uid = uid;
    }

    private void createAndShowGUI(String title, String body) {
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));

        component.add(new JLabel("<html><h1>" + title + "</h1></html>"));
        component.add(new JLabel("<html>" + body + "</html>"));
    }
}

class AddEditLearningPageGenericGUI {
    public static void createAndShowGUI(JPanel component, JButton saveButton,
            JTextField titleInput, JTextField cardTitleInput, JTextArea cardDescInput, JTextArea bodyInput) {

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

        var cCardTitleLabel = new GridBagConstraints();
        cCardTitleLabel.anchor = GridBagConstraints.FIRST_LINE_START;
        cCardTitleLabel.gridx = 0;
        cCardTitleLabel.gridy = 2;

        var cCardTitleInput = new GridBagConstraints();
        cCardTitleInput.anchor = GridBagConstraints.FIRST_LINE_START;
        cCardTitleInput.fill = GridBagConstraints.HORIZONTAL;
        cCardTitleInput.gridx = 0;
        cCardTitleInput.gridy = 3;

        var cCardDescLabel = new GridBagConstraints();
        cCardDescLabel.anchor = GridBagConstraints.FIRST_LINE_START;
        cCardDescLabel.gridx = 0;
        cCardDescLabel.gridy = 4;

        var cCardDescInput = new GridBagConstraints();
        cCardDescInput.anchor = GridBagConstraints.FIRST_LINE_START;
        cCardDescInput.fill = GridBagConstraints.HORIZONTAL;
        cCardDescInput.gridx = 0;
        cCardDescInput.gridy = 5;
        cCardDescInput.weighty = 0;
        cardDescInput.setPreferredSize(new Dimension(100, 100));

        var cBodyLabel = new GridBagConstraints();
        cBodyLabel.anchor = GridBagConstraints.FIRST_LINE_START;
        cBodyLabel.gridx = 0;
        cBodyLabel.gridy = 6;

        var cBodyInput = new GridBagConstraints();
        cBodyInput.anchor = GridBagConstraints.FIRST_LINE_START;
        cBodyInput.fill = GridBagConstraints.HORIZONTAL;
        cBodyInput.gridx = 0;
        cBodyInput.gridy = 7;
        cBodyInput.weighty = 1;
        bodyInput.setPreferredSize(new Dimension(200, 200));

        component.add(new JLabel("Add / Edit Title:"), cTitleLabel);
        component.add(titleInput, cTitleInput);
        component.add(new JLabel("Add / Edit Card Title:"), cCardTitleLabel);
        component.add(cardTitleInput, cCardTitleInput);
        component.add(new JLabel("Add / Edit Card Description:"), cCardDescLabel);
        component.add(new JScrollPane(cardDescInput), cCardDescInput);
        component.add(new JLabel("Add / Edit Body:"), cBodyLabel);
        component.add(new JScrollPane(bodyInput), cBodyInput);
        component.add(saveButton);
    }
}
