package com.thecrunchycorner.backend.formats.datehelpers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/* Convert a read string into java LocalDateTime for processing */
public class LocalDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {
    private static DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser,
                                     DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode timeStamp = oc.readTree(jsonParser);
        LocalDateTime deserialized;

        deserialized = LocalDateTime.parse(timeStamp.asText(), DATE_FORMATTER);

        ZoneId zoneId = ZoneId.of("Europe/London");

        // we return the existing date time with a timezone writing it as ISO with offset
        return ZonedDateTime.of(deserialized, zoneId);
    }
}
