package src;

import java.awt.Container;
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

public class Main extends JFrame {
    public Main () {
        super("Hello, World!");
    }

    // maybe utilize builder pattern somewhere
    private static JButton createButton(String text, String tooltip, ImageIcon icon, ActionListener listener) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(240, 80));
        // startButton.putClientProperty("JComponent.sizeVariant", "mini");

        button.addActionListener(listener);

        if (tooltip != null) {
            button.setToolTipText(tooltip);
        }
        return button;
    }

    private void addComponentsToPane() {
        final Container pane = getContentPane();

        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.Y_AXIS));
        buttonsContainer.setPreferredSize(new Dimension(200, 200));

        ActionListener buttonAction = e -> {
            new Learn().createAndShowGUI();
            dispose();
        };

        final JButton startButton = createButton("Start learning", null, new ImageIcon("src/assets/book.224x256.png"), buttonAction);
        final JButton aboutButton = createButton("About", null, new ImageIcon("src/assets/book.224x256.png"), buttonAction);

        buttonsContainer.add(startButton);
        buttonsContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonsContainer.add(aboutButton);

        pane.add(buttonsContainer);
    }

    private void createAndShowGUI() {
        setSize(new Dimension(640, 480));
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentsToPane();

        setVisible(true);
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> {
            new Main().createAndShowGUI();
        });
    }
}
