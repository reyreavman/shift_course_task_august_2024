package ru.rrk;

import lombok.RequiredArgsConstructor;
import ru.rrk.args.Args;
import ru.rrk.classifiers.DataType;
import ru.rrk.classifiers.DataTypeClassifier;
import ru.rrk.readers.InputFileReader;
import ru.rrk.stats.StatsManager;
import ru.rrk.writers.writer.Writer;
import ru.rrk.writers.WritersManager;

import java.io.File;
import java.util.List;

/**
 * Класс, связывающий основные компоненты программы
 */
@RequiredArgsConstructor
public class Manager {
    private final Args args;
    private final InputFileReader reader;
    private final DataTypeClassifier classifier;
    private final WritersManager writersManager;
    private final StatsManager statsManager;

    public Manager start() {
        List<File> files = args.getFiles();
        for (File file : files) {
            List<String> strings = reader.readFile(file);
            strings.parallelStream()
                    .forEach(string -> {
                        DataType dataType = classifier.classify(string);
                        if (!dataType.equals(DataType.VOID)) {
                            Writer<String> writer = writersManager.computeIfAbsent(dataType, args.getOutput(), args.getPrefix(), args.isAppendable());
                            writer.write(string.concat("\n"));
                            statsManager.addData(dataType, string);
                        }
                    });
        }
        writersManager.closeWriters();
        return this;
    }
}
