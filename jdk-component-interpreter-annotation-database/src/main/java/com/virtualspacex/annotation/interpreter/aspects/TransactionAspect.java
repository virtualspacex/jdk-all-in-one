/*
 * @Author: your name
 * @Date: 2020-10-10 16:15:06
 * @LastEditTime: 2020-10-11 20:18:14
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/proxy/handlers/TransactionHandler.java
 */
package com.virtualspacex.annotation.interpreter.aspects;

import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.AspectNodeInvokeThrowable;
import com.virtualspacex.util.database.DatabaseUtilFactory;

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
public class TransactionAspect extends AspectNode{

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

	@Override
    public Object invoke(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws AspectNodeInvokeThrowable {
		Object result = null;

		if(null != objects && objects.length > 0) {
			
			Object session = null;
			Class<?> c = DatabaseUtilFactory.getExecutorType();
			
			for(Object object : objects) {
				if(object.getClass().getName().equals(c.getName())) {
					session = object;
				}
			}
			
			if(null != session) {
				try {
					Object t = DatabaseUtilFactory.transaction(session);
					try {
			            result = this.next(o, method, objects, methodProxy);
			            DatabaseUtilFactory.commit(t);
	
					} catch (Exception e) {
						result = null;
						DatabaseUtilFactory.rollback(t);
					}
				} catch (Exception e) {
					throw new AspectNodeInvokeThrowable("@AutoTransaction failed to open transaction or rollback.", e);
				}
			} else {
				throw new AspectNodeInvokeThrowable("@AutoTransaction Can not find org.hibernate.Session.");
			}
		} else {
			throw new AspectNodeInvokeThrowable("@AutoTransaction Can not find org.hibernate.Session.");
		}
		
        return result;
    }
}