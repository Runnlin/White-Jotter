package io.github.runnlin.result;

public class Result {
    // response code
    private int code;
    public Result(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
