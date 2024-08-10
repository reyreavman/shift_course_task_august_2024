package ru.rrk;

import com.beust.jcommander.JCommander;
import ru.rrk.args.Args;
import ru.rrk.args.ArgsHandler;
import ru.rrk.classifiers.DataTypeClassifier;
import ru.rrk.printers.DefaultPrinter;
import ru.rrk.printers.Printer;
import ru.rrk.readers.InputFileReader;
import ru.rrk.stats.StatFactory;
import ru.rrk.stats.StatForm;
import ru.rrk.stats.StatsManager;
import ru.rrk.writers.WriterFactory;
import ru.rrk.writers.WritersManager;

public class Main {
    public static final String INTEGER_PATTERN = "^-?\\d+$";
    public static final String FLOAT_PATTERN = "^[-+]?[0-9]*[.,][0-9]+(?:[eE][-+]?[0-9]+)?$";

    public static void main(String[] appArgs) {
        long startTime = System.currentTimeMillis();

        initElements(appArgs);

        System.out.printf("time - %d%n", System.currentTimeMillis() - startTime);
    }

    private static void initElements(String[] appArgs) {
        Args args = new Args();
        JCommander commander = JCommander.newBuilder()
                .addObject(args)
                .programName("JavaTestTask")
                .allowParameterOverwriting(false)
                .build();
        ArgsHandler.parse(args, commander, appArgs);

        InputFileReader reader = new InputFileReader();

        DataTypeClassifier classifier = new DataTypeClassifier(INTEGER_PATTERN, FLOAT_PATTERN);

        WriterFactory writerFactory = new WriterFactory();
        WritersManager writersManager = new WritersManager(writerFactory);

        StatFactory statFactory = new StatFactory();
        StatsManager statsManager = new StatsManager(statFactory);

        Printer printer = new DefaultPrinter(System.out);

        Manager manager = new Manager(args, reader, classifier, writersManager, statsManager, printer).start();
    }
}