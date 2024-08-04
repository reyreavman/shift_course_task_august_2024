package ru.rrk.validator;

import com.beust.jcommander.ParameterException;
import org.junit.jupiter.api.Test;
import ru.rrk.args.validators.PrefixValidator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrefixValidatorTest {
    PrefixValidator validator = new PrefixValidator();
    String prefixFlagName = "-p";

    @Test
    public void provideEmptyString_throwsParameterException() {
        String input = "";

        assertThrows(
                ParameterException.class,
                () -> validator.validate(prefixFlagName, input),
                "Parameter %s provides a non-valid value"
        );
    }

    @Test
    public void provideNonMatchingString_throwsParameterException() {
        String input = "!!!!adasdasdDSADSADSA";

        assertThrows(
                ParameterException.class,
                () -> validator.validate(prefixFlagName, input),
                "Parameter %s provides a non-valid value"
        );
    }

    @Test
    public void provideMatchingString_returnsOK() {
        String input = "valid_prefix";

        assertDoesNotThrow(() -> validator.validate(prefixFlagName, input));
    }
}
