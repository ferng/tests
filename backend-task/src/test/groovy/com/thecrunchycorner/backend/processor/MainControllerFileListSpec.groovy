package com.thecrunchycorner.backend.processor

import spock.lang.Specification

import java.nio.file.Path
import java.nio.file.Paths

/*  Mocking a filesystem is more complex than mocking a database connection or rest endpoint, so
    these tests will need access to the FS
    This shouldn't affect performance as it's local FS to the tests (unlike DB/rest would be)
 */
class MainControllerFileListSpec  extends Specification {
    def test() {
        given:
        def sourceDirName = "data"
        def path = Paths.get(sourceDirName)

        when:
        def result = MainController.getFiles(path)

        then:
        result.contains("users.json")
        result.contains("users.xml")
        result.contains("users.csv")
    }
}
