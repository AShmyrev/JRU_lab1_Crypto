package com.company;

import com.sun.source.tree.Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Utils {
    private static final Set<Character> ALPHABET_SET = Set.of(
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п',
            'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '.', ',', '"', ':', ';', '-', '—', '!', '?', ' ', '/', '\\',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    );
    private static final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String SYMBOLS = ".,\":-—!? /\\";
    private static final String DIGITS = "0123456789";
    private static final String ALPHABET = RUSSIAN_ALPHABET + ENGLISH_ALPHABET +  SYMBOLS + DIGITS;
    private static final String PARTS_OF_WORD = RUSSIAN_ALPHABET + ENGLISH_ALPHABET + '-';
    private static final int MAX_WORD_LENGTH = 30;
    private static final int MAX_LENGTH_WITHOUT_SPACES = 50;

    public static final Scanner SCANNER = new Scanner(System.in);

//    private static final int TEST_KEY = 3;
//    private static final String TEST_USER_PATH = "C:\\Users\\User\\Desktop\\testUserFile.txt";
//    private static final String TEST_USER_PATH_ENCRYPTED = "C:\\Users\\User\\Desktop\\testUserFile1-encrypted.txt";


    // TODO: Расшифровывает текст на основе статистического анализа
    public static void decryptStatisticAnalysis() {
        String userTextPath = getUserTextPath();
        String userText = getUserTextFromFile(userTextPath);
        String userStatisticTextPath = getUserTextPath();
        String userStatisticText = getUserTextFromFile(userStatisticTextPath);

        HashMap<Character, Integer> mapOfSymbolStatisticsOfTheUserText = getSymbolStatistics(userText);
        HashMap<Character, Integer> mapOfSymbolStatisticsOfTheUserStatisticText = getSymbolStatistics(userStatisticText);
    }

    // TODO: Получает статистику двух HashMap
    private static HashMap<Character, Character> getHashMapsStatistics(HashMap<Character, Integer> mapUserText,
                                                                     HashMap<Character, Integer> mapUserStatisticText) {
        HashMap<Character, Character> mapComparedStatistics = new HashMap<>();

        int theBiggestMapSize = 0;
        if (mapUserText.size() >= mapUserStatisticText.size()) {
            theBiggestMapSize = mapUserText.size();
        } else {
            theBiggestMapSize = mapUserStatisticText.size();
        }
        for (int i = 0; i < theBiggestMapSize; i++) {
//            if ()
            Map.Entry<Character, Integer> current = null;
            for (Map.Entry<Character, Integer> entryUserText : mapUserText.entrySet()) {

            }
            for (Map.Entry<Character, Integer> entryUserStatisticText : mapUserStatisticText.entrySet()) {

            }
            Map.Entry<Character, Character> currentMaxByValue = null;
        }

        return null;
    }



    /** Получает HashMap с количеством вхождений каждого символа + относительную статистику */
    private static HashMap<Character, Integer> getSymbolStatistics(String userText) {
        HashMap<Character, Integer> mapOfAbsoluteSymbolsStatistics = new HashMap<>();
        HashMap<Character, Integer> mapOfRelativeSymbolsStatistics = new HashMap<>();
        char[] userTextChars = userText.toCharArray();
        for (int i = 0; i < userTextChars.length; i++) {
            if (mapOfAbsoluteSymbolsStatistics.containsKey(userTextChars[i])) {
                Integer currentSymbolValue = mapOfAbsoluteSymbolsStatistics.get(userTextChars[i]);
                ++currentSymbolValue;
                mapOfAbsoluteSymbolsStatistics.put(userTextChars[i], currentSymbolValue);
            } else {
                mapOfAbsoluteSymbolsStatistics.put(userTextChars[i], 1);
            }
        }
        mapOfAbsoluteSymbolsStatistics = getMapFilledByAlphabet(mapOfAbsoluteSymbolsStatistics);

        for (Character currentChar : mapOfAbsoluteSymbolsStatistics.keySet()) {
            Integer currentValue = mapOfAbsoluteSymbolsStatistics.get(currentChar);
            int relativeValue = currentValue * 10_000 / userText.length();
            mapOfRelativeSymbolsStatistics.put(currentChar, relativeValue);
        }
//        for (Map.Entry<Character, Integer> entry : mapOfAbsoluteSymbolsStatistics.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
//        System.out.println();
        return mapOfRelativeSymbolsStatistics;
    }
    /** Дозаполняет HashMap на случай если какой-либо символ не встретился ни разу */
    private static HashMap<Character, Integer> getMapFilledByAlphabet(HashMap<Character, Integer> mapToBeFilled) {
        char[] alphabetChars = ALPHABET.toCharArray();
        for (int i = 0; i < alphabetChars.length; i++) {
            if ( !mapToBeFilled.containsKey(alphabetChars[i]) ) {
                mapToBeFilled.put(alphabetChars[i], 0);
            }
        }
        return mapToBeFilled;
    }

    /** Расшифровывает текст методом bruteforce */
    public static void decryptBruteForce() {
        String userTextPath = getUserTextPath();
        String userText = getUserTextFromFile(userTextPath);
        String bruteForcedText = userText;
        for (int i = 0; i < ALPHABET.length(); i++) {
            bruteForcedText = decryptTextViaKey(userText, i);
            if (isTheBruteForcedTextCorrect(bruteForcedText)) {
                if (i == 0) {
                    System.out.println("The text is not encrypted! BruteForce doesn't needed.");
                    break;
                }
                System.out.println("The text has been bruteForced!\nThe key is " + i);
                writeTextToFile(bruteForcedText, userTextPath, true);
                break;
            }
        }
    }
    /** Проверка расшифрованного брутфорсом текста на правильность */
    private static boolean isTheBruteForcedTextCorrect(String bruteForcedText) {
        char[] bruteForcedTextChars = bruteForcedText.toCharArray();
        int wordCounter = 0, wordsCounterLessThan30Symbols = 0, wordsCounterMoreThan30Symbols = 0;
        boolean wasThereAtLeastOneSpace = false;
        for (int i = 0; i < bruteForcedTextChars.length - 1; i++) {
            if (i == MAX_LENGTH_WITHOUT_SPACES && !wasThereAtLeastOneSpace) {
                System.out.println("BrutForce failed in MAX_LENGTH_WITHOUT_SPACES case!");
                return false;
            }
            /* Точка может быть пунктом, например: п.1
            if (bruteForcedTextChars[i] == '.' && bruteForcedTextChars[i + 1] != ' ') {
                System.out.println("BrutForce failed in '.' + ' ' case!");
                return false;
            }
             */
            if (bruteForcedTextChars[i] == ',' && bruteForcedTextChars[i + 1] != ' ') {
                System.out.println("BrutForce failed in ',' + ' ' case!");
                return false;
            }
            if (bruteForcedTextChars[i] == '!' && bruteForcedTextChars[i + 1] != ' ') {
                System.out.println("BrutForce failed in '!' + ' ' case!");
                return false;
            }
            if (bruteForcedTextChars[i] == '?' && bruteForcedTextChars[i + 1] != ' ') {
                System.out.println("BrutForce failed in '?' + ' ' case!");
                return false;
            }
            if (bruteForcedTextChars[i] == ';' && bruteForcedTextChars[i + 1] != ' ') {
                System.out.println("BrutForce failed in ';' + ' ' case!");
                return false;
            }
            if (PARTS_OF_WORD.indexOf(bruteForcedTextChars[i]) != -1) {
                wordCounter++;
            }
            if (bruteForcedTextChars[i] == ' ') {
                if (i != 0) {
                    wasThereAtLeastOneSpace = true;
                }
                if (wordCounter > MAX_WORD_LENGTH) {
                    wordsCounterMoreThan30Symbols++;
                } else {
                    wordsCounterLessThan30Symbols++;
                }
                wordCounter = 0;
            }
            if ((double)wordsCounterMoreThan30Symbols / wordsCounterLessThan30Symbols * 100 > 2.0) {
                System.out.println("BrutForce failed in 'too much long words case'!");
                return false;
            }
        }
        return true;
    }

    /** Расшифровывает текст по ключу. */
    public static void decrypt() {
        String userTextPath = getUserTextPath();
        String userText = getUserTextFromFile(userTextPath)/*"вaхс0хзфхсегc0фхуснг-0нсхсугc0жсойрг0дюхя0кгылчусегрг-0л-0е0тсфозжфхелл-0угфылчусегрг:0d0hwr0gromqr0rweurvlwv,d:"*/;
        String preparedUserText = prepareUserText(userText);

        int userKey = getUserKey();

        String decryptedText = decryptTextViaKey(preparedUserText, userKey);

        System.out.println("The text has been decrypted!");
        writeTextToFile(decryptedText, userTextPath, false);
    }

    /** Зашифровывает текст по заданному ключу. */
    public static void encrypt() {
        String userTextPath = getUserTextPath();

        String userText = getUserTextFromFile(userTextPath)/*"    9Это    тестовая строка, которая должна быть зашифрована, и, в последствии, расшифрована. a eto doljno otbrositsya.   "*/;
        String preparedUserText = prepareUserText(userText);
        int userKey = getUserKey();

        String encryptedText = encryptTextViaKey(preparedUserText, userKey);

        System.out.println("The text has been encrypted!");
        writeTextToFile(encryptedText, userTextPath, true);
    }


    /** Вспомогательный метод, расшифровывающий текст по ключу */
    private static String decryptTextViaKey(String textToDecrypt, int key) {
        char[] alphabetChars = ALPHABET.toCharArray();
        char[] userTextChars = textToDecrypt.toCharArray();
        for (int i = 0; i < userTextChars.length; i++) {
            for (int j = 0; j < alphabetChars.length; j++) {
                if (userTextChars[i] == alphabetChars[j]) {
                    int newPosition = j - key;
                    if (newPosition < 0) {
                        newPosition = alphabetChars.length + newPosition;
                    }
                    userTextChars[i] = alphabetChars[newPosition];
                    break;
                }
            }
        }
        return new String(userTextChars);
    }
    /** Вспомогательный метод, шифрующий текст по ключу */
    private static String encryptTextViaKey(String textToEncrypt, int key) {
        char[] alphabetChars = ALPHABET.toCharArray();
        char[] userTextChars = textToEncrypt.toCharArray();
        for (int i = 0; i < userTextChars.length; i++) {
            for (int j = 0; j < alphabetChars.length; j++) {
                if (userTextChars[i] == alphabetChars[j]) {
                    int newPosition = j + key;
                    userTextChars[i] = alphabetChars[newPosition % alphabetChars.length];
//                    System.out.print(userTextChars[i]);
                    break;
                }
            }
        }
        return new String(userTextChars);
    }
    /** Получает key у пользователя */
    private static int getUserKey() {
        System.out.println("Put a key from 0 to " + Utils.ALPHABET.length());
        int userKey = -1;
        try {
            userKey = Utils.SCANNER.nextInt();
            while ( !(userKey >= 0 && userKey <= Utils.ALPHABET.length()) ) {
                System.out.println("Incorrect key. Put a key from 0 to " + Utils.ALPHABET.length());
                userKey = Utils.SCANNER.nextInt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userKey;
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
    // TODO: сделать флаг на все методы шифровки/расшифровки
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
            System.out.println("The text path is " + newFileName + '\n');
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
