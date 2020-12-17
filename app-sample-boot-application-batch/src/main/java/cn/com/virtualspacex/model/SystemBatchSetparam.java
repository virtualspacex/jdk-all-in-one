package cn.com.virtualspacex.model;

import java.io.Serializable;

public class SystemBatchSetparam implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
    private String paramId;
    private String paramValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}
