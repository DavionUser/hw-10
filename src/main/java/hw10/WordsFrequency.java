package hw10;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
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
            }
            List<String> list = new ArrayList<>(List.of(words.split(" ")));

                list.stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet()
                        .stream()
                        .sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()) * -1)
                        .collect(Collectors.toList())
                        .forEach(System.out::println);

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
