package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    /**
     * Sets the next print that will be printed.
     * 
     * @param print
     */
    void setNextPrint(String print);

    /**
     * Returns the next string that will be printed.
     * 
     * @return print 
     */
    String getNextPrint();

    /**
     * Returns the history of all printed strings.
     * 
     * @return history 
     */
    List<String> getHistory();

    /**
     * Prints the current setted string.
     */
    void printCurrentString();
}
