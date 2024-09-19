import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
public class FileManager {


    public class FileHandler {

        public static String readFile(String filePath) throws IOException {
            Path path = Paths.get(filePath);
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes, StandardCharsets.UTF_8);
        }

        public static void writeFile(String content, String filePath)  {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(content);
                System.out.println("Строка успешно записана в файл: " + filePath);
            } catch (IOException e) {
                System.out.println("Произошла ошибка при записи в файл: " + e.getMessage());
            }
        }
    }
}
