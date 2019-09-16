package com.asakatu.service;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class PostService{
    public String getDesignDate(LocalDateTime startDate, double duration) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("[]H:mm");

        LocalDateTime endLocalDate = startDate.plusHours((long) duration);
        String dayOfWeek = startDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.JAPANESE);
        return dayOfWeek + " "
                + dateFormat.format(startDate)
                + " 〜 "
                + dateFormat.format(endLocalDate);
    }
}