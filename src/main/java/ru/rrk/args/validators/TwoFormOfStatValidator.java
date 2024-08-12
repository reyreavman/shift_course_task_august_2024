package ru.rrk.args.validators;

import com.beust.jcommander.IParametersValidator;
import com.beust.jcommander.ParameterException;

import java.util.Map;

import static java.lang.Boolean.TRUE;

/**
 * Валидатор обрабатывает случай, когда переданы два флага статистики. В таком случае флаг -s далее игнорируется, после выполнения выводится полная статистика.
 */
public class TwoFormOfStatValidator implements IParametersValidator {
    @Override
    public void validate(Map<String, Object> map) throws ParameterException {
        if (map.get("-s") == TRUE && map.get("-f") == TRUE) {
            System.out.println("Two statistics flags passed, please choose only one flag");
            System.exit(100);
        }
    }
}