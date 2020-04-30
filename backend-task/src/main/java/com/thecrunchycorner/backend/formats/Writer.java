package com.thecrunchycorner.backend.formats;

import java.io.IOException;
import java.util.List;

public interface Writer {
    void saveData(String file, List<Definition> definitions) throws IOException;
}
