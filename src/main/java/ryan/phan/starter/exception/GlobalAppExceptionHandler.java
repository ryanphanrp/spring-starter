package ryan.phan.starter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ryan.phan.starter.constant.common.ResponseCode;
import ryan.phan.starter.dto.common.ResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestControllerAdvice
public class GlobalAppExceptionHandler {

    @ExceptionHandler({GlobalAppException.class})
    @ResponseStatus(HttpStatus.GONE)
    public ResponseDto<?> globalAppHandler(HttpServletRequest req, GlobalAppException exp) {
        return new ResponseDto<>(exp.getCode());
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleBindException(BindException ex) {
        final FieldError fieldError = ex.getFieldError();
        if (Objects.nonNull(fieldError)) {
            return new ResponseDto<>(ResponseCode.BAD_REQUEST, fieldError.getDefaultMessage());
        }
        return new ResponseDto<>(ResponseCode.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto<?> handleUncaughtException(Exception ex) {
        ex.printStackTrace();
        return new ResponseDto<>(ResponseCode.INTERNAL_ERROR, ex.getMessage());
    }

}
