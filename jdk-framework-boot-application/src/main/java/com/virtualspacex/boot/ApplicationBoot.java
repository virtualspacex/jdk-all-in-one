/*
 * @Author: your name
 * @Date: 2020-12-16 19:15:08
 * @LastEditTime: 2020-12-29 09:45:46
 * @LastEditors: your name
 * @Description: In User Settings Edit
 * @FilePath: \jdk-all-in-one\jdk-framework-boot-application\src\main\java\com\virtualspacex\boot\ApplicationBoot.java
 */
package com.virtualspacex.boot;

import com.virtualspacex.middleware.SYS;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.listener.EventCenter;
import com.virtualspacex.middleware.loader.AdvancedInstanceCreator;

public class ApplicationBoot {

	public static void run(Class<?> clazz) {
		try {
			EventCenter.emit(SYS.ON);
			// AnnotationEngine.loadInterpreters("com.virtualspacex.annotation.interpreter");
			// AnnotationEngine.loadClass(clazz);

			AdvancedInstanceCreator.loadInterpreters();
			AdvancedInstanceCreator.from(clazz);

			EventCenter.emit(SYS.OFF);
		} catch (InterpreAnnotationException e) {
			e.printStackTrace();
		}
	}
}

