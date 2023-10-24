package com.kr.matitting.exception.Map;

import com.kr.matitting.exception.BaseException;
import com.kr.matitting.exception.BaseExceptionType;

public class MapException extends BaseException {
    private BaseExceptionType exceptionType;

    public MapException(MapExceptionType baseException){
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}