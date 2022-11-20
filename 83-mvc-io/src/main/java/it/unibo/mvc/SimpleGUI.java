package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;

    private final JFrame frame = new JFrame();

    /**
     * Crates a new SimpleGUI.
     * 
     * @param controller
     */
    public SimpleGUI(final SimpleController controller) {
        frame.setTitle("SimpleGUI test");
        final JPanel panel = new JPanel();
        final JPanel buttons = new JPanel();
        panel.setLayout(new BorderLayout());
        final JTextField field = new JTextField();
        final JTextArea area = new JTextArea();
        final JButton print = new JButton("Print");
        final JButton history = new JButton("Show history");
        panel.add(field, BorderLayout.NORTH);
        panel.add(area, BorderLayout.CENTER);
        buttons.add(print);
        buttons.add(history);
        panel.add(buttons, BorderLayout.SOUTH);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                controller.setNextPrint(field.getText());
                controller.printCurrentString();
            }
        });

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                area.removeAll();
                for (final String printed : controller.getHistory()) {
                    area.append(printed + "\n");
                }
            }
        });
    }

    /**
     * Displays the SimpleGUI.
     */
    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * Starts the SimpleGUI.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }
}
