package ru.netology;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger beautifulNickname3 = new AtomicInteger(0);
    static AtomicInteger beautifulNickname4 = new AtomicInteger(0);
    static AtomicInteger beautifulNickname5 = new AtomicInteger(0);

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
    public static boolean isPalindrome(String nickname) {
        int length = nickname.length();
        for (int i=0; i < length/2; i++) {
            if (nickname.charAt(i) != nickname.charAt(length-1-i)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isOneLetterNick(String nickname) {
        int length = nickname.length();
        for (int i=1; i < length; i++) {
            if (nickname.charAt(i-1) != nickname.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAscendingOrderLetters(String nickname) {
        int length = nickname.length();
        for (int i = 1; i< length; i++) {
            if ((int)nickname.charAt(i) < (int)nickname.charAt(i-1)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        //создаем поток проверки никнеймов на палиндромы
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (isPalindrome(texts[i])) {
                    switch (texts[i].length()) {
                        case 3:
                            int value = beautifulNickname3.addAndGet(1);
                            beautifulNickname3.set(value);
                            break;
                        case 4:
                            value = beautifulNickname4.addAndGet(1);
                            beautifulNickname4.set(value);
                            break;
                        default:
                            //длина никнейма, значит, равна 5
                            value = beautifulNickname5.addAndGet(1);
                            beautifulNickname5.set(value);
                            break;

                    }
                }
            }
        }

        );

        Thread thread2 = new Thread (() -> {
            for (int i = 0; i < texts.length; i++) {
                if (isOneLetterNick(texts[i])) {
                    switch (texts[i].length()) {
                        case 3:
                            int value = beautifulNickname3.addAndGet(1);
                            beautifulNickname3.set(value);
                            break;
                        case 4:
                            value = beautifulNickname4.addAndGet(1);
                            beautifulNickname4.set(value);
                            break;
                        default:
                            //длина никнейма, значит, равна 5
                            value = beautifulNickname5.addAndGet(1);
                            beautifulNickname5.set(value);
                            break;

                    }
                }
            }
        }

        );

        Thread thread3 = new Thread (() -> {
            for (int i = 0; i < texts.length; i++) {
                if (isAscendingOrderLetters(texts[i])) {
                    switch (texts[i].length()) {
                        case 3:
                            int value = beautifulNickname3.addAndGet(1);
                            beautifulNickname3.set(value);
                            break;
                        case 4:
                            value = beautifulNickname4.addAndGet(1);
                            beautifulNickname4.set(value);
                            break;
                        default:
                            //длина никнейма, значит, равна 5
                            value = beautifulNickname5.addAndGet(1);
                            beautifulNickname5.set(value);
                            break;

                    }
                }
            }
        }

        );
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("Красивых слов с длиной 3: " + beautifulNickname3 + " шт.");
        System.out.println("Красивых слов с длиной 4: " + beautifulNickname4 + " шт.");
        System.out.println("Красивых слов с длиной 5: " + beautifulNickname5 + " шт.");





    }
}