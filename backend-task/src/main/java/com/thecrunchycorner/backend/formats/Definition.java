package com.thecrunchycorner.backend.formats;

import java.time.ZonedDateTime;

public interface Definition {
    int getUserId();

    String getFirstName();

    String getLastName();

    String getUserName();

    String getUserType();

    ZonedDateTime getLastLogin();
}
