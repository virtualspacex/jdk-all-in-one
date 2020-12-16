package com.virtualspacex.model;

import java.util.HashMap;
import java.util.Map;

public class PerformanceInfo {
    private static Map<String, MethodPerformanceInfo> methodMap = new HashMap<String, MethodPerformanceInfo>();

    public static MethodPerformanceInfo get(String clazzAndMethodName){
        return methodMap.get(clazzAndMethodName);
    }

    public static void set(String clazzAndMethodName, MethodPerformanceInfo mInfo){
        methodMap.put(clazzAndMethodName, mInfo);
    }

    public static Map<String, MethodPerformanceInfo> getResult(){
        return methodMap;
    }

    public String result(){
        return methodMap.toString();
    }
}