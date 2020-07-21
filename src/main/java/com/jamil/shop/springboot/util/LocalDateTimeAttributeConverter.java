package com.jamil.shop.springboot.util;

import org.joda.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;

/**
 * Created by Abdul Fatah on 5/17/2017.
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime dateTime) {

        return dateTime != null ? new Timestamp(dateTime.toLocalDate().toDateTimeAtCurrentTime().getMillis()) : null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {

        return timestamp != null ? new LocalDateTime(timestamp.getTime()) : null;
    }

}
