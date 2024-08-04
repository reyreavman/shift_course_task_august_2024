package ru.rrk.classifiers;

import lombok.RequiredArgsConstructor;

/**
 * Класс, классифицирующий строки в файлах.
 */
@RequiredArgsConstructor
public class DataTypeClassifier {
    private final String intPattern;
    private final String floatPattern;

    public DataType classify(String string) {
        if (string.isEmpty() || string.isBlank()) return DataType.VOID;
        if (string.matches(intPattern)) return DataType.INTEGER;
        if (string.matches(floatPattern)) return DataType.FLOAT;
        return DataType.STRING;
    }
}
