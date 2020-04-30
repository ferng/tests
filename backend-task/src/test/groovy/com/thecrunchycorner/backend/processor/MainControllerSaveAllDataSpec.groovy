package com.thecrunchycorner.backend.processor


import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths

/*  Mocking a filesystem is more complex than mocking a database connection or rest endpoint, so
    these tests will need access to the FS
    This shouldn't affect performance as it's local FS to the tests (unlike DB/rest would be)
 */

class MainControllerSaveAllDataSpec extends Specification {
    def test() {
        given:
        def sourceDirName = "data"
        def destDirName = "testoutput2"
        def path = Paths.get(sourceDirName)
        def files = MainController.getFiles(path)
        def loaded = MainController.loadAllData(sourceDirName, files)
        loaded.sort(null);

        when:
        def outDir = new File(destDirName)
        cleanTestDir(outDir)
        MainController.createOutputDir(destDirName)
        MainController.saveAllData(destDirName, files, loaded)
        def destPath = Paths.get(sourceDirName)
        def destFiles = MainController.getFiles(destPath)
        def actualJson = new String ( Files.readAllBytes( Paths.get(destDirName + "/users.json")) );
        def actualCsv = new String ( Files.readAllBytes( Paths.get(destDirName + "/users.csv")) );
        def actualXml = new String ( Files.readAllBytes( Paths.get(destDirName + "/users.xml")) );

        then:
        destFiles.size() == 3
        getExpectedJson() == actualJson
        getExpectedCsv() == actualCsv
        getExpectedXml() == actualXml
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

    String getExpectedJson() {
        return "[ {\n" +
                "  \"user_id\" : 1,\n" +
                "  \"first_name\" : \"John\",\n" +
                "  \"last_name\" : \"Doe\",\n" +
                "  \"username\" : \"John1\",\n" +
                "  \"user_type\" : \"Employee\",\n" +
                "  \"last_login_time\" : \"2015-01-12T12:01:34+00:00\"\n" +
                "}, {\n" +
                "  \"user_id\" : 2,\n" +
                "  \"first_name\" : \"Mary\",\n" +
                "  \"last_name\" : \"Jane\",\n" +
                "  \"username\" : \"Mary23\",\n" +
                "  \"user_type\" : \"Employee\",\n" +
                "  \"last_login_time\" : \"2014-11-01T13:45:42+00:00\"\n" +
                "}, {\n" +
                "  \"user_id\" : 3,\n" +
                "  \"first_name\" : \"David\",\n" +
                "  \"last_name\" : \"Payne\",\n" +
                "  \"username\" : \"Dpayne\",\n" +
                "  \"user_type\" : \"Manager\",\n" +
                "  \"last_login_time\" : \"2014-09-23T09:35:02+01:00\"\n" +
                "}, {\n" +
                "  \"user_id\" : 4,\n" +
                "  \"first_name\" : \"Joe\",\n" +
                "  \"last_name\" : \"Public\",\n" +
                "  \"username\" : \"joey99\",\n" +
                "  \"user_type\" : \"Employee\",\n" +
                "  \"last_login_time\" : \"2014-09-22T08:23:54+01:00\"\n" +
                "}, {\n" +
                "  \"user_id\" : 5,\n" +
                "  \"first_name\" : \"Gary\",\n" +
                "  \"last_name\" : \"May\",\n" +
                "  \"username\" : \"gary23\",\n" +
                "  \"user_type\" : \"Manager\",\n" +
                "  \"last_login_time\" : \"2015-01-02T12:09:35+00:00\"\n" +
                "}, {\n" +
                "  \"user_id\" : 6,\n" +
                "  \"first_name\" : \"Jessica\",\n" +
                "  \"last_name\" : \"James\",\n" +
                "  \"username\" : \"jj56\",\n" +
                "  \"user_type\" : \"Employee\",\n" +
                "  \"last_login_time\" : \"2015-01-13T08:56:12+00:00\"\n" +
                "}, {\n" +
                "  \"user_id\" : 7,\n" +
                "  \"first_name\" : \"Frank\",\n" +
                "  \"last_name\" : \"Bruno\",\n" +
                "  \"username\" : \"franky\",\n" +
                "  \"user_type\" : \"employee\",\n" +
                "  \"last_login_time\" : \"2015-01-12T16:15:43+00:00\"\n" +
                "}, {\n" +
                "  \"user_id\" : 8,\n" +
                "  \"first_name\" : \"Bob\",\n" +
                "  \"last_name\" : \"Marley\",\n" +
                "  \"username\" : \"bobby3\",\n" +
                "  \"user_type\" : \"Manager\",\n" +
                "  \"last_login_time\" : \"2015-02-01T13:01:00+00:00\"\n" +
                "}, {\n" +
                "  \"user_id\" : 9,\n" +
                "  \"first_name\" : \"Shaun\",\n" +
                "  \"last_name\" : \"Stevens\",\n" +
                "  \"username\" : \"shaun\",\n" +
                "  \"user_type\" : \"Employee\",\n" +
                "  \"last_login_time\" : \"2015-02-01T13:00:00+00:00\"\n" +
                "}, {\n" +
                "  \"user_id\" : 10,\n" +
                "  \"first_name\" : \"Ruby\",\n" +
                "  \"last_name\" : \"Wax\",\n" +
                "  \"username\" : \"ruby\",\n" +
                "  \"user_type\" : \"Employee\",\n" +
                "  \"last_login_time\" : \"2014-12-12T08:09:13+00:00\"\n" +
                "} ]"
    }

    String getExpectedCsv() {
        return "User ID,First Name,Last Name,Username,User Type,Last Login Time\n" +
                "1,John,Doe,John1,Employee,2015-01-12T12:01:34+00:00\n" +
                "2,Mary,Jane,Mary23,Employee,2014-11-01T13:45:42+00:00\n" +
                "3,David,Payne,Dpayne,Manager,2014-09-23T09:35:02+01:00\n" +
                "4,Joe,Public,joey99,Employee,2014-09-22T08:23:54+01:00\n" +
                "5,Gary,May,gary23,Manager,2015-01-02T12:09:35+00:00\n" +
                "6,Jessica,James,jj56,Employee,2015-01-13T08:56:12+00:00\n" +
                "7,Frank,Bruno,franky,employee,2015-01-12T16:15:43+00:00\n" +
                "8,Bob,Marley,bobby3,Manager,2015-02-01T13:01:00+00:00\n" +
                "9,Shaun,Stevens,shaun,Employee,2015-02-01T13:00:00+00:00\n" +
                "10,Ruby,Wax,ruby,Employee,2014-12-12T08:09:13+00:00\n"
    }

    String getExpectedXml() {
        return "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<users>\n" +
                "  <user>\n" +
                "    <userid>1</userid>\n" +
                "    <firstname>John</firstname>\n" +
                "    <surname>Doe</surname>\n" +
                "    <username>John1</username>\n" +
                "    <type>Employee</type>\n" +
                "    <lastlogintime>2015-01-12T12:01:34+00:00</lastlogintime>\n" +
                "  </user>\n" +
                "  <user>\n" +
                "    <userid>2</userid>\n" +
                "    <firstname>Mary</firstname>\n" +
                "    <surname>Jane</surname>\n" +
                "    <username>Mary23</username>\n" +
                "    <type>Employee</type>\n" +
                "    <lastlogintime>2014-11-01T13:45:42+00:00</lastlogintime>\n" +
                "  </user>\n" +
                "  <user>\n" +
                "    <userid>3</userid>\n" +
                "    <firstname>David</firstname>\n" +
                "    <surname>Payne</surname>\n" +
                "    <username>Dpayne</username>\n" +
                "    <type>Manager</type>\n" +
                "    <lastlogintime>2014-09-23T09:35:02+01:00</lastlogintime>\n" +
                "  </user>\n" +
                "  <user>\n" +
                "    <userid>4</userid>\n" +
                "    <firstname>Joe</firstname>\n" +
                "    <surname>Public</surname>\n" +
                "    <username>joey99</username>\n" +
                "    <type>Employee</type>\n" +
                "    <lastlogintime>2014-09-22T08:23:54+01:00</lastlogintime>\n" +
                "  </user>\n" +
                "  <user>\n" +
                "    <userid>5</userid>\n" +
                "    <firstname>Gary</firstname>\n" +
                "    <surname>May</surname>\n" +
                "    <username>gary23</username>\n" +
                "    <type>Manager</type>\n" +
                "    <lastlogintime>2015-01-02T12:09:35+00:00</lastlogintime>\n" +
                "  </user>\n" +
                "  <user>\n" +
                "    <userid>6</userid>\n" +
                "    <firstname>Jessica</firstname>\n" +
                "    <surname>James</surname>\n" +
                "    <username>jj56</username>\n" +
                "    <type>Employee</type>\n" +
                "    <lastlogintime>2015-01-13T08:56:12+00:00</lastlogintime>\n" +
                "  </user>\n" +
                "  <user>\n" +
                "    <userid>7</userid>\n" +
                "    <firstname>Frank</firstname>\n" +
                "    <surname>Bruno</surname>\n" +
                "    <username>franky</username>\n" +
                "    <type>employee</type>\n" +
                "    <lastlogintime>2015-01-12T16:15:43+00:00</lastlogintime>\n" +
                "  </user>\n" +
                "  <user>\n" +
                "    <userid>8</userid>\n" +
                "    <firstname>Bob</firstname>\n" +
                "    <surname>Marley</surname>\n" +
                "    <username>bobby3</username>\n" +
                "    <type>Manager</type>\n" +
                "    <lastlogintime>2015-02-01T13:01:00+00:00</lastlogintime>\n" +
                "  </user>\n" +
                "  <user>\n" +
                "    <userid>9</userid>\n" +
                "    <firstname>Shaun</firstname>\n" +
                "    <surname>Stevens</surname>\n" +
                "    <username>shaun</username>\n" +
                "    <type>Employee</type>\n" +
                "    <lastlogintime>2015-02-01T13:00:00+00:00</lastlogintime>\n" +
                "  </user>\n" +
                "  <user>\n" +
                "    <userid>10</userid>\n" +
                "    <firstname>Ruby</firstname>\n" +
                "    <surname>Wax</surname>\n" +
                "    <username>ruby</username>\n" +
                "    <type>Employee</type>\n" +
                "    <lastlogintime>2014-12-12T08:09:13+00:00</lastlogintime>\n" +
                "  </user>\n" +
                "</users>\n"
    }

}
