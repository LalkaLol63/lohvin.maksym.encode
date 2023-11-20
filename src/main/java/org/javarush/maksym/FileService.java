package org.javarush.maksym;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {
    public static String readFile(Path filePath) throws IOException { //читання файлу
        return Files.readString(filePath);
    }

    public static void writeFile(Path filePath, String text) throws IOException { //запис файлу
        Files.createFile(filePath);
        Files.writeString(filePath, text);
    }
    public static Path addMarkToPath(Path filePath, String mark) { // додавання марки до назви файлу
        StringBuilder newFilePath = new StringBuilder(filePath.toString());
        //якщо файл має розширення
        if(newFilePath.lastIndexOf(".") != 1 && newFilePath.lastIndexOf(".") != 0) {
            newFilePath.insert(newFilePath.lastIndexOf("."), mark);
            return Path.of(newFilePath.toString());
        }
        return null;
    }

}
