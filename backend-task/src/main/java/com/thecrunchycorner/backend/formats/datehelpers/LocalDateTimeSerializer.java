package com.thecrunchycorner.backend.formats.datehelpers;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/* Convert java LocalDateTime to a string for writing */
public class LocalDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

    // ISO_OFFSET_DATE_TIME changes +00:00 to Z as per ISO OFFSET_DATE_TIME so doesn't produce
    // what is in the examples.

    // This adds doesn't insert colon between HH:mm as in the examples
    private static DateTimeFormatter ISO_DATE_TIME_WITH_FORCED_OFFSET =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

    // So we use our own
    private String formatter(ZonedDateTime dateTime) {
        String formatted = dateTime.format(ISO_LOCAL_DATE_TIME);
        if (dateTime.getOffset().getId() == "Z") {
            formatted += "+00:00";
        } else {
            formatted += dateTime.getOffset().getId();
        }
        return formatted;
    }

    @Override
    public void serialize(ZonedDateTime dateTime, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(formatter(dateTime));
    }
}