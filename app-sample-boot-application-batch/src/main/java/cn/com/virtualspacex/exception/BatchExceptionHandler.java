package cn.com.virtualspacex.exception;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import cn.com.virtualspacex.constants.Constant;

import com.virtualspacex.annotation.ExceptionHandler;
import com.virtualspacex.exception.handler.DefaultExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

/**
 * @author yao-chaochao
 * @version 1.0
 * @date 2020/09/17
 */
public class BatchExceptionHandler extends DefaultExceptionHandler {
    /**
     * 捕捉并处理Exception异常
     *
     * @param e Exception异常
     */
    @ExceptionHandler(Exception.class)
    public void exception(Exception e) {
        Logger.getLogger(Constant.LOGGER_CONTAINER).error(eToString(e),e);
    }

    /**
     * 捕捉并处理HibernateException异常
     *
     * @param e HibernateException异常
     */
    @ExceptionHandler(HibernateException.class)
    public void exception(HibernateException e) {
        Logger.getLogger(Constant.LOGGER_CONTAINER).error(eToString(e),e);
    }

    /**
     * 捕捉并处理SQLException异常
     *
     * @param e SQLException异常
     */
    @ExceptionHandler(SQLException.class)
    public void exception(SQLException e) {
        Logger.getLogger(Constant.LOGGER_CONTAINER).error(eToString(e),e);
    }

    /**
     * 捕捉并处理NullPointerException异常
     *
     * @param e NullPointerException异常
     */
    @ExceptionHandler(NullPointerException.class)
    public void exception(NullPointerException e) {
        Logger.getLogger(Constant.LOGGER_CONTAINER).error(eToString(e),e);
    }

    private String eToString(Throwable e) {
        String stackInfo;
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            e.printStackTrace(pw);
            stackInfo = sw.toString();
        }
        return stackInfo;
    }
}
