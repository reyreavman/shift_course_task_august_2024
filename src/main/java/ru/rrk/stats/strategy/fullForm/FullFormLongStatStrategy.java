package ru.rrk.stats.strategy.fullForm;

import lombok.Getter;
import ru.rrk.stats.strategy.StatStrategy;

/**
 * Класс, собирающий полную статистику для целых чисел.
 */
@Getter
public class FullFormLongStatStrategy implements StatStrategy {
    private long count = 0;
    private long min = Long.MAX_VALUE;
    private long max = Long.MIN_VALUE;
    private long sum = 0;
    private long mean = 0;

    @Override
    public void addData(String data) {
        long number = Long.parseLong(data);
        updateCount();
        checkMin(number);
        checkMax(number);
        updateSum(number);
        updateMean();
    }

    private void updateCount() {
        count++;
    }

    private void checkMin(long number) {
        if (number < min) min = number;
    }

    private void checkMax(long number) {
        if (number > max) max = number;
    }

    private void updateSum(long number) {
        sum += number;
    }

    private void updateMean() {
        mean = sum / count;
    }

    @Override
    public String toString() {
        return "(count = %d, min = %d, max = %d, sum = %d, mean = %d)".formatted(count, min, max, sum, mean);
    }
}
