package ru.rrk.stats.strategy.fullForm;

import lombok.Getter;
import lombok.ToString;
import ru.rrk.stats.strategy.StatStrategy;

@Getter
public class FullFormFloatStatStrategy implements StatStrategy {
    private float count = 0;
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
         return "Floats stats:\n" +
                 "\tcount = %s\n".formatted(count) +
                 "\tmin = %s, max = %s\n".formatted(min, max) +
                 "\tsum = %s\n".formatted(sum) +
                 "\tmean = %s\n".formatted(mean);
    }
}
