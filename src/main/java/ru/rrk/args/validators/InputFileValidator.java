package ru.rrk.args.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

/**
 * Валидатор обрабатывает входные файлы.
 */
public class InputFileValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        File file = new File(value);
        String message = null;

        if (!file.exists()) message = "File with path %s does not exists.".formatted(file.getPath());
        else if (!file.isFile()) message = "Path %s passed is not a file.".formatted(file.getPath());
        else if (!file.canRead()) message = "File %s is unreadable.".formatted(file.getPath());

        if (message != null) {
            System.out.println(message);
            System.exit(100);
        }
    }
}
