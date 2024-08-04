package ru.rrk.args.validators;



import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.util.regex.Pattern;

/**
 * Валидатор обрабатывает префиксы имен файлов.
 */
public class PrefixValidator implements IParameterValidator {
    private final static Pattern PREFIX_PATTERN = Pattern.compile("^[a-zA-Zа-яА-Я0-9_\\-]+");
    @Override
    public void validate(String name, String value) throws ParameterException {
        if (value.isEmpty() || !PREFIX_PATTERN.matcher(value).find()) throw new ParameterException(
                "Parameter %s provides a non-valid value".formatted(name)
        );
    }
}
