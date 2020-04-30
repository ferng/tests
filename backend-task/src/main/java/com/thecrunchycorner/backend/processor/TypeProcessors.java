package com.thecrunchycorner.backend.processor;

import com.thecrunchycorner.backend.formats.Reader;
import com.thecrunchycorner.backend.formats.Writer;
import com.thecrunchycorner.backend.formats.csv.CsvReader;
import com.thecrunchycorner.backend.formats.csv.CsvWriter;
import com.thecrunchycorner.backend.formats.json.JsonReader;
import com.thecrunchycorner.backend.formats.json.JsonWriter;
import com.thecrunchycorner.backend.formats.xml.XmlReader;
import com.thecrunchycorner.backend.formats.xml.XmlWriter;

/*
    Each file type needs it's own reader and writer. These are held here for easy access.

    For any new files types create a new format package with:
        Reader
        Writer
        Record definition
    Then:
        And add it to this enum
        And potentially update MainController.getTypeProcessor() with any appropriate business
        logic allowing it to identify the correct file format

 */
public enum TypeProcessors {
    JSON_FORMATTER(new JsonReader(), new JsonWriter()),
    CSV_FORMATTER(new CsvReader(), new CsvWriter()),
    XML_FORMATTER(new XmlReader(), new XmlWriter());

    private Reader reader;
    private Writer writer;

    TypeProcessors(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    Reader getReader() {
        return this.reader;
    }

    Writer getWriter() {
        return this.writer;
    }
}
