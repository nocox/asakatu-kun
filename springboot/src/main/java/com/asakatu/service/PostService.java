package com.asakatu.service;

import com.asakatu.entity.Event;
import com.asakatu.response.ForFrontEvent;
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

    String getDesignDateTime(Event event){
        DateTimeFormatter dateFormatDate = DateTimeFormatter.ofPattern("[]MM月dd日(eee)", Locale.JAPAN);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("[]H:mm");

        LocalDateTime endLocalDate = event.getStartDate().plusHours((long) event.getDuration());
        return dateFormatDate.format(event.getStartDate()) + " "
                + dateFormat.format(event.getStartDate())
                + " 〜 "
                + dateFormat.format(endLocalDate);
    }

    public ForFrontEvent convertEventForFront(Event event){
        ForFrontEvent forFrontEvent = new ForFrontEvent();
        forFrontEvent.setDesignDate(getDesignDateTime(event));
        forFrontEvent.setEvent(event);
        return forFrontEvent;
    }
}