package com.thecrunchycorner.backend.processor;

import com.thecrunchycorner.backend.formats.CommonDefinition;
import com.thecrunchycorner.backend.formats.Definition;
import com.thecrunchycorner.backend.formats.Writer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
    This spins up the application. The main method could be renamed to simply make it a service
    called by something (if the application were to be deployed somewhere for instance.
 */
public class MainController {
    private static final Logger LOGGER = LogManager.getLogger(MainController.class);

    // We could get the source and destination paths from user input, but as it's just run within
    // an IDE I haven't done that
    public static void main(String[] args) {
        LOGGER.info("System starting up and processing files from {} ", System.getProperty("user" +
                ".dir"));

        String sourceDirName = "data";
        String destinationDirName = "output";

        Path sourceDir = Paths.get(sourceDirName);
        try {
            // We get a list of files to process
            List<String> files = getFiles(sourceDir);

            // We load the data from all the files converting it to a common format to allow the
            // business logic to process the complete set
            List<Definition> sourceData;
            sourceData = loadAllData(sourceDirName, files);
            LOGGER.info(("All data read"));

            // And we sort it (the business logic)
            sourceData.sort(null);
            LOGGER.info(("Data sorted"));

            // Create a directory for the output
            if (!createOutputDir(destinationDirName)) {
                throw new IOException("Could not create output directory");
            }

            // And write all the data, each writer knows how to write it's own data
            saveAllData(destinationDirName, files, sourceData);
            LOGGER.info(("All data saved"));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    /* Go through the input directory and get a list of all the files */
    static List<String> getFiles(Path sourceDir) throws IOException {
        Stream<Path> stream = Files.walk(sourceDir, Integer.MAX_VALUE);
        return stream
                .map(Path::getFileName)
                .map(String::valueOf)
                .filter((name) -> name.lastIndexOf('.') > 0)
                .collect(Collectors.toList());
    }


    /*
        We have a list of files to process
     */
    static List<Definition> loadAllData(String sourceDirName, List<String> files) {
        List<Definition> sourceData = new ArrayList<>();

        files.forEach((String fileName) -> {
            // we get the appropriate processor for each of the file types
            TypeProcessors typeProcessor = getTypeProcessor(fileName);
            if (typeProcessor != null) {
                try {
                    // we load the data
                    List<Definition> rawData =
                            typeProcessor.getReader().loadData(sourceDirName + "/" + fileName);

                    // and we convert it to our common format
                    rawData.forEach((Definition record) -> sourceData.add(new CommonDefinition(record)));
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        });
        return sourceData;
    }


    /*
       Pretty much the inverse of the above method. We have the list of files to write.
       Although very similar to the above and we could have parameterized the behaviour to
       READ/WRITE. I prefer having distinct methods for such different behaviour, it's just
       clearer.
     */
    static void saveAllData(String destDirName, List<String> files, List<Definition> data) {
        files.forEach((String fileName) -> {
            // we get the appropriate processor for each of the file types
            TypeProcessors typeProcessor = getTypeProcessor(fileName);
            if (typeProcessor != null) {
                try {
                    Writer writer = typeProcessor.getWriter();

                    // and we write the data
                    writer.saveData(destDirName + "/" + fileName, data);
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        });
    }


    /*
    Simple utility to create or empty the output directory
     */
    static boolean createOutputDir(String destinationDirName) {
        File outDir = new File(destinationDirName);
        if (outDir.exists()) {
            File[] existingFiles = outDir.listFiles();
            if (existingFiles != null) {
                for (File file : existingFiles) {
                    file.delete();
                }
            }
            return true;
        } else {
            return outDir.mkdir();
        }
    }


    /* Right now we only use the extension to determine the file type. But changing this logic
    would refine the process according to whatever business criteria dictated.
     */
    static TypeProcessors getTypeProcessor(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase();
        try {
            return TypeProcessors.valueOf(extension + "_FORMATTER");
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Unkown file type for file {} ", fileName);
            return null;
        }
    }
}
