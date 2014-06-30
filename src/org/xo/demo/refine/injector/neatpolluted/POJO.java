package org.xo.demo.refine.injector.neatpolluted;

public class POJO {

	private Object mObj;
	
	private int mInt;
	
	private String mString = "Neat Field";

	private int mExt = 3;

	private static String mStaticExt = "Extended Field"; 


	public Object getmObj() {
		return mObj;
	}

	public void setmObj(Object mObj) {
		this.mObj = mObj;
	}

	public int getmInt() {
		return mInt;
	}

	public void setmInt(int mInt) {
		this.mInt = mInt;
	}

	public String getmString() {
		return mString;
	}

	public void setmString(String mString) {
		this.mString = mString;

		setExt(3);
		setStaticExt("New Static Jack");
	}

	public int getExt() {
		return mExt;
	}

	public void setExt(int ext) {
		mExt = ext;

		mString = "ext set to be " + ext;
	}

	public static String getStaticExt() {
		return mStaticExt;
	}

	public static void setStaticExt(String str) {
		mStaticExt = str;
	}
}
