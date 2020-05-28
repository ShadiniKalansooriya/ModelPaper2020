package com.example.modelpaper2020.Database;

import android.provider.BaseColumns;

public final class UserProfile {

    public UserProfile() {
    }

    public static class Users implements BaseColumns {
        public static final String TABLE_USERS = "users";
        public static final String COLOUMN_NAME_USERNAME = "userName";
        public static final String COLOUMN_NAME_PASSWORD = "password";
        public static final String COLOUMN_NAME_DOB = "dateOfBirth";
        public static final String COLOUMN_NAME_GENDER = "Gender";
    }
}
