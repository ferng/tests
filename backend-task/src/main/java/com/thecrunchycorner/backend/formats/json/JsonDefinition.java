package com.thecrunchycorner.backend.formats.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thecrunchycorner.backend.formats.Definition;
import com.thecrunchycorner.backend.formats.datehelpers.LocalDateTimeDeserializer;
import com.thecrunchycorner.backend.formats.datehelpers.LocalDateTimeSerializer;
import java.io.Serializable;
import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonDefinition implements Serializable, Definition {

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("username")
    private String userName;

    @JsonProperty("user_type")
    private String userType;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty("last_login_time")
    private ZonedDateTime lastLogin;

    public JsonDefinition() {
    }

    JsonDefinition(Definition otherType) {
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
