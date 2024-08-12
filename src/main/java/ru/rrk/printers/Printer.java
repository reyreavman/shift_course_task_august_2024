package ru.rrk.printers;

import ru.rrk.classifiers.DataType;

public interface Printer {
    void printLn(String string);

    void printStatWithType(DataType dataType, String stat, String lineFeed);
}
