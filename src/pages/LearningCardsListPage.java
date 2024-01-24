package src.pages;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.JSONArray;
import org.json.JSONObject;

import src.App;
import src.components.LearningCard;

public class LearningCardsListPage implements TabPage {
    public JPanel component = new JPanel();

    public LearningCardsListPage() {
        var addCardButton = new JButton("Add Card");

        new LearningCardsListPageGUI()
                .createAndShowGUI(addCardButton)
                .createBehaviour(addCardButton);
    }

    private class LearningCardsListPageGUI {
        private LearningCardsListPageGUI createAndShowGUI(JButton addCardButton) {
            component.setLayout(new GridBagLayout());

            final JLabel pageTitle = new JLabel("Learning Cards");
            pageTitle.setFont(new Font("Serif", Font.PLAIN, 30));
            pageTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
            var cTitle = new GridBagConstraints();
            cTitle.anchor = GridBagConstraints.FIRST_LINE_START;
            cTitle.gridx = 0;
            cTitle.gridy = 0;
            cTitle.weighty = 0;

            final JLabel pageDesc = new JLabel(
                    "Here, you can view your learning cards, or press the book button to edit them.");
            pageDesc.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
            var cPageDesc = new GridBagConstraints();
            cPageDesc.anchor = GridBagConstraints.FIRST_LINE_START;
            cPageDesc.gridy = 1;
            cPageDesc.weighty = 0;

            var cLearningCards = new GridBagConstraints();
            cLearningCards.anchor = GridBagConstraints.FIRST_LINE_START;
            cLearningCards.gridx = 0;
            cLearningCards.gridy = 2;
            cLearningCards.weighty = 1;

            var cAddCardButton = new GridBagConstraints();
            cAddCardButton.anchor = GridBagConstraints.LAST_LINE_END;

            component.add(pageTitle, cTitle);
            component.add(pageDesc, cPageDesc);
            var learningCards = new LearningCards();
            component.add(learningCards.cardsContainer, cLearningCards);
            component.add(addCardButton, cAddCardButton);

            return this;
        }

        private void createBehaviour(JButton addCardButton) {
            addCardButton.addActionListener(e -> {
                App.addTab("Add Card", null, new AddNewLearningCardPage().component, "add-learning-card-page",
                        true);
            });
        }
    }

}

class LearningCards {
    JPanel cardsContainer;

    public LearningCards() {
        this.cardsContainer = new JPanel(new GridLayout(0, 5, 35, 30));

        JSONArray jsonArray = parseJSONArrayFromFile("src/data/LearningPages.json");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject learningPageObj = jsonArray.getJSONObject(i);
            JSONObject cardObj = learningPageObj.getJSONObject("card");

            String uid = learningPageObj.getString("uid");
            String pageTitle = learningPageObj.getString("title");
            String pageBody = learningPageObj.getString("body");

            String cardTitle = cardObj.getString("title");
            String cardDesc = cardObj.getString("desc");
            String cardTooltip = cardObj.getString("tooltip");

            LearningCardsGUI.createGUI(cardsContainer, pageTitle, pageBody, cardTitle, cardDesc, cardTooltip, uid);
        }
    }

    private class LearningCardsGUI {
        public static void createGUI(JPanel cardsContainer, String pageTitle, String pageBody, String cardTitle,
                String cardDesc,
                String cardTooltip, String uid) {

            var learningCardPage = new LearningCardPage(pageTitle, pageBody, cardTitle, cardDesc,
                    cardTooltip, uid);
            var card = new LearningCard(cardTitle, cardDesc, cardTooltip, learningCardPage);

            cardsContainer.add(card.component);
        }
    }

    private JSONArray parseJSONArrayFromFile(String path) {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(path)));
            return new JSONArray(jsonString);
        } catch (IOException io) {
            System.out.println("File read error: " + io);
            System.exit(1);
            return null;
        } catch (Exception e) {
            System.out.println("JSON parse error: " + e);
            System.exit(1);
            return null;
        }
    }
}
