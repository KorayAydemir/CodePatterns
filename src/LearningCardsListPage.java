package src;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.JSONObject;

public class LearningCardsListPage {
    JPanel page = new JPanel();

    public LearningCardsListPage() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        page.setLayout(new GridBagLayout());

        final JLabel pageTitle = new JLabel("Learning Cards");
        pageTitle.setFont(new Font("Serif", Font.PLAIN, 30));
        pageTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        var cTitle = new GridBagConstraints();
        cTitle.anchor = GridBagConstraints.FIRST_LINE_START;
        cTitle.gridx = 0;
        cTitle.gridy = 0;
        cTitle.weighty = 0;

        final JLabel pageDesc = new JLabel("Here, you can view your learning cards, or press the book button to edit them.");
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

        page.add(pageTitle, cTitle);
        page.add(pageDesc, cPageDesc);
        page.add(learningCards.cardsContainer, cLearningCards);
    }
}

class LearningCards {
    JPanel cardsContainer = new JPanel(new GridLayout(0, 5, 35, 30));

    public LearningCards() {
        //var card = new LearningCard("title", "tooltip tooltip tooltip tooltip tooltip tooltip ", "contentPreview");
        //var card2 = new LearningCard("title3", "tooltip tooltip tooltip ", "contentPreview");
        //var card3 = new LearningCard("title4", "tooltip tooltip tooltip tooltip tooltip tooltip ", "contentPreview");
        //var card4 = new LearningCard("title5", "tooltip tooltip tooltip tooltip tooltip tooltip ", "contentPreview");
        //var card5 = new LearningCard("title6", "tooltip tooltip tooltip tooltip tooltip tooltip ", "contentPreview");
        //var card6 = new LearningCard("title7", "tooltip tooltip tooltip tooltip tooltip tooltip ", "contentPreview");
        //var card7 = new LearningCard("title8", "tooltip tooltip tooltip tooltip tooltip tooltip ", "contentPreview");
        //var card8 = new LearningCard("title9", "tooltip tooltip tooltip tooltip tooltip tooltip ", "contentPreview");

        //cardsContainer.add(card.component);
        //cardsContainer.add(card2.component);
        //cardsContainer.add(card3.component);
        //cardsContainer.add(card4.component);
        //cardsContainer.add(card5.component);
        //cardsContainer.add(card6.component);
        //cardsContainer.add(card7.component);
        //cardsContainer.add(card8.component);
    }

}

class LearningCardsTab extends TabPage {
    public static final String name = "Learning Cards";
    public static final Icon icon = null;

    LearningCardsTab() {
        super(new LearningCardsListPage().page, name, icon);
    }
}
