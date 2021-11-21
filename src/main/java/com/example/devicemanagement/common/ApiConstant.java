package com.example.devicemanagement.common;

import java.time.LocalDateTime;

public class ApiConstant {

    public static String DEFAULT_SUCCESS_CODE = "S01";

    public static String DEFAULT_ERROR_CODE = "E01";

    public static LocalDateTime MAX_DATE = LocalDateTime.of(9999, 12, 30, 0, 0, 0);

    public static LocalDateTime MIN_DATE = LocalDateTime.of(1994, 02, 23, 0, 0, 0);
}
