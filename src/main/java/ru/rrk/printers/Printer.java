package ru.rrk.printers;

public interface Printer {
    void print(String string);

    void print(String string, String lineFeed);
}
