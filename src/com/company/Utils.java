package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Utils {

    public static final Set<Character> ALPHABET_SET = Set.of(
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п',
            'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '.', ',', '"', ':', ';', '-', '—', '!', '?', ' ', '/', '\\',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    );
    public static final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final String SYMBOLS = ".,\":-—!? /\\";
    public static final String DIGITS = "0123456789";
    public static final String ALPHABET = RUSSIAN_ALPHABET + ENGLISH_ALPHABET +  SYMBOLS + DIGITS;
    public static final Scanner SCANNER = new Scanner(System.in);

    public static final int TEST_KEY = 3;
    public static final String TEST_USER_PATH = "C:\\Users\\User\\Desktop\\testUserFile.txt";
    public static final String TEST_USER_PATH_ENCRYPTED = "C:\\Users\\User\\Desktop\\testUserFile-encrypted.txt";


    // TODO: Расшифровывает текст на основе статистического анализа
    public static String decryptStatisticAnalysis(String text, String similarText) {
        return "";
    }
    // TODO: Расшифровывает текст методом bruteforce
    public static String decryptBruteForce(String text) {
        return "";
    }

    /** Расшифровывает текст по ключу. */
    public static void decrypt() {
        String userTextPath = getUserTextPath();
        String userText = getUserTextFromFile(userTextPath)/*"вaхс0хзфхсегc0фхуснг-0нсхсугc0жсойрг0дюхя0кгылчусегрг-0л-0е0тсфозжфхелл-0угфылчусегрг:0d0hwr0gromqr0rweurvlwv,d:"*/;
        String preparedUserText = prepareUserText(userText);

        System.out.println("Put a key from 0 to " + Utils.ALPHABET_SET.size());
        int userKey = Utils.SCANNER.nextInt();

        char[] userTextChars = preparedUserText.toCharArray();
        char[] alphabetChars = ALPHABET.toCharArray();
        for (int i = 0; i < userTextChars.length; i++) {
            for (int j = 0; j < alphabetChars.length; j++) {
                if (userTextChars[i] == alphabetChars[j]) {
                    int newPosition = j - userKey;
                    if (newPosition < 0) {
                        newPosition = alphabetChars.length + newPosition;
                    }
                    userTextChars[i] = alphabetChars[newPosition];
                    break;
                }
            }
        }
        writeTextToFile(new String(userTextChars), userTextPath, false);
    }

    /** Зашифровывает текст по заданному ключу. */
    public static void encrypt() {
        String userTextPath = getUserTextPath();

        String userText = getUserTextFromFile(userTextPath)/*"    9Это    тестовая строка, которая должна быть зашифрована, и, в последствии, расшифрована. a eto doljno otbrositsya.   "*/;
        String preparedUserText = prepareUserText(userText);

        System.out.println("Put a key from 0 to " + Utils.ALPHABET_SET.size());
        int userKey = Utils.SCANNER.nextInt();

        char[] userTextChars = preparedUserText.toCharArray();
        char[] alphabetChars = ALPHABET.toCharArray();
        for (int i = 0; i < userTextChars.length; i++) {
            for (int j = 0; j < alphabetChars.length; j++) {
                if (userTextChars[i] == alphabetChars[j]) {
                    int newPosition = j + userKey;
                    userTextChars[i] = alphabetChars[newPosition % alphabetChars.length];
                    System.out.print(userTextChars[i]);
                    break;
                }
            }
        }
        writeTextToFile(new String(userTextChars), userTextPath, true);
    }


    /** Получает путь к файлу пользователя */
    // TODO: Исправить двойной вызов Scanner
    private static String getUserTextPath() {
        System.out.println("Put the full path of your text here: ");
        try {
            String userTextPath = Utils.SCANNER.nextLine();
            while (!userTextPath.endsWith(".txt")) {
                System.out.println("There is a mistake in the filename. Try again: ");
                userTextPath = Utils.SCANNER.nextLine();
            }
            return userTextPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /** Удаление символов, не входящих в алфавит. */
    public static String prepareUserText(String userText) {
        userText = userText.toLowerCase();
        userText = userText.trim();

        char[] textChars = userText.toCharArray();
        ArrayList<Character> checkedTextChars = new ArrayList<>();

        for (int i = 0; i < textChars.length; i++) {
            if (ALPHABET_SET.contains(textChars[i])) {
                checkedTextChars.add(textChars[i]);
            }
        }
        checkedTextChars = removeSpaces(checkedTextChars);
        return getStringFromArrayListCharacter(checkedTextChars);
    }
    /** Получает String из ArrayList<Character> */
    private static String getStringFromArrayListCharacter(ArrayList<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }
    /** Удаляет двойные пробелы, которые могли возникнуть из-за очистки текста от лишних символов */
    private static ArrayList<Character> removeSpaces(ArrayList<Character> checkedTextChars) {
        ArrayList<Character> checkedTextCharsWithoutDoubledSpaces = new ArrayList<>();

        checkedTextCharsWithoutDoubledSpaces.add(checkedTextChars.get(0));
        for (int i = 1; i < checkedTextChars.size(); i++) {
            if (checkedTextChars.get(i) == ' ' && checkedTextCharsWithoutDoubledSpaces.get(checkedTextCharsWithoutDoubledSpaces.size() - 1) == ' ') {
                continue;
            } else {
                checkedTextCharsWithoutDoubledSpaces.add(checkedTextChars.get(i));
            }
        }
        return checkedTextCharsWithoutDoubledSpaces;
    }
    /** Записывает текст из строки в файл. false - зашифрованный, true - расшифрованный*/
    public static void writeTextToFile(String userText, String previousUserTextPath, boolean isEncryptOrDecrypt) {
        int txtIndex = previousUserTextPath.lastIndexOf(".txt");
        String fileName = previousUserTextPath.substring(0, txtIndex);
        String[] methods = new String[] {"-encrypted", "-decrypted"};
        String newFileName = fileName;
        if (isEncryptOrDecrypt) {
            newFileName = newFileName + methods[0] + ".txt";
        } else {
            newFileName = newFileName + methods[1] + ".txt";
        }
        try {
            Files.writeString(Path.of(newFileName), userText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** Считывает текст из файла в строку. */
    public static String getUserTextFromFile(String userTextPath) {
        try {
            byte[] userTextBytes = Files.readAllBytes(Path.of(userTextPath));
            return new String(userTextBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }





    /**
     * Deprecated methods
     */
    //     public static final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
//        public static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
//        public static final String SYMBOLS = ".,\":-!? ";
//        public static final String DIGITS = "0123456789";
//        public static final String ALPHABET = RUSSIAN_ALPHABET + /*ENGLISH_ALPHABET +  */SYMBOLS/* + DIGITS*/;
    /** DEPRECATED Проверяет на наличие в тексте символов из алфавита.
    public static String deprecatedCheckTextAccordingToTheAlphabet(String text) {
        text = text.toLowerCase();
        text = text.trim();

        char[] textChars = text.toCharArray();
        char[] alphabetChars = ALPHABET.toCharArray();
        ArrayList<Character> checkedTextChars = new ArrayList<>();

        for (int i = 0; i < textChars.length; i++) {
            for (int j = 0; j < alphabetChars.length; j++) {
                if (alphabetChars[j] == textChars[i]) {
                    checkedTextChars.add(textChars[i]);
                }
            }
        }
        return getStringFromArrayListCharacter(checkedTextChars);
    }*/
}
