package src;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import src.pages.LearningCardsListPage;

public class EntryFrame {
    private static final JFrame frame = new JFrame("CodePatterns");

    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> {
            new EntryFrame();
        });
    }

    public EntryFrame() {
        final JButton startButton = new JButton("Start Learning");

        new EntryFrameGUI()
                .createAndShowGUI(frame, startButton)
                .createBehaviour(startButton);
    }

    private class EntryFrameGUI {
        public EntryFrameGUI() {
        }

        private EntryFrameGUI createAndShowGUI(JFrame frame, JButton startButton) {
            styleFrame(frame);
            styleStartButton(startButton);

            JPanel buttonContainer = new JPanel();
            styleButtonsContainer(buttonContainer, startButton);
            frame.getContentPane().add(buttonContainer);

            frame.setVisible(true);

            return this;
        }

        private void createBehaviour(JButton startButton) {
            startButton.addActionListener((e) -> {
                App.getInstance();
                App.addTab("Learning Cards", null, new LearningCardsListPage().component, "0", true);
                frame.dispose();
            });
        }

        private void styleFrame(JFrame frame) {
            frame.setSize(new Dimension(640, 480));
            frame.setLayout(new GridBagLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        private void styleStartButton(JButton startButton) {
            startButton.setMaximumSize(new Dimension(240, 80));
        }

        private void styleButtonsContainer(JPanel buttonsContainer, JButton startButton) {
            buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.Y_AXIS));
            buttonsContainer.setPreferredSize(new Dimension(200, 200));

            buttonsContainer.add(startButton);
            buttonsContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }
}
