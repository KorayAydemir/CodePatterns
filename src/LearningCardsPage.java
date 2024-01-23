package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LearningCardsPage {
    JPanel page = new JPanel();

    public LearningCardsPage() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        LearningCards learningCards = new LearningCards();
        page.add(learningCards.cardsContainer);
    }
}

class LearningCards {
    JPanel cardsContainer = new JPanel();

    public LearningCards() {
        Card card = new Card("title", "tooltip tooltip tooltip tooltip tooltip tooltip ", "contentPreview");

        cardsContainer.add(card.component);
    }

    private class Card {
        final JButton editButton = new CardButton("HB").getButton();
        final JPanel component = new JPanel(new GridBagLayout());

        public Card(String title, String desc, String contentPreview) {
            JLabel titleLabel = new JLabel();
            titleLabel.setText("<html><h2>" + title + "</h2></html>");
            JLabel descText = new JLabel();
            descText.setText("<html>" + desc + "</html>");

            setUpCard();

            GridBagConstraints cTitleLabel = setUpTitleConstraints(titleLabel);
            GridBagConstraints cDesc = setUpDescConstraints(descText);
            GridBagConstraints cEditButton = setUpEditButtonConstraints();
            editButton.setMinimumSize(new Dimension(25, 25));

            component.add(titleLabel, cTitleLabel);
            component.add(descText, cDesc);
            component.add(editButton, cEditButton);
        }

        private GridBagConstraints setUpTitleConstraints(JLabel titleLabel) {
            GridBagConstraints c = new GridBagConstraints();

            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.FIRST_LINE_START;
            c.weightx = 0.9;
            c.gridx = 0;
            c.gridy = 0;
            return c;
        }

        private GridBagConstraints setUpDescConstraints(JLabel descText) {
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.FIRST_LINE_START;
            c.weightx = 1;
            c.weighty = 0.9;
            c.gridwidth = 2;
            c.gridx = 0;
            c.gridy = 1;
            return c;
        }

        private GridBagConstraints setUpEditButtonConstraints() {
            GridBagConstraints c = new GridBagConstraints();
            c.anchor = GridBagConstraints.FIRST_LINE_END;
            c.gridy = 0;
            c.gridheight = 1;

            return c;
        }

        private void setUpCard() {
            component.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.black),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            component.setPreferredSize(new Dimension(300, 130));

            component.addMouseListener(new HoverMouseListener());
        }

        public class HoverMouseListener extends MouseAdapter {
            @Override
            public void mouseEntered(MouseEvent e) {
                editButton.setVisible(true);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                openContentInNewTab();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (mouseIsOnButton(e, editButton))
                    return;

                editButton.setVisible(false);
            }

            private boolean mouseIsOnButton(MouseEvent e, JButton button) {
                Point p = e.getPoint();
                return button.getBounds().contains(p) ;
            }
        }

        void openContentInNewTab() {
            App.addTab(new LearningCardsTab());
            // TODO IMPLEMENT
        }
    }

    class CardButton {
        JButton button = new JButton();

        public CardButton(String text) {
            button.setMaximumSize(new Dimension(20, 20));
            button.setVisible(false);
        }

        public JButton getButton() {
            return button;
        }
    }
}

class LearningCardsTab extends TabPage {
    public static final String name = "Learning Cards";
    public static final Icon icon = null;
    public static final String tooltip = "Manage your learning cards";

    LearningCardsTab() {
        super(new LearningCardsPage().page, name, icon, tooltip);
    }
}
