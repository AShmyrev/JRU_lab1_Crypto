package com.company;

public class Main {

    public static void main(String[] args) {
//        startProgram();

        String testText = "    Это    тестовая строка, которая должна быть зашифрована, и, в последствии, расшифрована. a eto doljno otbrositsya.   ";
        int testKey = 5/*Utils.ALPHABET.length()*/;
        System.out.println(testText);
        String test = new String(Utils.prepareUserText(testText));
        System.out.println(test);


        /*
         *   Создание алфавита
         *   1.  Кодирование
         *   2.  Декодирование
         *   3.  Брутфорс
         *       после точки пробел; пробел после запятой; пробел после !, ?, ;, --.
         *       80%
         *       длина слова < 25
         *       не больше 4 гласных/согласных подряд
         *       '.' ' ' 'A'
         *   4.  Статистический Анализ
         *       Можно попробовать взять Thinking in java
         *       Ключ не ищется! Берется зашифрованный файл и расшифрованный аналогичный файл того же автора
         *       и происходит попытка расшифровки на основе добавочного файла.
         */
    }

    public static void startProgram() {
        while(true) {
            System.out.println("""
                    Welcome to my humble crypto program.
                    Choose necessary option from the list:
                    1  -   encrypt selected text via key
                    2  -   decrypt selected text with bruteforce method
                    3  -   decrypt selected text with statistic analysis method
                    0  -   exit program""");

            int userOption = Utils.scanner.nextInt();
            switch (userOption) {
                case (1): {
                    System.out.println("Put the full path of your text here: ");
                    String textPath = Utils.scanner.nextLine();
                    System.out.println("Put a key from 0 to " + Utils.ALPHABET_SET.size());
                    int userKey = Utils.scanner.nextInt();
                    Utils.textWriterToFile(Utils.encrypt(Utils.textReaderFromFile(textPath), userKey));
                    System.out.println("The text encrypted to "/*TODO: create Path*/);
                    break;
                }
                case (2): {
                    System.out.println("bruteforce decrypt option");
                    break;
                }
                case (3): {
                    System.out.println("statistic analysis option");
                    break;
                }
                case (0): {
                    System.exit(0);
                }
            }
        }
    }
}
