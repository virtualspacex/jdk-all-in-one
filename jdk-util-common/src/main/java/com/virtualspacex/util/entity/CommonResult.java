/*
 * @fileName: FileOperateResult.java
 * @Description: 
 * @date: 2020/4/1
 * @Copyright: Copyright (c) 2020,Fuji Electric (Hangzhou) Software Co., Ltd. All rights reserved.
 */
package com.virtualspacex.util.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: FileOperateResult
 * @Description: 
 * @author: 
 */
public class CommonResult<T> {

	private boolean result = false;
	
	private String code = "";
	
	private List<String> msg = new ArrayList<String>();
	
	private T value;
	
	public boolean result() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String code() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<String> msg() {
		return msg;
	}

	public void setMsg(List<String> msg) {
		this.msg = msg;
	}
	
	public void addMsg(String msg) {
		this.msg.add(msg);
	}

	public T value() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
