package com.eri.wechat2;

public class ResponseBase {
    public enum Status {
        OK, FAIL, ERROR, PASS
    }

    private Status status = Status.OK;
    private String errMessage;
    private int errorCode;

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getMessage() {
        return errMessage;
    }
    public void setMessage(String message) {
        this.errMessage = message;
    }
    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
