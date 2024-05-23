package com.codejstudio.algi.common.web;

import org.springframework.context.ApplicationContext;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public final class ApplicationContextUtil {

	/* variables */

	private static ApplicationContext applicationContext;


	/* getters & setters */

	public static final ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static final void setApplicationContext(ApplicationContext applicationContext) {
		ApplicationContextUtil.applicationContext = applicationContext;
	}

}
