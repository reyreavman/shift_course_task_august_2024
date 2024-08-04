package ru.rrk;

import com.beust.jcommander.JCommander;
import ru.rrk.args.Args;
import ru.rrk.args.ArgsParser;
import ru.rrk.classifiers.DataTypeClassifier;
import ru.rrk.readers.InputFileReader;
import ru.rrk.writers.factory.WriterFactory;

public class Main {
    public static void main(String[] appArgs) {
        Args args = new Args();
        JCommander commander = JCommander.newBuilder()
                .addObject(args)
                .programName("JavaTestTask")
                .allowParameterOverwriting(false)
                .build();
        ArgsParser.parse(args, commander, appArgs);
        InputFileReader reader = new InputFileReader();
        DataTypeClassifier classifier = new DataTypeClassifier("^-?\\d+$", "^[-+]?[0-9]*[.,][0-9]+(?:[eE][-+]?[0-9]+)?$");
        WriterFactory writerFactory = new WriterFactory();
        Manager manager = new Manager(args, reader, classifier, writerFactory).start();
        System.out.println(args.toString());
    }
}