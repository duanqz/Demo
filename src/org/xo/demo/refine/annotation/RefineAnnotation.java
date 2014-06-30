package org.xo.demo.refine.annotation;

import java.lang.annotation.ElementType;


public class RefineAnnotation {

	public void func() {
		System.out.print("Func");
	}

	@BaiduHook(note="Extend function", type=ElementType.METHOD)
	public void extFunc() {
		System.out.print("extFunc");
	}
}
