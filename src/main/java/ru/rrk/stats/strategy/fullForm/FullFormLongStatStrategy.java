package ru.rrk.stats.strategy.fullForm;

import lombok.Getter;
import ru.rrk.stats.strategy.StatStrategy;

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
        return "Integers stats:\n" +
                "\tcount = %s\n".formatted(count) +
                "\tmin = %s, max = %s\n".formatted(min, max) +
                "\tsum = %s\n".formatted(sum) +
                "\tmean = %s\n".formatted(mean);
    }
}
