package ru.rrk.writers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rrk.classifiers.DataType;
import ru.rrk.writers.writer.Writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

@Getter
@RequiredArgsConstructor
public class WritersRepository {
    private final WriterFactory writerFactory;
    private final HashMap<DataType, Writer<String>> writers = new HashMap<>();

    /**
     * Метод проверяет существование Writer, который обеспечивает соответствующий тип данных, если данного Writer в мапе нет,
     * тогда создает его.
     * @param dataType тип данных, который должен быть обеспечен Writer.
     * @param output путь для выходных файлов.
     * @param prefix префикс имен выходных файлов.
     * @param append режим добавления в существующий файл.
     * @return найденный или только что созданный Writer.
     */
    public Writer<String> getOrCreateWriter(DataType dataType, Path output, String prefix, boolean append) {
        if (!writers.containsKey(dataType)) putNewWriterInMap(dataType, output, prefix, append);
        return writers.get(dataType);
    }

    /**
     * Вызов метода close для всех Writer.
     */
    public void closeWriters() {
        writers.values().forEach(Writer::close);
    }

    /**
     * Метод кладет в мапу Writer с заданными данными.
     *
     * @param type   тип данных, которые обеспечит выходной Writer.
     * @param output путь, заданный пользователем.
     * @param prefix префикс, заданный пользователем.
     * @param append режим добавления в существующий файл.
     */
    private void putNewWriterInMap(DataType type, Path output, String prefix, boolean append) {
        String fileName = constructFileName(prefix, type.toString());
        String path = constructPathName(output);
        writers.put(
                type,
                createNewWriter(path, fileName, append)
        );
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
}
