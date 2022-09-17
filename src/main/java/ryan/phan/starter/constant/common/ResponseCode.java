package ryan.phan.starter.constant.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK(200, "Ok"),
    CREATED(201, "Created"),
    BAD_REQUEST(-400, "Bad request"),
    UNAUTHORIZED(-401, "Unauthorized"),
    NOT_FOUND(-404, "Not found"),
    INTERNAL_ERROR(-500, "Internal error"),
    ACCESS_DENIED(-503, "Access denied"),
    UNSUPPORTED_OP(-600, "Unsupported operation"),
    INVALID_OP(-601, "Invalid operation"),

    NOT_FOUND_USER(-404, "User not found");

    private final int code;
    private final String message;
}
