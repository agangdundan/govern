package xyz.frt.govern.common;

/**
 * @author phw
 * @date Created in 04-13-2018
 * @description
 */
public class UnauthorizedException extends RuntimeException {

    private Integer code;
    private String msg;

    public UnauthorizedException() { super(); }

    public UnauthorizedException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
