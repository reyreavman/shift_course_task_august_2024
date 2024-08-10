package ru.rrk;

import lombok.RequiredArgsConstructor;
import ru.rrk.args.Args;
import ru.rrk.classifiers.DataType;
import ru.rrk.classifiers.DataTypeClassifier;
import ru.rrk.printers.Printer;
import ru.rrk.readers.InputFileReader;
import ru.rrk.stats.StatForm;
import ru.rrk.stats.StatsRepository;
import ru.rrk.writers.WritersRepository;

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
    private final WritersRepository writersRepository;
    private final StatsRepository statsRepository;
    private final Printer printer;

    public Manager start() {
        List<File> files = args.getFiles();

        for (File file : files) {
            List<String> strings = reader.readFile(file);
            strings.forEach(string -> {
                DataType dataType = classifier.classify(string);
                if (!dataType.equals(DataType.VOID)) {
                    writeDataWithType(dataType, string);

                    addDataToStatWithType(dataType, string);
                }
            });
        }
        writersRepository.closeWriters();
        printStat();
        return this;
    }

    private void writeDataWithType(DataType dataType, String string) {
        writersRepository.getOrCreateWriter(
                dataType, args.getOutput(), args.getPrefix(), args.isAppendable()
        ).write(string.concat("\n"));
    }

    private void addDataToStatWithType(DataType dataType, String string) {
        if (mapToStatForm() != null) {
            statsRepository.getOrCreateStats(
                    dataType, mapToStatForm()
            ).addData(string);
        }
    }

    private void printStat() {
        if (mapToStatForm() != null) {
            printer.printLn("\nStats:");
            statsRepository.getStats()
                    .forEach((type, stat) -> printer.printStatWithType(type, stat.toString(), "---------------------"));
        }
    }

    private StatForm mapToStatForm() {
        if (args.isFullStatisticsForm()) return StatForm.FULL;
        else if (args.isShortStatisticsForm()) return StatForm.SHORT;
        else return null;
    }
}
