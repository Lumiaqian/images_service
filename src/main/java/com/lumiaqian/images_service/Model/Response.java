package com.lumiaqian.images_service.Model;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: Response
 * @Package: com.lumiaqian.iamges_service.Model
 * @Description: 响应的JSON格式
 * @date 2019-04-28 15:25
 **/
public class Response implements Serializable {

    private String code;
    private String message;
    private Object data;

    public Response() {
        this.setCode(ResultCode.SUCCESS);
        this.setMessage("成功！");

    }

    public Response(ResultCode code) {
        this.setCode(code);
        this.setMessage(code.msg());
    }

    public Response(ResultCode code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public Response(ResultCode code, String message, Object data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code.val();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
