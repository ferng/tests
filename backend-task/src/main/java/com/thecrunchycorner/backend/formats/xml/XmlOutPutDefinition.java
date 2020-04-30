package com.thecrunchycorner.backend.formats.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* Simple wrapper class to write all records within a single XML object */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonRootName("users")
class XmlOutPutDefinition implements Serializable {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "user")
    private List<XmlDefinition> definitions = new ArrayList<>();

    void add(XmlDefinition definition) {
        definitions.add(definition);
    }
}
