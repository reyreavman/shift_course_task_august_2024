package ru.rrk.stats.strategy.fullForm;

import lombok.Getter;
import ru.rrk.stats.strategy.StatStrategy;

/**
 * Класс, собирающий полную статистику для вещественных чисел.
 */
@Getter
public class FullFormFloatStatStrategy implements StatStrategy {
    private int count = 0;
    private float min = Float.MAX_VALUE;
    private float max = Float.MIN_VALUE;
    private float sum = 0;
    private float mean = 0;

    @Override
    public void addData(String data) {
        float number = Float.parseFloat(data);
        updateCount();
        checkMin(number);
        checkMax(number);
        updateSum(number);
        updateMean();
    }

    private void updateCount() {
        count++;
    }

    private void checkMin(float number) {
        if (number < min) min = number;
    }

    private void checkMax(float number) {
        if (number > max) max = number;
    }

    private void updateSum(float number) {
        sum += number;
    }

    private void updateMean() {
        mean = sum / count;
    }

    @Override
    public String toString() {
        return "(count = %d, min = %f, max = %f, sum = %f, mean = %f)".formatted(count, min, max, sum, mean);
    }
}
