package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String print = new String();
    private List<String> history;

    public SimpleController() {
        this.history = new LinkedList<>();
    }

    @Override
    public void setNextPrint(final String nextPrint) {
        Objects.requireNonNull(nextPrint, "Cannot set a null string to print");
        this.print = nextPrint;
    }

    @Override
    public String getNextPrint() {
        return this.print;
    }

    @Override
    public List<String> getHistory() {
        return List.copyOf(this.history);
    }

    @Override
    public void printCurrentString() {
        if (!this.print.isEmpty()) {
            System.out.println(this.print);
            history.add(this.print);
        } else {
            throw new IllegalStateException("No string is set to print");
        }
    }

}
