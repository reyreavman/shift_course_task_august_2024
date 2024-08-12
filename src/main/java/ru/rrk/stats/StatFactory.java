package ru.rrk.stats;

import ru.rrk.classifiers.DataType;
import ru.rrk.stats.strategy.StatStrategy;
import ru.rrk.stats.strategy.fullForm.FullFormFloatStatStrategy;
import ru.rrk.stats.strategy.fullForm.FullFormLongStatStrategy;
import ru.rrk.stats.strategy.fullForm.FullFormStringStatStrategy;
import ru.rrk.stats.strategy.shortForm.ShortFormStatStrategy;

public class StatFactory {
    public StatStrategy create() {
        return new ShortFormStatStrategy();
    }

    public StatStrategy create(DataType type) {
        return switch (type) {
            case INTEGER -> new FullFormLongStatStrategy();
            case FLOAT -> new FullFormFloatStatStrategy();
            case STRING -> new FullFormStringStatStrategy();
            default -> null;
        };
    }
}
