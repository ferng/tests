package com.thecrunchycorner.backend.processor

import com.thecrunchycorner.backend.formats.Definition
import spock.lang.Specification

/*  Mocking a filesystem is more complex than mocking a database connection or rest endpoint, so
    these tests will need access to the FS
    This shouldn't affect performance as it's local FS to the tests (unlike DB/rest would be)
 */

class MainControllerCreateOutputDirSpec extends Specification {
    def test() {
        given:
        def destinationDirName = "testoutput"
        def outDir = new File(destinationDirName)
        cleanTestDir(outDir)

        when:
        MainController.createOutputDir(destinationDirName)

        then:
        outDir.exists()
    }


    void cleanTestDir(File destinationDirName) {
        if (destinationDirName.exists()) {
            File[] existingFiles = destinationDirName.listFiles();
            if (existingFiles != null) {
                for (File file : existingFiles) {
                    file.delete()
                }
            }
        }
        destinationDirName.delete()
    }

}
