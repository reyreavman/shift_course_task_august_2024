package ru.rrk;

import lombok.RequiredArgsConstructor;
import ru.rrk.args.Args;
import ru.rrk.classifiers.DataType;
import ru.rrk.classifiers.DataTypeClassifier;
import ru.rrk.printers.Printer;
import ru.rrk.readers.InputFileReader;
import ru.rrk.stats.StatForm;
import ru.rrk.stats.StatsManager;
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
    private final Printer printer;

    public Manager start() {
        List<File> files = args.getFiles();

        for (File file : files) {
            List<String> strings = reader.readFile(file);
            strings.forEach(string -> {
                        DataType dataType = classifier.classify(string);
                        if (!dataType.equals(DataType.VOID)) {
                            writersManager.getOrCreateWriter(
                                    dataType, args.getOutput(), args.getPrefix(), args.isAppendable()
                            ).write(string.concat("\n"));

                            if (mapToStatForm() != null) {
                                statsManager.getOrCreateStats(
                                        dataType, mapToStatForm()
                                ).addData(string);
                            }
                        }
                    });
        }
        writersManager.closeWriters();
        if (mapToStatForm() != null) {
            statsManager.getAllStats()
                    .forEach(stat -> printer.print(stat.toString()));
        }
        return this;
    }

    private StatForm mapToStatForm() {
        if (args.isFullStatisticsForm()) return StatForm.FULL;
        else if (args.isShortStatisticsForm()) return StatForm.SHORT;
        else return null;
    }
}
