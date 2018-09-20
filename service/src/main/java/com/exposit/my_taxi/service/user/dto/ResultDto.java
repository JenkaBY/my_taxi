package com.exposit.my_taxi.service.user.dto;

public class ResultDto {
    private Result result;
    private int code;
    private String message;

    public ResultDto() {
    }

    public ResultDto(boolean activated) {
        result = activated ? Result.SUCCESS : Result.FAILURE;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result.toString().toLowerCase();
    }

    public void setResult(String result) {
        this.result = Result.valueOf(result.toUpperCase());
    }

    public void setResult(Result result) {
        this.result = result;
    }

    private enum Result {
        SUCCESS, FAILURE
    }
}
