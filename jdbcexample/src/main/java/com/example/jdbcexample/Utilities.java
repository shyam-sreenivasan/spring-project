package com.example.jdbcexample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class Utilities {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    public static Date convertStringToDate(String dateStr) throws ParseException {
        return formatter.parse(dateStr);
    }
}
