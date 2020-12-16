package com.virtualspacex.util.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

public class CommonUtil {
	
	public static <T> T mapToObject(Map<String, Object> m, Class<T> t) throws InstantiationException, IllegalAccessException {

		Field[] fields = t.getDeclaredFields();

		T obj = t.newInstance();
		for (Field field : fields) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
				continue;
			}
			field.setAccessible(true);
			field.set(obj, m.get(field.getName()));
		}

		return obj;
	}
	
	public static String eToStackString(Throwable e) {
        String stackInfo;
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            e.printStackTrace(pw);
            stackInfo = sw.toString();
        }
        return stackInfo;
	}
}
