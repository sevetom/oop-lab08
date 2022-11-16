package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    void setNextPrint(String print);

    String getNextPrint();

    List<String> getHistory();

    void printCurrentString();
}
