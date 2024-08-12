package ru.rrk.writers;

import ru.rrk.writers.writer.DefaultWriter;
import ru.rrk.writers.writer.Writer;

import java.io.BufferedWriter;

public class WriterFactory {
    public Writer<String> createWriter(BufferedWriter writer) {
        return new DefaultWriter(writer);
    }
}
