package ryan.phan.starter.exception;

import lombok.Getter;
import ryan.phan.starter.constant.common.ResponseCode;

@Getter
public class GlobalAppException extends RuntimeException {
    private final ResponseCode code;

    public GlobalAppException(ResponseCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public GlobalAppException(String message) {
        super(message);
        this.code = ResponseCode.INTERNAL_ERROR;
    }
}
