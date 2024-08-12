package ru.rrk.args.validators;


import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.util.regex.Pattern;

/**
 * Валидатор обрабатывает префиксы имен файлов.
 */
public class PrefixValidator implements IParameterValidator {
    private final static Pattern PREFIX_PATTERN = Pattern.compile("^[^-][a-zA-Zа-яА-Я0-9_\\-]+");

    @Override
    public void validate(String name, String value) throws ParameterException {
        if (value.isEmpty() || !PREFIX_PATTERN.matcher(value).find()) {
            System.out.printf("Parameter %s provides a non-valid value\n", name);
            System.exit(100);
        }

    }
}
