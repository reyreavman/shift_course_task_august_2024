package ru.rrk.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Читаем строки из файлов, отдаем обратно список строк
 */
public class InputFileReader {
    public List<String> readFile(File file) {
        ArrayList<String> values = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (true) {
                String string = reader.readLine();
                if (string == null) break;
                else values.add(string);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(100);
        }
        return Collections.unmodifiableList(values);
    }
}