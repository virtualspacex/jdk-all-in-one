package com.virtualspacex.annotation.interpreter.aspects;

import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.AspectNodeInvokeThrowable;
import com.virtualspacex.util.common.CommonUtil;

import net.sf.cglib.proxy.MethodProxy;

import static com.virtualspacex.util.logger.LoggerFactory.*;

import java.lang.reflect.Method;

/**
 * 自動印刷ログプロキシクラス
 *
 * @author yao-chaochao
 * @version 0.0.1
 * @className
 * @description
 * @date 2020/08/26
 * @since JDK8
 */
public class LogAspect extends AspectNode{
	
    private static int stackcount = 0;
    
    private static final String DEBUG_BEGIN_FORMAT = " [%d] [begin] class: %s, method: %s, parameters: %s ";
    
    private static final String DEBUG_END_FORMAT = " [%d] [end] class: %s, method: %s, result: %s ";

    /**
     * プロキシ監視
     *
     * @param o           プロキシオブジェクト
     * @param method      方法
     * @param objects     パラメータ
     * @param methodProxy プロキシ方法
     * @return メソッドの戻り結果
     * @throws Throwable 例外を投げる
     * @since 0.0.1
     */
    public Object invoke(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws AspectNodeInvokeThrowable {

        String classname = method.getDeclaringClass().getName();
        String methodname = method.getName();
        String parameters = getParameters(objects);
        String strReturnValue = "Null";

        int index = getStadkIndex();

        getLogger().debug(String.format(DEBUG_BEGIN_FORMAT, index, classname, methodname, parameters));

        Object result = null;

        try {
            result = this.next(o, method, objects, methodProxy);
        } catch (AspectNodeInvokeThrowable e) {

            getLogger().error(CommonUtil.eToStackString(e));

            throw e;
        }

        if (result != null) {
            strReturnValue = result.toString();
        }
        
        getLogger().debug(String.format(DEBUG_END_FORMAT, index, classname, methodname, strReturnValue));

        return result;
    }

    private String getParameters(Object[] objects) {
        StringBuilder params = new StringBuilder("");

        if (objects.length != 0) {
            for (Object object : objects) {
                if (object != null) {
                    params.append(" " + object.toString());
                } else {
                	params.append(" Null");
                }
            }
        }

        return params.toString();
    }
    
    synchronized private int getStadkIndex() {
    	
        synchronized(this) {
            if (stackcount > 2147483647) {
                stackcount = 0;
            } else {
                stackcount++;
            }
        }
        
        return stackcount;
    }
}