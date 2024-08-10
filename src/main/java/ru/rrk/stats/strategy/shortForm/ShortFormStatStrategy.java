package ru.rrk.stats.strategy.shortForm;

import lombok.Getter;
import lombok.ToString;
import ru.rrk.stats.strategy.StatStrategy;

@Getter
public class ShortFormStatStrategy implements StatStrategy {
    private int count = 0;

    @Override
    public void addData(final String data) {
        count++;
    }

    @Override
    public String toString() {
        return "ShortFormStatStrategy{" +
                "count=" + count +
                '}';
    }
}
