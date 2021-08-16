package com.librarty.book.constance;

public class AppConstance {
    public static final class ErrorConstance {
        public static final String BOOK_NOT_UPDATE = "Book Not Update";
    }

    public static final class NotFoundConstance {
        public static final String BOOK_NOT_FOUND = "Book Not Found";
        public static final String PACKAGE_NOT_FOUND = "Package Not Found";
    }

    public static final class DuplicatedConstance {
        public static final String BOOK_ALREADY_EXISTS = "Book already exists";
        public static final String PACKAGE_ALREADY_EXISTS = "Package already exists";
        public static final String USER_ALREADY_EXISTS = "User already exists";
    }

    public static final class DetailConstance {
        public static final String BOOK_ADDED = "Book added successful";
        public static final String BOOK_UPDATED = "Book successfully updated";
        public static final String BOOK_DELETED = "Book successfully deleted";

        public static final String PACKAGE_ADDED = "Package added successful";
        public static final String PACKAGE_UPDATED = "Package successfully updated";
        public static final String PACKAGE_DELETED = "Package successfully deleted";

        public static final String BOOK_ADD_FAILED = "Sorry!Book add failed";
        public static final String BOOK_DELETE_FAILED = "Sorry!Book delete failed";
        public static final String BOOK_UPDATE_FAILED = "Sorry!Book update failed";
    }

    public static final class ApplicationErrors{
        public static final String APPLICATION_ERROR_OCCURRED_MESSAGE = "Unexpected Error Occurred";
    }

    public static final class ErrorCodeConstance{
        public static final int RESOURCE_NOT_FOUND = 404;
        public static final int RESOURCE_ALREADY_EXISTS = 403;
        public static final int PASSWORD_FORMAT_WRONG = 501;
        public static final int EMAIL_DUPLICATE = 502;
    }
}
