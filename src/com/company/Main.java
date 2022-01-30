/**
 *   Создание алфавита------------------------------------------------------------------------------------Completed
 *   Проверка правильности текста-------------------------------------------------------------------------Completed
 *   Запись/чтение в/из файл(а)---------------------------------------------------------------------------Mostly Completed
 *   1.  Кодирование--------------------------------------------------------------------------------------Completed
 *   2.  Декодирование------------------------------------------------------------------------------------Completed
 *   3.  Брутфорс-----------------------------------------------------------------------------------------Completed
 *       после точки пробел; пробел после запятой; пробел после !, ?, ;, --.
 *       90%
 *       длина слова < 25
 *       не больше 5 гласных/согласных подряд
 *       '.' ' ' 'A'
 *   4.  Статистический Анализ----------------------------------------------------------------------------Incompleted
 *       Ключ не ищется! Берется зашифрованный файл и расшифрованный аналогичный файл того же автора
 *       и происходит попытка расшифровки на основе добавочного файла.
 */
package com.company;

public class Main {

    public static void main(String[] args) {
        startProgram();


//        String testText = "    Это    тестовая строка, которая должна быть зашифрована, и, в последствии, расшифрована. a eto doljno otbrositsya.   ";
//        int testKey = 5/*Utils.ALPHABET.length()*/;
//
//        System.out.println(Utils.encrypt(testText, testKey));


//        System.out.println(testText);
//        String test = new String(Utils.prepareUserText(testText));
//        System.out.println(test);
    }

    public static void startProgram() {
        while(true) {
            System.out.println("""
                    Welcome to my humble crypto program.
                    Choose necessary option from the list:
                    1  -   encrypt selected text via key
                    2  -   decrypt selected text via key
                    3  -   decrypt selected text with bruteforce method
                    4  -   decrypt selected text with statistic analysis method
                    0  -   exit program""");

            int userOption = -1;
            try {
                userOption = Utils.SCANNER.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (userOption) {
                case (1): {
                    Utils.encrypt();
                    break;
                }
                case (2): {
                    Utils.decrypt();
                    break;
                }
                case (3): {
                    Utils.decryptBruteForce();
                    break;
                }
                case (4): {
                    System.out.println("Statistic analysis option in progress... Choose another option\n");
                    break;
                }
                case (0): {
                    System.exit(0);
                }
                default: {
                    System.out.println("Wrong number!");
                }
            }
        }
    }
}
