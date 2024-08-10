package ru.rrk.stats.strategy.shortForm;

import lombok.Getter;
import ru.rrk.stats.strategy.StatStrategy;

/**
 * Класс, собирающий краткую статистику.
 */
@Getter
public class ShortFormStatStrategy implements StatStrategy {
    private int count = 0;

    @Override
    public void addData(final String data) {
        count++;
    }

    @Override
    public String toString() {
        return "(count = %d)".formatted(count);
    }
}
