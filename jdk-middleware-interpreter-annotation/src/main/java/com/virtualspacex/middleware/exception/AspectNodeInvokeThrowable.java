/*
 * @Author: your name
 * @Date: 2020-10-11 11:10:42
 * @LastEditTime: 2020-12-14 22:58:16
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/exception/AspectNodeInvokeException.java
 */
package com.virtualspacex.middleware.exception;

public class AspectNodeInvokeThrowable extends Throwable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AspectNodeInvokeThrowable(){

    }
    
    public AspectNodeInvokeThrowable(String message){
        super(message);
    }

    public AspectNodeInvokeThrowable(Throwable cause){
        super(cause);
    }

    public AspectNodeInvokeThrowable(String message, Throwable cause){
        super(message, cause);
    }
    
}
