package it.unibo.mvc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String DEFAULT_PATH = System.getProperty("user.home") + File.separator;
    private static final String DEFAULT_NAME = "output.txt";

    private File currentFile;

    public Controller() { 
        this.currentFile = new File(Controller.DEFAULT_PATH, Controller.DEFAULT_NAME);
    }

    public void setFile(final File file) {
        this.currentFile = file;
    }

    public File getFile() {
        return this.currentFile;
    }

    public void writeFile(final String content) throws IOException {
        try (final BufferedWriter fw = new BufferedWriter(new FileWriter(currentFile))) {
            fw.write(content);
        } catch (IOException e) {
            System.out.println("Error when writing on " + this.currentFile.getAbsolutePath());
            e.printStackTrace();
        }
    }
}
