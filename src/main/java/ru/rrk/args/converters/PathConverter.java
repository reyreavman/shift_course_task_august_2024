package ru.rrk.args.converters;

import com.beust.jcommander.IStringConverter;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс, конвертирующий входные строки в пути системы.
 */
public class PathConverter implements IStringConverter<Path> {
    @Override
    public Path convert(String value) {
        return Paths.get(value);
    }
}
