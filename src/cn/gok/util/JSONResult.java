package cn.gok.util;

import java.util.List;

/**
 * @Description: 自定义响应数据结构
 * 				200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 */
public class JSONResult {

    // 响应业务状态
    private Integer status;
    
    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;
    
    private String ok;	// 不使用

    public static JSONResult build(Integer status, String msg, Object data) {
        return new JSONResult(status, msg, data);
    }

    /**
     * 响应成功时使用，并附带相关的数据信息给前端
     * @param data
     * @return
     */
    public static JSONResult ok(Object data) {
        return new JSONResult(data);
    }

    /**
     * 响应成功时使用,但是不附带数据给前端
     * @return
     */
    public static JSONResult ok() {
        return new JSONResult(null);
    }
    
    /**
     * 响应失败时使用,并附带相应的错误提示信息给前端
     * @param msg
     * @return
     */
    public static JSONResult errorMsg(String msg) {
        return new JSONResult(500, msg, null);
    }
    
    
    
    public JSONResult() {

    }

    public JSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public JSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

}
