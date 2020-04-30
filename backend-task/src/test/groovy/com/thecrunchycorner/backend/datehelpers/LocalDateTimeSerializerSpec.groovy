package com.thecrunchycorner.backend.datehelpers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.thecrunchycorner.backend.formats.datehelpers.LocalDateTimeSerializer
import spock.lang.Specification

import java.time.ZoneId
import java.time.ZonedDateTime

class LocalDateTimeSerializerSpec extends Specification {

    def test() {
        given:
        def jsonGenerator = Mock(JsonGenerator.class)
        def serializerProvider = Mock(SerializerProvider.class)
        def zoneId = ZoneId.of("Europe/London")
        def zonedDateTime = ZonedDateTime.of(2015, 01, 12, 12, 01, 34, 00, zoneId)
        def serializer = new LocalDateTimeSerializer()


        when:
        serializer.serialize(zonedDateTime, jsonGenerator, serializerProvider)

        then:
        1 * jsonGenerator.writeString("2015-01-12T12:01:34+00:00")
    }

}
