package com.thecrunchycorner.backend.formats;

import java.io.IOException;
import java.util.List;

public interface Reader {
    List<Definition> loadData(String file) throws IOException;
}
