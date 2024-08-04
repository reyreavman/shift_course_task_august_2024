package ru.rrk.writers;

import lombok.RequiredArgsConstructor;

import java.io.BufferedWriter;
import java.io.IOException;

@RequiredArgsConstructor
public class WriterImpl implements Writer<String> {
    private final BufferedWriter writer;

    @Override
    public void write(String value) {
        try {
            writer.write(value);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(100);
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(100);
        }
    }
}
