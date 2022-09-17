package ryan.phan.starter.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ryan.phan.starter.constant.common.ResponseCode;

@Data
@Builder
@AllArgsConstructor
public class ResponseDto<T> {

    private int code;
    private String message;
    private T body;

    public ResponseDto(ResponseCode resCode, T body) {
        this.body = body;
        this.code = resCode.getCode();
        this.message = resCode.getMessage();
    }

    public ResponseDto(ResponseCode resCode) {
        this.message = resCode.getMessage();
        this.code = resCode.getCode();
    }
}
