/*
 * @Author: your name
 * @Date: 2020-10-11 10:46:16
 * @LastEditTime: 2020-10-11 19:56:55
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/exception/InterpreAnnotationException.java
 */
package com.virtualspacex.middleware.exception;

public class InterpreAnnotationException extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InterpreAnnotationException(Exception e){
        super(e);
    }

    public InterpreAnnotationException(){

    }
    
    public InterpreAnnotationException(String message){
        super(message);
    }

    public InterpreAnnotationException(String message, Exception cause){
        super(message, cause);
    }
    
}
