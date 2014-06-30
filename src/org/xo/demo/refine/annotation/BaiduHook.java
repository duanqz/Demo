package org.xo.demo.refine.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface BaiduHook {

	// Description
	String note();

	// Type of annotation target
	ElementType type();
}
