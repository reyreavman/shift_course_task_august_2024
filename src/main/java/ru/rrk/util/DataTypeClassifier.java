package ru.rrk.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DataTypeClassifier {
    private final String intPattern;
    private final String floatPattern;

    public DataType classify(String string) {
        if (string == null || string.isBlank()) return DataType.VOID;
        if (string.matches(intPattern)) return DataType.INTEGER;
        if (string.matches(floatPattern)) return DataType.FLOAT;
        return DataType.STRING;
    }
}
