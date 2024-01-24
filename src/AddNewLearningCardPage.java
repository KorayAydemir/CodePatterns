package src;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONObject;

public class AddNewLearningCardPage {
    JPanel component = new JPanel();
    public AddNewLearningCardPage() {
        JButton saveButton = new JButton("Save");
        JTextField titleInput = new JTextField();
        JTextArea bodyInput = new JTextArea();
        JTextField cardTitleInput = new JTextField();
        JTextArea cardDescInput = new JTextArea();

        new AddLearningCardPageGUI()
                .createAndShowGUI(component, saveButton, titleInput, cardTitleInput, cardDescInput, bodyInput)
                .createBehaviour(saveButton, titleInput, cardTitleInput, cardDescInput, bodyInput);

    }

    public class AddLearningCardPageGUI {
        private AddLearningCardPageGUI() {}

        private AddLearningCardPageGUI createAndShowGUI(JPanel component, JButton saveButton, JTextField titleInput, JTextField cardTitleInput, JTextArea cardDescInput, JTextArea bodyInput) {
            AddEditLearningPageGenericGUI.createAndShowGUI(component, saveButton, titleInput, cardTitleInput, cardDescInput, bodyInput);

            return this;
        }

        private void createBehaviour(JButton saveButton, JTextField titleInput, JTextField cardTitleInput, JTextArea cardDescInput, JTextArea bodyInput) {
            saveButton.addActionListener((e) -> {
                String newTitle = titleInput.getText();
                String newBody = bodyInput.getText();
                String newCardTitle = cardTitleInput.getText();
                String newCardDesc = cardDescInput.getText();

                JSONObject newCard = new JSONObject();
                newCard.put("uid", Utils.generateUID());
                newCard.put("title", newTitle);
                newCard.put("body", newBody);
                newCard.put("card", new JSONObject());
                newCard.getJSONObject("card").put("title", newCardTitle);
                newCard.getJSONObject("card").put("desc", newCardDesc);
                newCard.getJSONObject("card").put("tooltip", newCardDesc);

                try {
                    JSONArray jsonArray = Utils.parseJSONArrayFromFile("src/data/LearningPages.json");
                    jsonArray.put(newCard);
                    Utils.writeJSONArrayToFile("src/data/LearningPages.json", jsonArray);
                } catch (IOException io) {
                    System.out.println("IO Error: " + io.getMessage());
                    io.printStackTrace();
                } finally {
                    SwingUtilities.invokeLater(() -> {
                        App.component.setComponentAt(0, new LearningCardsListPage().component);
                        App.component.setSelectedIndex(0);
                    });
                }
            });
        }
    }
}
