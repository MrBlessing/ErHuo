package yuol.secondary.market.erhuo.bean;

import java.util.List;

public class Result {

    /**
     * code : 123
     * message : 恭喜你，登录成功!
     * data : []
     */

    private int code;
    private String message;
    private List<Object> data;

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

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
