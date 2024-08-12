package ru.rrk.printers;

import lombok.RequiredArgsConstructor;
import ru.rrk.classifiers.DataType;

import java.io.PrintStream;

@RequiredArgsConstructor
public class DefaultPrinter implements Printer {
    private final PrintStream printStream;

    @Override
    public void printLn(String string) {
        printStream.println(string);

    }

    /**
     * Выводит в переданный PrintStream данные статистики.
     * @param dataType тип статистики.
     * @param stat строковое представление статистики.
     * @param printSeparator разделитель
     */
    @Override
    public void printStatWithType(DataType dataType, String stat, String printSeparator) {
        printStream.println(
                dataType.toString()
                        .concat(": ")
                        .concat(stat)
                        .concat("\n")
                        .concat(printSeparator)

        );
    }
}
