package src;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddNewLearningCardPage {
    JPanel component = new JPanel();
    public AddNewLearningCardPage() {
        JButton saveButton = new JButton("Save");
        JTextField titleInput = new JTextField();
        JTextArea bodyInput = new JTextArea();
        JTextField cardTitleInput = new JTextField();
        JTextArea cardDescInput = new JTextArea();

        AddEditLearningPageGenericGUI.createAndShowGUI(component, saveButton, titleInput, cardTitleInput, cardDescInput, bodyInput);
    }
}
