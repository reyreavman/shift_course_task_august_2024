package ru.rrk.writers;

public interface Writer<T> {
    void write(T value);

    void close();
}
