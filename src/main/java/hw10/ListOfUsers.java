package hw10;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ListOfUsers {
    private static final String FILE_PATH = "main\\files\\fileUsers.txt";
    private static final String JSON_PATH = "main\\files\\user.json";

    public static void main(String[] args) {
        File file = new File(FILE_PATH);
        File fileJson = new File(JSON_PATH);
        String json;

        List<String> list = new ArrayList<>();
        isFileExist(file);
        isFileExist(fileJson);

        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(file))) {
            bWriter.write("name age\n" +
                    "\n" +
                    "alice 21\n" +
                    "\n" +
                    "ryan 30");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            scanner.nextLine();

            while (scanner.hasNext()) {

                try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(fileJson))) {
                    User user = new User(scanner.next(), scanner.nextInt());
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    json = gson.toJson(user);
                    list.add("\n" + json + "\n");
                    bWriter.write(list.toString());

                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Json file is written");
    }

    static class User {

        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return getName() + getAge();
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