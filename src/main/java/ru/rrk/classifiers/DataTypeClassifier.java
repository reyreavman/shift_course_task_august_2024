package ru.rrk.classifiers;

import lombok.RequiredArgsConstructor;

/**
 * Класс, классифицирующий строки.
 */
@RequiredArgsConstructor
public class DataTypeClassifier {
    private final String intPattern;
    private final String floatPattern;

    /**
     *
     * @param string строка для классификации.
     * @return тип данной строки: STRING, FLOAT, INTEGER или VOID
     */
    public DataType classify(String string) {
        if (string.isEmpty() || string.isBlank()) return DataType.VOID;
        if (string.matches(intPattern)) return DataType.INTEGER;
        if (string.matches(floatPattern)) return DataType.FLOAT;
        return DataType.STRING;
    }
}
