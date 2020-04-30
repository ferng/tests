package com.thecrunchycorner.backend.datehelpers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.thecrunchycorner.backend.formats.datehelpers.LocalDateTimeDeserializer
import spock.lang.Specification

import java.time.Month

class LocalDateTimeDeserializerSpec extends Specification {

    def test() {
        given:
        def jsonParser = Mock(JsonParser.class)
        def codec = Mock(ObjectCodec.class)
        def jsNode = Mock(JsonNode.class)
        def context = Mock(DeserializationContext.class)
        def deserializer = new LocalDateTimeDeserializer()

        when:
        jsNode.asText() >> "22-09-2014 08:23:54"
        codec.readTree(jsonParser) >> jsNode
        jsonParser.getCodec() >> codec
        def result = deserializer.deserialize(jsonParser, context)


        then:
        result.getYear() == 2014
        result.getMonth() == Month.SEPTEMBER
        result.getDayOfMonth() == 22
        result.getHour() == 8
        result.getMinute() == 23
        result.getSecond() == 54
        result.getOffset().getId() == "+01:00"
        result.toString() == "2014-09-22T08:23:54+01:00[Europe/London]"
    }

}
