package com.trollMarket.helper;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class CustomFormat {
    private static final Locale indoFormat = new Locale("ID", "Id");

    public static String currencyIndoFormat(BigDecimal price){
        return NumberFormat.getCurrencyInstance(indoFormat).format(price);
    }

    public static String dateFormatter(LocalDate date){
        String day = Integer.toString(date.getDayOfMonth());
        String month = Integer.toString(date.getMonthValue());
        String year = Integer.toString(date.getYear());

        day = (day.length() == 1) ? String.format("0%s",day) : day;
        month = (month.length() == 1) ? String.format("0%s",month) : month;

        return String.format("%s/%s/%s", day, month, year);
    }
}
