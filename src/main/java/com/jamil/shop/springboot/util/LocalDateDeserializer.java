package com.jamil.shop.springboot.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;

/**
 * Created by Abdul Fatah on 5/29/17.
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TextNode node = p.getCodec().readTree(p);
        String dateString = node.asText();
        return LocalDate.parse(dateString, DateTimeFormat.forPattern("yyyy-M-d"));
    }
}
