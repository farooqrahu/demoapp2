package com.jamil.shop.springboot.util;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * Created by Ikrama on 7/18/2017.
 */
public class LocalDateTimeConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateTimeConverter.class);

    public Long toLong(LocalDateTime localDateTime){
        SimpleDateFormat simpleDateFormat;
        Date date;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = simpleDateFormat.parse(localDateTime.toString("yyyy-MM-dd"));
            return date.getTime();
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(),e);
        }
        return null;
    }

    public LocalDateTime toEndOfDay(LocalDateTime localDateTime) {
        localDateTime = localDateTime.withTime(23, 59, 59, 999);
        return localDateTime;
    }

    public LocalDateTime toStartOfDay(LocalDateTime localDateTime) {
        localDateTime = localDateTime.minusDays(1);
        localDateTime = localDateTime.withTime(0, 0, 0, 0);
        return localDateTime;
    }

    public LocalDateTime toStartOfDay(LocalDateTime localDateTime, int hour, int minutes, int seconds) {
        localDateTime = localDateTime.minusDays(1);
        localDateTime = localDateTime.withTime(hour, minutes, seconds, 0);
        return localDateTime;
    }

    public Timestamp toTimestamp(LocalDateTime localDateTime){
        return new Timestamp(localDateTime.toDateTime().getMillis());
    }

}
