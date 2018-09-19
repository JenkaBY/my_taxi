package com.exposit.my_taxi.service.user.dto;

public class ResultDto {
    private String result;
    private final static String SUCCESS = "success";
    private final static String FAIL = "failure";


    public ResultDto() {
    }

    public ResultDto(boolean activated) {
        result = activated ? SUCCESS : FAIL;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
