package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Utils {

    public static final Set<Character> ALPHABET_SET = Set.of(
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п',
            'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
            '.', ',', '"', ':', '-', '!', '?', ' ',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
            );
    public static final Scanner scanner = new Scanner(System.in);

    // TODO: Расшифровывает текст возвращая ключ.
    public static int decrypt(String text) {
        return 0;
    }
    // TODO: Зашифровывает текст по заданному ключу.
    public static String encrypt(String text, int key) {
        return "";
    }

    /** Удаление символов, не входящих в алфавит.*/
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
    /** Получает String из ArrayList<Character>*/
    public static String getStringFromArrayListCharacter(ArrayList<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }

    /** Удаляет двойные пробелы, которые могли возникнуть из-за очистки текста от лишних символов*/
    public static ArrayList<Character> removeSpaces(ArrayList<Character> checkedTextChars) {
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

    // TODO: Записывает текст из строки в файл.
    public static void textWriterToFile(String text) {

    }
    // TODO: Считывает текст из файла в строку.
    public static String textReaderFromFile(String stringPath) {
        return "";
    }





    /**
     * Deprecated methods
     */
    // public static final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    //    public static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    //    public static final String SYMBOLS = ".,\":-!? ";
    //    public static final String DIGITS = "0123456789";
    //    public static final String ALPHABET = RUSSIAN_ALPHABET + /*ENGLISH_ALPHABET +  */SYMBOLS/* + DIGITS*/;
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
