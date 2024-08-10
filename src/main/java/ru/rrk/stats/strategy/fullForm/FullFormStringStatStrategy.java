package ru.rrk.stats.strategy.fullForm;

import lombok.Getter;
import lombok.ToString;
import ru.rrk.stats.strategy.StatStrategy;

@Getter
public class FullFormStringStatStrategy implements StatStrategy {
    private int count = 0;
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = Integer.MIN_VALUE;

    @Override
    public void addData(String data) {
        updateCount();
        checkMinLength(data.length());
        checkMaxLength(data.length());
    }

    private void updateCount() {
        count++;
    }

    private void checkMinLength(int length) {
        if (length < minLength) minLength = length;
    }

    private void checkMaxLength(int length) {
        if (length > minLength) maxLength = length;
    }

    @Override
    public String toString() {
        return "String stats:\n" +
                "\tcount = %s\n".formatted(count) +
                "\tmin = %s, max = %s\n".formatted(minLength, maxLength);
    }
}
