package ru.rrk.stats.strategy.fullForm;

import lombok.Getter;
import ru.rrk.stats.strategy.StatStrategy;

/**
 * Класс, собирающий полную статистику для строк.
 */
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
        return "(count = %d, minLength = %d, maxLength = %d)".formatted(count, minLength, maxLength);
    }
}
