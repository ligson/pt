package org.ligson.biz.core.enums.common;

/**
 * 应用系统代码 枚举类
 * 
 * @author ranbaosheng
 *
 */
public enum AppCodeEnum {
	
	
	//个人
	CARNATION(1, "康乃馨"), 
	NURSE(2, "护工通用"),
	NURSE_BAOLI(3,"护工保利"),
	
	
	//企业
	HOMECARE(4,"居家云"),
	ORGCARE(5,"机构养老"),
	KLH(6,"康老汇"),
	
	IDATA(7,"健康数据中心"),
	
	ALL(0,"所有"),

	;

	private Integer code;

	private String msg;

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	AppCodeEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static void main(String args[]) {
		System.out.println(AppCodeEnum.CARNATION.name());
		System.out.println(AppCodeEnum.CARNATION.getCode());
		System.out.println(AppCodeEnum.CARNATION.getMsg());
	}

}
