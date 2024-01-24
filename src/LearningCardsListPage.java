package src;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.JSONArray;
import org.json.JSONObject;

public class LearningCardsListPage implements TabPage {
    JPanel component = new JPanel();

    public LearningCardsListPage() {
        new LearningCardsListPageGUI()
                .createAndShowGUI();
    }

    private class LearningCardsListPageGUI {
        private void createAndShowGUI() {
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

            LearningCards learningCards = new LearningCards();
            var cLearningCards = new GridBagConstraints();
            cLearningCards.anchor = GridBagConstraints.FIRST_LINE_START;
            cLearningCards.gridx = 0;
            cLearningCards.gridy = 2;
            cLearningCards.weighty = 1;

            component.add(pageTitle, cTitle);
            component.add(pageDesc, cPageDesc);
            component.add(learningCards.cardsContainer, cLearningCards);
        }

    }

}

class LearningCards {
    JPanel cardsContainer = new JPanel(new GridLayout(0, 5, 35, 30));

    public LearningCards() {
        try {
            JSONArray jsonArray = parseJSONArrayFromFile("src/data/LearningPages.json");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject learningPageObj = jsonArray.getJSONObject(i);
                JSONObject cardObj = learningPageObj.getJSONObject("card");

                String pageUid = learningPageObj.getString("uid");
                String pageTitle = learningPageObj.getString("title");
                String pageBody = learningPageObj.getString("body");

                String cardTitle = cardObj.getString("title");
                String cardDesc = cardObj.getString("desc");
                String cardTooltip = cardObj.getString("tooltip");

                LearningCardPage learningCardPage = new LearningCardPage(pageTitle, pageBody, cardTitle, cardDesc,
                        cardTooltip, pageUid);
                LearningCard card = new LearningCard(cardTitle, cardDesc, cardTooltip, learningCardPage);

                cardsContainer.add(card.component);
            }
        } catch (Exception e) {
            System.out.println(e);
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
