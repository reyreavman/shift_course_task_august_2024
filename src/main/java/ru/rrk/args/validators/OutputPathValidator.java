package ru.rrk.args.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Валидатор обрабатывает флаг -o. Рассмотрены 2 случая: переданная строка не является директорией, директория недоступна для записи
 */
public class OutputPathValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        String message = null;
        Path path = Paths.get(value);

        if (!Files.isDirectory(path))
            message = "Parameter %s contains a string that is not a directory.".formatted(name);
        else if (!Files.isWritable(path))
            message = "Parameter %s contains a directory that is not writable.".formatted(name);

        if (message != null) {
            System.out.println(message);
            System.exit(100);
        }
    }
}
