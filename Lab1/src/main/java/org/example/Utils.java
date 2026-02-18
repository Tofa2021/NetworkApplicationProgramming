package org.example;

import java.time.LocalDate;

public class Utils {
    public static String convertDateToString(LocalDate date) {
        return date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
    }
}
