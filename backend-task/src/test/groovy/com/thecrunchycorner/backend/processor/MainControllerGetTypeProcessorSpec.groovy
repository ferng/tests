package com.thecrunchycorner.backend.processor


import com.thecrunchycorner.backend.formats.Definition
import spock.lang.Specification

import java.nio.file.Paths

/*  Mocking a filesystem is more complex than mocking a database connection or rest endpoint, so
    these tests will need access to the FS
    This shouldn't affect performance as it's local FS to the tests (unlike DB/rest would be)
 */

class MainControllerGetTypeProcessorSpec extends Specification {
    def test() {
        when:
        def actualResult = MainController.getTypeProcessor(fileName)

        then:
        expectedResult == actualResult

        where:
        fileName | expectedResult
        "users.csv" | TypeProcessors.CSV_FORMATTER
        "users.xml" | TypeProcessors.XML_FORMATTER
        "users.json" | TypeProcessors.JSON_FORMATTER
        "users.unk" | null


    }

    String getByIdx(int id, List<Definition> resultSet) {
        for(Definition singleResult: resultSet) {
            if (singleResult.getUserId() == id) {
                return singleResult.getUserName();
            }
        }
        return null;
    }
}
