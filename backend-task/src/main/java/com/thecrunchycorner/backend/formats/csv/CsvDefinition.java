package com.thecrunchycorner.backend.formats.csv;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thecrunchycorner.backend.formats.Definition;
import com.thecrunchycorner.backend.formats.datehelpers.LocalDateTimeDeserializer;
import com.thecrunchycorner.backend.formats.datehelpers.LocalDateTimeSerializer;
import java.io.Serializable;
import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder( {"User ID", "First Name", "Last Name", "Username", "User Type", "Last Login " +
        "Time"})
public class CsvDefinition implements Serializable, Definition {

    @JsonProperty("User ID")
    private int userId;

    @JsonProperty("First Name")
    private String firstName;

    @JsonProperty("Last Name")
    private String lastName;

    @JsonProperty("Username")
    private String userName;

    @JsonProperty("User Type")
    private String userType;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty("Last Login Time")
    private ZonedDateTime lastLogin;

    public CsvDefinition() {
    }

    CsvDefinition(Definition otherType) {
        this.userId = otherType.getUserId();
        this.firstName = otherType.getFirstName();
        this.lastName = otherType.getLastName();
        this.userName = otherType.getUserName();
        this.userType = otherType.getUserType();
        this.lastLogin = otherType.getLastLogin();
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserType() {
        return userType;
    }

    public ZonedDateTime getLastLogin() {
        return lastLogin;
    }
}
