package ru.rrk.validator;

import com.beust.jcommander.ParameterException;
import org.junit.jupiter.api.Test;
import ru.rrk.args.validators.TwoFormOfStatValidator;

import java.util.Map;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TwoFormOfStatValidatorTest {
    TwoFormOfStatValidator validator = new TwoFormOfStatValidator();

    @Test
    public void twoFormOfStatPassed_returnsMapWithShortKeyEqualsFalse() {
        Map<String, Object> providedMap = Map.ofEntries(
                Map.entry("-a", TRUE),
                Map.entry("-s", TRUE),
                Map.entry("-f", TRUE)
        );

        assertThrows(
                ParameterException.class,
                () -> validator.validate(providedMap),
                "Two statistics flags passed, full statistics will be displayed"
        );
    }

    @Test
    public void oneFormOfStatPassed_returnsOK() {
        Map<String, Object> providedMap = Map.ofEntries(
                Map.entry("-a", TRUE),
                Map.entry("-s", TRUE)
        );

        assertDoesNotThrow(() -> validator.validate(providedMap));
    }
}
