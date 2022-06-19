package hw10;

import java.io.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordsFrequency {
    private static final String FILE_PATH = "src\\main\\files\\words.txt";

    public static void main(String[] args) {
        File file = new File(FILE_PATH);
        String words = "";

        isFileExist(file);

        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(file))) {
            bWriter.write("the day is sunny the the\n" +
                    "\n" +
                    "the sunny is is");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
            words = bReader.readLine();

            while (bReader.readLine() != null) {
                words += " " + bReader.readLine();
                String[] arW = words.split(" ");
                List<String> list = List.of(arW);

                list.stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet()
                        .stream()
                        .filter(element -> element.getValue() > 0)
                        .collect(Collectors.toList())
                        .forEach(System.out::println);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void isFileExist(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
