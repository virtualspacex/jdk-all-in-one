/*
 * @Author: keiki
 * @Date: 2020-12-20 12:47:32
 * @LastEditTime: 2021-01-01 15:49:14
 * @LastEditors: keiki
 * @Description: 
 */
package cn.com.virtualspacex.model;

import java.util.HashMap;
import java.util.Map;

public class BatchDynamicConfigutation implements BatchDynamicConfigutationMBean{

	private Map<String, String> a = new HashMap<>();
	
	private String b = "i am B";
	
	public BatchDynamicConfigutation() {
		a.put("AKey", "AValue");
	}
	
	@Override
	public void SetA(Map<String, String> hello) {
		a = hello;
	}

	@Override
	public Map<String, String> getA() {
		return a;
	}

	@Override
	public void SetB(String str) {
		b = str;
	}

	@Override
	public String getB() {
		return b;
	}
	
}