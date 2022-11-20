package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String print;
    private final List<String> history;

    /**
     * Crates a new SimpleController.
     */
    public SimpleController() {
        this.history = new LinkedList<>();
    }

    /**
     * Sets the next print that will be printed.
     * 
     * @param nextPrint
     */
    @Override
    public void setNextPrint(final String nextPrint) {
        Objects.requireNonNull(nextPrint, "Cannot set a null string to print");
        this.print = nextPrint;
    }

    /**
     * Returns the next string that will be printed.
     * 
     * @return print 
     */
    @Override
    public String getNextPrint() {
        return this.print;
    }

    /**
     * Returns the history of all printed strings.
     * 
     * @return history 
     */
    @Override
    public List<String> getHistory() {
        return List.copyOf(this.history);
    }

    /**
     * Prints the current setted string.
     */
    @Override
    public void printCurrentString() {
        if (this.print != null) {
            System.out.println(this.print); // NOPMD: allowed as this is just an exercise
            history.add(this.print);
        } else {
            throw new IllegalStateException("No string is set to print");
        }
    }

}
