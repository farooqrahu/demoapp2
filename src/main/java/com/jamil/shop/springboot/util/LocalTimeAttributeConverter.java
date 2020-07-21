package com.jamil.shop.springboot.util;
import org.joda.time.LocalTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Time;

/**
 * Created by Ikrama on 5/19/2017.
 */
@Converter(autoApply = true)
public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, Time> {
    @Override
    public Time convertToDatabaseColumn(LocalTime joda){
        if (joda != null) {
            java.time.LocalTime java8 = java.time.LocalTime.of(joda.getHourOfDay(), joda.getMinuteOfHour(), joda.getSecondOfMinute());
            return Time.valueOf(java8);
        }

        return null;
    }

    @Override
    public LocalTime convertToEntityAttribute(Time time) {
        return (time == null ? null : new LocalTime(time));
    }
}
