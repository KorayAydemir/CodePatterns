package src;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

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

class EditLearningCardPage implements TabPage {
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
                .createAndShowGUI(editPageTitle, editPageBody, editCardTitle, editCardDesc, saveButton,
                        titleInput, cardTitleInput, cardDescInput,
                        bodyInput)
                .createBehaviour(saveButton, titleInput, bodyInput, cardTitleInput, cardDescInput, editPage.uid);
    }

    private class EditLearningCardPageGUI {
        public EditLearningCardPageGUI() {
        }

        private EditLearningCardPageGUI createAndShowGUI(String editPageTitle, String editPageBody,
                String editCardDesc, String editCardTitle, JButton saveButton,

                JTextField titleInput, JTextField cardTitleInput, JTextArea cardDescInput, JTextArea bodyInput) {

            titleInput.setText(editPageTitle);
            bodyInput.setText(editPageBody);
            cardTitleInput.setText(editCardTitle);
            cardDescInput.setText(editCardDesc);

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

            component.add(new JLabel("Edit Title:"), cTitleLabel);
            component.add(titleInput, cTitleInput);
            component.add(new JLabel("Edit Card Title:"), cCardTitleLabel);
            component.add(cardTitleInput, cCardTitleInput);
            component.add(new JLabel("Edit Card Description:"), cCardDescLabel);
            component.add(new JScrollPane(cardDescInput), cCardDescInput);
            component.add(new JLabel("Edit Body:"), cBodyLabel);
            component.add(new JScrollPane(bodyInput), cBodyInput);
            component.add(saveButton);
            return this;
        }

        private void createBehaviour(JButton saveButton, JTextField titleInput, JTextArea bodyInput, JTextField cardTitleInput, JTextArea cardDescInput, String uid) {
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
                    jsonArray = parseJSONArrayFromFile("src/data/LearningPages.json");
                } catch (IOException io) {
                    System.out.println("Error reading file: " + e);
                    io.printStackTrace();
                }

                JSONArray editedJSONArray = editJSON(jsonArray, uid, newTitle, newBody, newCardTitle, newCardDesc);

                try {
                    writeJSONArrayToFile("src/data/LearningPages.json", editedJSONArray);
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

        private JSONArray parseJSONArrayFromFile(String filePath) throws IOException {
            FileReader fileReader = new FileReader(filePath);
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            return new JSONArray(jsonTokener);

        }

        private void writeJSONArrayToFile(String filePath, JSONArray jsonArray) throws IOException {
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(jsonArray.toString(4)); // '4' is the number of spaces to use for indentation
            }
        }

        public JSONArray editJSON(JSONArray jsonArray, String targetUid, String newTitle, String newBody, String newCardTitle,
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
