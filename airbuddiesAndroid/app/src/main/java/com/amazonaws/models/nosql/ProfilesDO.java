package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "airbuddies-mobilehub-1822139899-Profiles")

public class ProfilesDO {
    private String _userId;
    private String _firstName;
    private String _age;
    private String _description;
    private String _lastName;
    private String _picture;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBRangeKey(attributeName = "First Name")
    @DynamoDBAttribute(attributeName = "First Name")
    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(final String _firstName) {
        this._firstName = _firstName;
    }
    @DynamoDBAttribute(attributeName = "Age")
    public String getAge() {
        return _age;
    }

    public void setAge(final String _age) {
        this._age = _age;
    }
    @DynamoDBAttribute(attributeName = "Description")
    public String getDescription() {
        return _description;
    }

    public void setDescription(final String _description) {
        this._description = _description;
    }
    @DynamoDBAttribute(attributeName = "Last Name")
    public String getLastName() {
        return _lastName;
    }

    public void setLastName(final String _lastName) {
        this._lastName = _lastName;
    }
    @DynamoDBAttribute(attributeName = "Picture")
    public String getPicture() {
        return _picture;
    }

    public void setPicture(final String _picture) {
        this._picture = _picture;
    }

}
