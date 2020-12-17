package cn.com.virtualspacex.exception;

import org.apache.log4j.Logger;

import cn.com.virtualspacex.constants.Constant;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 未捕捉到的异常的处理类
 *
 * @author yao-chaochao
 * @version 1.0
 * @date 2020/09/17
 */
public class BatchUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    /**
     * 处理未捕捉到的异常
     *
     * @param t 线程
     * @param e 异常
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String stackInfo;
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            e.printStackTrace(pw);
            stackInfo = sw.toString();
        }
        Logger.getLogger(Constant.LOGGER_CONTAINER).error(stackInfo,e);
    }
}
