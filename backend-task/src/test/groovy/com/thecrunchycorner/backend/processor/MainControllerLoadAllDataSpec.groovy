package com.thecrunchycorner.backend.processor

import com.sun.javaws.Main
import com.thecrunchycorner.backend.formats.CommonDefinition
import com.thecrunchycorner.backend.formats.Definition
import spock.lang.Specification

import java.nio.file.Paths

/*  Mocking a filesystem is more complex than mocking a database connection or rest endpoint, so
    these tests will need access to the FS
    This shouldn't affect performance as it's local FS to the tests (unlike DB/rest would be)
 */

class MainControllerLoadAllDataSpec extends Specification {
    def test() {
        given:
        def sourceDirName = "data"
        def path = Paths.get(sourceDirName)
        def files = MainController.getFiles(path)

        when:
        def result = MainController.loadAllData(sourceDirName, files)

        then:
        result.size() == 10
        getByIdx(id, result) == userName

        where:
        id | userName
        1 | "John1"
        2 | "Mary23"
        3 | "Dpayne"
        4 | "joey99"
        5 | "gary23"
        6 | "jj56"
        7 | "franky"
        8 | "bobby3"
        9 | "shaun"
        10 | "ruby"


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
