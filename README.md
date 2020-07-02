## Transalate
This application translates text in taken from a file into another language. The translated text
 is then written to a separate file.
 
The application uses google translate.

## Usage
translate [OPTION]

```bash
    -h, --help
            display option / usage help
    -v, --version
            display version number
    -s, --source=LANGUAGE
            language the source text is written in; this option may be ommitted or will be
 ignored if the input file starts with the line:
                --- LANGUAGE: <LANGUAGE> ---
    -t, --target=LANGUAGE
            language to translate the text into
    -i, --input=FILE
            a file containing the text to be translated; may optionally include the line:
                --- LANGUAGE: <LANGUAGE> ---
            to define the source language; if this option is ommitted a file called source.txt
 will be used
    -o, --output=FILE
            this file will be created / overwritten and contain the translated text; if this option is ommitted a file called target.txt will be used
```

### Defaults
* If no source language is specified either as an option or within the input file English will be used as a default
* If no target language is specified either as an option or within the input file Spanish will be used as a default
* If no input file is specified source.txt in the current directory will be read
* If no output file is specified target.txt in the current directory will be created

### Languages
For the purposes of this exercise the following languages can used for both source and
 destination languages, either the ISO code or Language name can be used as a parameter:

|Language|ISO-639-1 Code|
|---|---|
|English|en|
|Icelandic|is|
|Japanese|ja|
|Spanish|es|


## Executing the application
### Pull down dependencies
```bash
mvn clean install
```

### Run all tests
```bash
mvn clean test
```


### Running the program via maven
The program can be run without a full build via maven. This will use the defaults specified above
```bash
mvn exec:java
```
or parameters can be passed
```bash
mvn exec:java -Dexec.args="-s en -t ja -i source.txt -o target.txt"
```


### Creating a standalone app
```bash
#build the jar
mvn clean package

#run the standalone app with the defaults specified above
java -jar target/topcoder_translate-1.0-SNAPSHOT.jar 

#or parameters can be passed
java -jar target/topcoder_translate-1.0-SNAPSHOT.jar -s en -t ja -i source.txt -o target.txt
```