package cn.com.virtualspacex.model;

import java.util.Map;

public interface BatchDynamicConfigutationMBean{
	
	public void SetA(Map<String, String> hello);
	public Map<String, String> getA();
	public void SetB(String str);
	public String getB();
	
}