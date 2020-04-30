package com.thecrunchycorner.backend.formats.xml;

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
public class XmlDefinition implements Serializable, Definition {

    @JsonProperty("userid")
    private int userId;

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("surname")
    private String lastName;

    @JsonProperty("username")
    private String userName;

    @JsonProperty("type")
    private String userType;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty("lastlogintime")
    private ZonedDateTime lastLogin;

    public XmlDefinition() {
    }

    XmlDefinition(Definition otherType) {
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
