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
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс, связывающий основные компоненты программы
 */
@RequiredArgsConstructor
public class Manager {
    private final Args args;
    private final InputFileReader reader;
    private final DataTypeClassifier classifier;
    private final WriterFactory writerFactory;
    private final ConcurrentHashMap<DataType, Writer<String>> writers = new ConcurrentHashMap<>();

    public Manager start() {
        List<File> files = args.getFiles();
        for (File file : files) {
            List<String> strings = reader.readFile(file);
            strings.parallelStream()
                    .forEach(string -> {
                        DataType dataType = classifier.classify(string);
                        if (!dataType.equals(DataType.VOID)) {
                            Writer<String> writer = writers.computeIfAbsent(dataType, k -> putNewWriterInMap(dataType, args.getOutput(), args.getPrefix(), args.isAppendable()));
                            writer.write(dataType.toString().concat("\n"));
                        }
                    });
        }
        writers.values().forEach(Writer::close);
        return this;
    }

    /**
     * Метод собирает имя файла из префикса и заданного имени.
     * @param prefix префикс имени файла.
     * @param name имя файла.
     * @return возвращает имя файла.
     */
    private String constructFileName(String prefix, String name) {
        return prefix != null ?
                prefix.concat(name.toLowerCase()).concat("s.txt") :
                name.toLowerCase().concat("s.txt");
    }

    /**
     * Метод собирает путь выходного файла.
     * @param path путь, который, был задан в аргументах.
     * @return путь собранный из данных системы или данных, которые задал юзер.
     */
    private String constructPathName(Path path) {
        return path != null ?
                path.toString() :
                System.getProperty("user.dir");
    }

    /**
     * Метод создает новый Writer.
     * @param constructedPath итоговый путь файла, в который предстоит писать результаты выполнения программы.
     * @param fileName имя выходного файла.
     * @param append режим добавления в существующий файл.
     * @return возвращает Writer, собранный из аргументов метода.
     */
    private Writer<String> createNewWriter(String constructedPath, String fileName, boolean append) {
        try {
            return writerFactory.createWriter(new BufferedWriter(new FileWriter(constructedPath.concat("/").concat(fileName), append)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(100);
        }
        return null;
    }

    /**
     * Метод создает и кладет в мапу Writer с заданными данными
     * @param type тип данных, которые обеспечит выходной Writer.
     * @param output путь, заданный пользователем.
     * @param prefix префикс, заданный пользователем.
     * @param append режим добавления в существующий файл.
     * @return возвращает созданный Writer.
     */
    private Writer<String> putNewWriterInMap(DataType type, Path output, String prefix, boolean append) {
        String fileName = constructFileName(prefix, type.toString());
        String path = constructPathName(output);
        return writers.put(type, createNewWriter(path, fileName, append));
    }
}
