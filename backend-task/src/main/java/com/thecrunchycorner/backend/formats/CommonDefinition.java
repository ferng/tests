package com.thecrunchycorner.backend.formats;

import java.time.ZonedDateTime;
import java.util.Objects;

public class CommonDefinition implements Definition, Comparable {
    private int userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String userType;
    private ZonedDateTime lastLogin;


    public CommonDefinition(Definition otherType) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommonDefinition commonDefinition = (CommonDefinition) o;
        return userId == commonDefinition.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public int compareTo(Object o) {
        return this.getUserId() - ((CommonDefinition) o).getUserId();
    }
}
