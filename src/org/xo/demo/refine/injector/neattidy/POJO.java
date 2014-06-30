package org.xo.demo.refine.injector.neattidy;

public class POJO {

	private Object mObj;
	
	private int mInt;

	private String mString = "Neat Field";

	private int mExt;

	static class Injector {
		private static String mStaticExt = "Extended Field";

		public static void initExtField(POJO pojo) {
			pojo.mExt = 3;
		}

		public static int getExt(POJO pojo) {
			return pojo.mExt;
		}

		public static void setExt(POJO pojo, int ext) {
			pojo.mExt = ext;

			// Here we want to invoke object field.
			pojo.mString = "Ext set to be " + pojo.mExt;
		}

		public static String getStaticExt() {
			return mStaticExt;
		}
		
		public static void setStaticExt(String str) {
			mStaticExt = str;
		}
	}


	public POJO() {
		Injector.initExtField(this);
	}

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

		Injector.setExt(this, 3);
		Injector.setStaticExt("New Static Jack");
	}

}
