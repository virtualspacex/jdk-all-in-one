package com.virtualspacex.annotation.interpreter.aspects;

import java.lang.reflect.Method;
import java.math.BigInteger;

import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.AspectNodeInvokeThrowable;
import com.virtualspacex.model.MethodPerformanceInfo;
import com.virtualspacex.model.PerformanceInfo;

import net.sf.cglib.proxy.MethodProxy;

public class PerformanceMonitorAspect extends AspectNode {

    @Override
    public Object invoke(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws AspectNodeInvokeThrowable {

                String clazzAndMethodName = method.getDeclaringClass().toString() + method.getName();
                MethodPerformanceInfo mInfo = PerformanceInfo.get(clazzAndMethodName);
                if(null == mInfo){
                    mInfo = new MethodPerformanceInfo();
                }

                long begintime = System.currentTimeMillis();

                Object result = null;

                try {
                    result = this.next(o, method, objects, methodProxy);
                } catch (AspectNodeInvokeThrowable e) {
                    mInfo.errCost(BigInteger.valueOf(System.currentTimeMillis() - begintime));
                    PerformanceInfo.set(clazzAndMethodName, mInfo);
                    throw e;
                }

                mInfo.cost(BigInteger.valueOf(System.currentTimeMillis() - begintime));
                PerformanceInfo.set(clazzAndMethodName, mInfo);

                return result;
    }
    
}