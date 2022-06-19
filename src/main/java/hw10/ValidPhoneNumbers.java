package hw10;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPhoneNumbers {
    private static final String FILE_PATH = "src\\main\\files\\file.txt";

    public static void main(String[] args) {
        File file = new File(FILE_PATH);
        isFileExist(file);

        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(file))) {
            bWriter.write("987-123-4567\n" +
                    "\n" +
                    "123 456 7890\n" +
                    "\n" +
                    "(123) 456-7890\n");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
            String numbers = bReader.readLine();
            String regex = "(\\(\\d{3}\\)|\\d{3})( |\\-)\\d{3}\\-\\d{4}";
            Pattern pattern = Pattern.compile(regex);

            while (numbers != null) {
                Matcher matcher = pattern.matcher(numbers);

                if (matcher.matches()) {
                    System.out.println(matcher.group());
                }
                numbers = bReader.readLine();
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
