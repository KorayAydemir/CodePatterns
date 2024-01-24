package src;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONObject;

public class EditLearningCardPage implements TabPage {
    public JPanel component = new JPanel();

    public EditLearningCardPage(LearningCardPage editPage) {
        var editPageTitle = editPage.title;
        var editPageBody = editPage.body;
        var editCardTitle = editPage.cardTitle;
        var editCardDesc = editPage.cardDesc;

        JButton saveButton = new JButton("Save");
        JTextField titleInput = new JTextField();
        JTextArea bodyInput = new JTextArea();
        JTextField cardTitleInput = new JTextField();
        JTextArea cardDescInput = new JTextArea();

        new EditLearningCardPageGUI()
                .createAndShowGUI(component, saveButton, titleInput, cardTitleInput, cardDescInput, bodyInput)
                .fillGUI(titleInput, cardTitleInput, cardDescInput, bodyInput, editPageTitle, editCardTitle,
                        editCardDesc, editPageBody)
                .createBehaviour(saveButton, titleInput, bodyInput, cardTitleInput, cardDescInput, editPage.uid);
    }

    private class EditLearningCardPageGUI {
        private EditLearningCardPageGUI() {}

        private EditLearningCardPageGUI createAndShowGUI(JPanel component, JButton saveButton,
                JTextField titleInput, JTextField cardTitleInput, JTextArea cardDescInput, JTextArea bodyInput) {

            AddEditLearningPageGenericGUI.createAndShowGUI(component, saveButton, titleInput, cardTitleInput,
                    cardDescInput, bodyInput);

            return this;
        }

        private EditLearningCardPageGUI fillGUI(JTextField titleInput, JTextField cardTitleInput,
                JTextArea cardDescInput,
                JTextArea bodyInput, String editPageTitle, String editCardTitle, String editCardDesc,
                String editPageBody) {
            titleInput.setText(editPageTitle);
            cardTitleInput.setText(editCardTitle);
            cardDescInput.setText(editCardDesc);
            bodyInput.setText(editPageBody);

            return this;
        }

        private void createBehaviour(JButton saveButton, JTextField titleInput, JTextArea bodyInput,
                JTextField cardTitleInput, JTextArea cardDescInput, String uid) {
            saveButton.addActionListener((e) -> {
                String newTitle = titleInput.getText();
                String newBody = bodyInput.getText();
                String newCardTitle = cardTitleInput.getText();
                String newCardDesc = cardDescInput.getText();

                LearningCardPage page = new LearningCardPage(newTitle, newBody, newTitle, newBody, "", uid);
                App.closeSelectedTab();
                App.addTab(newTitle, null, page.component, "", true);

                JSONArray jsonArray = null;
                try {
                    jsonArray = Utils.parseJSONArrayFromFile("src/data/LearningPages.json");
                } catch (IOException io) {
                    System.out.println("Error reading file: " + e);
                    io.printStackTrace();
                }

                JSONArray editedJSONArray = editJSON(jsonArray, uid, newTitle, newBody, newCardTitle, newCardDesc);

                try {
                    Utils.writeJSONArrayToFile("src/data/LearningPages.json", editedJSONArray);
                } catch (IOException e1) {
                    System.out.println("Error writing to file: " + e);
                    e1.printStackTrace();
                } finally {
                    SwingUtilities.invokeLater(() -> {
                        App.component.setComponentAt(0, new LearningCardsListPage().component);
                    });
                }
            });
        }

        public JSONArray editJSON(JSONArray jsonArray, String targetUid, String newTitle, String newBody,
                String newCardTitle,
                String newCardDesc) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                if (obj.has("uid") && obj.getString("uid").equals(targetUid)) {
                    obj.put("title", newTitle);
                    obj.put("body", newBody);
                    obj.getJSONObject("card").put("title", newCardTitle);
                    obj.getJSONObject("card").put("desc", newCardDesc);
                    jsonArray.put(i, obj);
                    break;
                }
            }

            return jsonArray;
        }
    }
}
