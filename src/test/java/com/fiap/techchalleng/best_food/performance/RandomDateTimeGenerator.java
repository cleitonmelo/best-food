package com.fiap.techchalleng.best_food.performance;

import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class RandomDateTimeGenerator {

    private static final Random random = new Random();

    public static String generateRandomDate() {
        int startYear = 2023;
        int endYear = 2025;
        int year = startYear + random.nextInt(endYear - startYear + 1);
        int month = random.nextInt(12) + 1;

        LocalDate startDate = LocalDate.of(year, month, 1);
        int maxDay = startDate.lengthOfMonth();
        int day = random.nextInt(maxDay) + 1;

        LocalDate randomDate = LocalDate.of(year, month, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return randomDate.format(formatter);
    }

    public static String generateRandomTime() {
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}


