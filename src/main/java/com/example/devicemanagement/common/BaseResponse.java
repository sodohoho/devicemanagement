package com.example.devicemanagement.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    private String code;
    private T data;
    private List<String> error;

    public static <T> BaseResponse<T> error(List<String> allErrors) {
        return new BaseResponse<T>(ApiConstant.DEFAULT_ERROR_CODE, null, allErrors);
    }

    public static <T> BaseResponse<T> ok(T deviceRequestDTO) {
        return new BaseResponse<T>(ApiConstant.DEFAULT_SUCCESS_CODE, deviceRequestDTO, null);
    }

}
