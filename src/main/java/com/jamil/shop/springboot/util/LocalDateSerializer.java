package com.jamil.shop.springboot.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 * Created by Abdul Fatah on 5/27/17.
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate>{
    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeString(formatter.print(value));
    }
}
