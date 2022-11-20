package it.unibo.mvc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String DEFAULT_PATH = System.getProperty("user.home") + File.separator;
    private static final String DEFAULT_NAME = "output.txt";

    private File currentFile;

    /**
     * Crates a new controller.
     */
    public Controller() { 
        this.currentFile = new File(Controller.DEFAULT_PATH, Controller.DEFAULT_NAME);
    }

    /**
     * Sets the file that will be opened for writing.
     * @param file
     */
    public void setFile(final File file) {
        final File parent = file.getParentFile();
        if (parent.exists()) {
            this.currentFile = file;
        } else {
            throw new IllegalArgumentException("Cannot save in a non-existing folder.");
        }
    }

    /**
     * Returns the current setted file.
     * 
     * @return currentFile
     */
    public File getFile() {
        return this.currentFile;
    }

    /**
     * Returns the path of the current setted file.
     * 
     * @return currentPath
     */
    public String getPath() {
        return this.currentFile.getPath();
    }

    /**
     * Writes a String on a set file.
     * 
     * @param content
     */
    public void writeFile(final String content) throws IOException {
        try (BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(currentFile), "UTF-8"))) {
            fw.write(content);
        }
    }
}
