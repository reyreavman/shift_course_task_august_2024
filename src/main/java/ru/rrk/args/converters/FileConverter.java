package ru.rrk.args.converters;

import com.beust.jcommander.IStringConverter;

import java.io.File;

/**
 * Класс, конвертирующий входные строки в файлы.
 */
public class FileConverter implements IStringConverter<File> {
    @Override
    public File convert(String value) {
        return new File(value);
    }
}
