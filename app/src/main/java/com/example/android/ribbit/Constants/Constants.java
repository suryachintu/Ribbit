package com.example.android.ribbit.Constants;

import com.example.android.ribbit.BuildConfig;

/**
 * Created by surya on 06-06-2016.
 */
public class Constants {
    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    private static final String USERS = "users";
    private static final String RELATION = "relation";
    public static final String FIREBASE_URL_USER = FIREBASE_URL + USERS;
    public static final String FIREBASE_URL_USER_RELATION = FIREBASE_URL + USERS + RELATION;
}
