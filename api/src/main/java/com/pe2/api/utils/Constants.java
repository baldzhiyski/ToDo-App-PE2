package com.pe2.api.utils;

public enum Constants {
    ;

    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.uni-stuttgart\\.de$";


    public static final String TODO_NOT_FOUND_MESSAGE = "ToDo with id %d not in the DB!";

    public static final String ASSIGNEE_NOT_FOUND_MESSAGE = "Assignee with id %d does not exist in the DB!";

    public static final String INVALID_EMAIL = "Invalid email. Must be a valid email ending with @uni-stuttgart.de";
}
