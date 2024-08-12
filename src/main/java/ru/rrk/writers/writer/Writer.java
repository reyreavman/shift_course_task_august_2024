package ru.rrk.writers.writer;

public interface Writer<T> {
    void write(T value);

    void close();
}
