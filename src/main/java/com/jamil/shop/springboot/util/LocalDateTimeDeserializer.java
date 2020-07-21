package com.jamil.shop.springboot.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;

/**
 * Created by Abdul Fatah on 5/17/2017.
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime>{

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TextNode node = p.getCodec().readTree(p);
        String dateString = node.asText();
        String dateTimePattern = "\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}";

        if(dateString.matches(dateTimePattern)){
            return LocalDateTime.parse(dateString, DateTimeFormat.forPattern("yyyy-MM-dd hh:mm:ss"));
        }else{
            LocalDate date = LocalDate.parse(dateString, DateTimeFormat.forPattern("yyyy-MM-dd"));
            return date.toDateTimeAtCurrentTime().toLocalDateTime();
        }

    }
}
