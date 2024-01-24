package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LearningCard {
    final JPanel component = new JPanel(new GridBagLayout());
    final LearningCardPage tabToOpen;

    public LearningCard(String title, String desc, String tooltip, LearningCardPage tabToOpen) {
        this.tabToOpen = tabToOpen;

        JButton editButton = new JButton();
        new LearningCardGUI()
                .createGUI(component, title, desc, tooltip, editButton)
                .createBehaviour(component, editButton);
    }

    private class LearningCardGUI {
        LearningCardGUI() {
        }

        private LearningCardGUI createGUI(JPanel component, String title, String desc, String tooltip, JButton editButton) {
            styleButton(editButton);
            styleComponent(component);

            JLabel titleLabel = new JLabel();
            titleLabel.setText("<html><h2>" + title + "</h2></html>");

            JLabel descText = new JLabel();
            descText.setText("<html>" + desc + "</html>");

            GridBagConstraints cTitleLabel = createTitleConstraints(titleLabel);
            GridBagConstraints cDesc = createDescConstraints(descText);
            GridBagConstraints cEditButton = createEditButtonConstraints();

            component.add(titleLabel, cTitleLabel);
            component.add(descText, cDesc);
            component.add(editButton, cEditButton);

            return this;
        }

        private void createBehaviour(JPanel component, JButton editButton) {
            component.addMouseListener(new HoverMouseListener(editButton, tabToOpen));
            editButton.addActionListener((e) -> {
                App.getInstance().addTab(new EditLearningCardPage(tabToOpen));
            });
        }

        private GridBagConstraints createTitleConstraints(JLabel titleLabel) {
            GridBagConstraints c = new GridBagConstraints();

            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.FIRST_LINE_START;
            c.weightx = 0.9;
            c.gridx = 0;
            c.gridy = 0;
            return c;
        }

        private GridBagConstraints createDescConstraints(JLabel descText) {
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

        private GridBagConstraints createEditButtonConstraints() {
            GridBagConstraints c = new GridBagConstraints();
            c.anchor = GridBagConstraints.FIRST_LINE_END;
            c.gridy = 0;
            c.gridheight = 1;

            return c;
        }

        private void styleButton(JButton editButton) {
            editButton.setMinimumSize(new Dimension(25, 25));
            editButton.setVisible(false);
            editButton.setMargin(new Insets(0, 0, 0, 0));
            editButton.setContentAreaFilled(false);
            editButton.setBorder(null);

            try {
                Image img = ImageIO.read(new File("src/assets/book.224x256.png"));
                Image scaledImg = img.getScaledInstance(25, 25, Image.SCALE_REPLICATE);
                editButton.setIcon(new ImageIcon(scaledImg));
            } catch (Exception e) {
                System.out.println("Error reading image: " + e);
            }
        }

        private void styleComponent(JPanel component) {
            component.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.black),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            component.setPreferredSize(new Dimension(300, 130));
        }
    }

    public class HoverMouseListener extends MouseAdapter {
        private JButton editButton;
        private LearningCardPage tabToOpen;

        HoverMouseListener(JButton editButton, LearningCardPage tabToOpen) {
            this.editButton = editButton;
            this.tabToOpen = tabToOpen;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            editButton.setVisible(true);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            App.getInstance().addTab(tabToOpen);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (mouseIsOnButton(e, editButton))
                return;

            editButton.setVisible(false);
        }

        private boolean mouseIsOnButton(MouseEvent e, JButton button) {
            Point p = e.getPoint();
            return button.getBounds().contains(p);
        }
    }
}
