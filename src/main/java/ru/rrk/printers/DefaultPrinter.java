package ru.rrk.printers;

import lombok.RequiredArgsConstructor;

import java.io.PrintStream;

@RequiredArgsConstructor
public class DefaultPrinter implements Printer {
    private final PrintStream printStream;

    @Override
    public void print(String string) {
        printStream.println(string);

    }

    @Override
    public void print(String string, String lineFeed) {
        printStream.println(string);
        printStream.println(lineFeed);
    }
}
