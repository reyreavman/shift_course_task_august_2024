package ru.rrk;

import lombok.RequiredArgsConstructor;
import ru.rrk.args.Args;
import ru.rrk.classifiers.DataType;
import ru.rrk.classifiers.DataTypeClassifier;
import ru.rrk.readers.InputFileReader;
import ru.rrk.writers.Writer;
import ru.rrk.writers.factory.WriterFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class Manager {
    private final Args args;
    private final InputFileReader reader;
    private final DataTypeClassifier classifier;
    private final WriterFactory writerFactory;
    private final HashMap<DataType, Writer<String>> writers = new HashMap<>();

    public Manager start() {
        List<File> files = args.getFiles();
        for (File file : files) {
            List<String> strings = reader.readFile(file);
            for (String string : strings) {
                DataType stringType = classifier.classify(string);

                if (stringType.equals(DataType.VOID)) continue;

                if (!writers.containsKey(stringType)) putNewWriterInMap(stringType, args.getOutput(), args.getPrefix(), args.isAppendable());
                Writer<String> writer = writers.get(stringType);

                writer.write(string.concat("\n"));
            }
        }
        writers.values().forEach(Writer::close);
        return this;
    }

    private String constructFileName(String prefix, String name) {
        return prefix != null ?
                prefix.concat(name.toLowerCase()).concat("s.txt") :
                name.toLowerCase().concat("s.txt");
    }

    private String constructPathName(Path path) {
        return path != null ?
                path.toString() :
                System.getProperty("user.dir");
    }

    private Writer<String> createNewWriter(DataType type, String path, String fileName, boolean append) {
        Writer<String> writer = null;
        try {
            writer = writerFactory.createWriter(new BufferedWriter(new FileWriter(path.concat("/").concat(fileName), append)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(100);
        }
        return writers.put(type, writer);
    }

    private Writer<String> putNewWriterInMap(DataType type, Path output, String prefix, boolean append) {
        String fileName = constructFileName(prefix, type.toString());
        String path = constructPathName(output);
        return createNewWriter(type, path, fileName, append);
    }
}
