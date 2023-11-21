package ru.melnik;

import java.util.*;

public class Main {
    public static final Map<Long, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) {

        for(int i = 0; i < 1000; i++) {

            new Thread(() -> {
                String s = generateRoute("RLRFR", 100);

                long count = s.chars()
                        .filter(str -> str == 'R')
                        .count();

                sumCountR(count);
            }
            ).start();
        }

        Map.Entry<Long, Integer> maxEntry = Collections.max(sizeToFreq.entrySet(), Map.Entry.comparingByValue());
        System.out.println("Самое частое количество повторений " + maxEntry.toString().replace("=", " (встретилось ") + " раз)");

        System.out.println("Другие размеры:");
        sizeToFreq.forEach((k,v) -> System.out.println(k + " (" + v + " раз)"));

    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    private static void sumCountR(long count) {
        synchronized(sizeToFreq) {
            sizeToFreq.put(count, sizeToFreq.getOrDefault(count, 0) +1);
        }
    }

}