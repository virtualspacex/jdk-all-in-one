package com.virtualspacex.boot;

import com.virtualspacex.middleware.SYS;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.listener.EventCenter;
import com.virtualspacex.middleware.loader.AnnotationEngine;

public class ApplicationBoot {

	public static void run(Class<?> clazz) {
		try {
			EventCenter.emit(SYS.ON);
			// AnnotationEngine.loadInterpreters("com.virtualspacex.annotation.interpreter");
			// AnnotationEngine.loadClass(clazz);

			AnnotationEngine.loadInterpreters();
			AnnotationEngine.from(clazz);

			EventCenter.emit(SYS.OFF);
		} catch (InterpreAnnotationException e) {
			e.printStackTrace();
		}
	}
}

