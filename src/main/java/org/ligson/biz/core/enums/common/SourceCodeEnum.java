package org.ligson.biz.core.enums.common;

/**
 * 平台枚举类
 *
 */
public enum SourceCodeEnum {
	
	
	PC("PC","电脑端"),
	IOS("IOS","移动苹果端"),
	ANDROID("ANDROID","移动安卓端"),
	HOMECARE("HOMECARE","居家云"),
	
	;
	SourceCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

    private String msg;

    private String code;

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

}
