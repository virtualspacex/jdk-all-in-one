/*
 * @Author: your name
 * @Date: 2020-10-11 14:02:32
 * @LastEditTime: 2021-01-01 15:33:43
 * @LastEditors: keiki
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/interpreter/AutoPropertySourceInterpreter.java
 */
package com.virtualspacex.annotation.interpreter;

import java.io.File;

import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.google.auto.service.AutoService;
import com.virtualspacex.annotation.AutoPropertySource;
import com.virtualspacex.annotation.SourceType;
import com.virtualspacex.util.io.file.FileUtil;
import com.virtualspacex.util.property.PropertiesReaderInterface;
import com.virtualspacex.util.property.PropertyManager;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;

// @InterpreterFor(AutoPropertySource.class)
@AutoService({AnnotationInterpreterInterface.class})
@SupportedAnnotationTypes({"com.virtualspacex.annotation.AutoPropertySource"})
public class AutoPropertySourceInterpreter implements AnnotationInterpreterInterface {

    @Override
    public AspectNode enhanceBehaviour(AspectNode existNode, Annotation annotation) throws InterpreAnnotationException {
        return existNode;
    }

    @Override
    public Object enhanceAttribute(Class<?> clazz, Object existInstance, Annotation annotation)
            throws InterpreAnnotationException {
        AutoPropertySource autoPropertySource = (AutoPropertySource) annotation;
        SourceType st = autoPropertySource.type();
        if (st == SourceType.DB) {
        	
        	/* do By JDBC*/
        	
//            String table = autoPropertySource.table();
//            String keyField = autoPropertySource.keyField();
//            String valueField = autoPropertySource.valueField();
//
//            Session session = HibernateUtils.openSession();
//
//            if (null != session) {
//                String sql = "SELECT " + keyField + "," + valueField + " FROM " + table;
//                try {
//                    SQLQuery query = session.createSQLQuery(sql);
//                    List result = query.list();
//                } catch (HibernateException e) {
//                    throw new InterpreAnnotationException(e);
//                }
//            } else {
//                throw new InterpreAnnotationException(" Can not connect to datebase.");
//            }

        } else if (st == SourceType.FILE) {
            String propertyFile = FileUtil.getClassPath() + File.separator + autoPropertySource.filepath();
            Class<?> readerClass = autoPropertySource.reader();
            try {
                PropertiesReaderInterface apr = (PropertiesReaderInterface) readerClass.newInstance();
                apr.loadPropertyFile(propertyFile);
                PropertyManager.add(apr);
            } catch (Exception e) {
                throw new InterpreAnnotationException(e);
            }
        }
        return existInstance;
    }

	@Override
	public boolean autoProxy(Annotation annotation) {
		return false;
	}
}
