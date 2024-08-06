package ru.rrk.writers.factory;

import ru.rrk.writers.Writer;
import ru.rrk.writers.DefaultWriter;

import java.io.BufferedWriter;

public class WriterFactory {
    public Writer<String> createWriter(BufferedWriter writer) {
        return new DefaultWriter(writer);
    }
}
