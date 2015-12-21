package org.ligson.biz.core.base;

import org.ligson.biz.core.enums.common.AppCodeEnum;
import org.ligson.biz.core.enums.common.SourceCodeEnum;

/**
 * 请求实体基础类
 * 
 */
@SuppressWarnings("serial")
public class BaseRequestDto extends BaseDto {

	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 应用系统编码
	 */
	private AppCodeEnum appCode;
	/**
	 * 平台编码
	 */
	private SourceCodeEnum sourceCode;
	/**
	 * 字符集
	 */
	private String charset;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public AppCodeEnum getAppCode() {
		return appCode;
	}

	public void setAppCode(AppCodeEnum appCode) {
		this.appCode = appCode;
	}

	public SourceCodeEnum getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(SourceCodeEnum sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	@Override
	public String toString() {
		return "BaseRequestDto [version=" + version + ", appCode=" + appCode + ", sourceCode=" + sourceCode
				+ ", charset=" + charset + ", toString()=" + super.toString() + "]";
	}

}
