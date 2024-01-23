package src;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class EntryFrame {
    private static final JFrame frame = new JFrame("Hello, World!");

    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> {
            new EntryFrame();
        });
    }

    public EntryFrame() {
        createAndShowGUI(frame);
    }

    private void createAndShowGUI(JFrame frame) {
        frame.setSize(new Dimension(640, 480));
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentsToPane(frame);

        frame.setVisible(true);
    }

    private void addComponentsToPane(JFrame frame) {
        JPanel buttons = createButtons();
        frame.getContentPane().add(buttons);
    }

    private JPanel createButtons() {
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.Y_AXIS));
        buttonsContainer.setPreferredSize(new Dimension(200, 200));

        final JButton startButton = new NavigationButton("Start Learning", new LearningCardsTab()).getButton();

        buttonsContainer.add(startButton);
        buttonsContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        return buttonsContainer;
    }

    private class NavigationButton {
        private String buttonText;
        private TabPage page;

        public NavigationButton(String buttonText, TabPage page) {
            this.buttonText = buttonText;
            this.page = page;
        }

        private static JButton createButton(String text, ImageIcon icon, ActionListener listener) {
            JButton button = new JButton(text);
            button.setMaximumSize(new Dimension(240, 80));

            button.addActionListener(listener);

            return button;
        }

        public JButton getButton() {
            return createButton(buttonText, null, (e) -> {
                App.getInstance().addTab(page);

                frame.dispose();
            });
        }

    }
}
