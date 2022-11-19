package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final int PROPORTION = 5;

    private final JFrame frame = new JFrame();
    private final Controller controller = new Controller();

    /**
     * Crates a new SimpleGUIWithFileChooser.
     */
    public SimpleGUIWithFileChooser() {
        frame.setTitle("My second Java graphical interface");
        final JPanel up = new JPanel();
        final JPanel bottom = new JPanel();
        up.setLayout(new BorderLayout());
        bottom.setLayout(new BorderLayout());
        final JTextField filePath = new JTextField(controller.getFile().getPath());
        filePath.setEditable(false);
        final JButton browse = new JButton("Browse...");
        final JTextArea text = new JTextArea();
        final JButton save = new JButton("Save");
        up.add(filePath);
        up.add(browse, BorderLayout.LINE_END);
        bottom.add(up, BorderLayout.NORTH);
        bottom.add(text, BorderLayout.CENTER);
        bottom.add(save, BorderLayout.SOUTH);
        frame.setContentPane(bottom);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                try {
                    controller.writeFile(text.getText());
                } catch (IOException e) {
                    e.printStackTrace(); // NOPMD: allowed as this is just an exercise
                }
            }
        });

        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final JFileChooser chooseFile = new JFileChooser("Select a new file");
                switch (chooseFile.showSaveDialog(frame)) {
                    case JFileChooser.APPROVE_OPTION:
                        controller.setFile(chooseFile.getSelectedFile());
                        filePath.setText(chooseFile.getSelectedFile().getPath());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, "An error has occurred");
                        break;
                }
            }
        });
    }

    /**
     * Displays the SimpleGUIWithFileChooser.
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
     * Starts the SimpleGUIWithFileChooser.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser().display();
    }
}
