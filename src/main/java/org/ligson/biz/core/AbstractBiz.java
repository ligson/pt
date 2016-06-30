package org.ligson.biz.core;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.ligson.biz.core.base.BaseRequestDto;
import org.ligson.biz.core.base.BaseResponseDto;
import org.ligson.biz.core.base.Result;
import org.ligson.biz.core.common.handler.DataStoreHandler;
import org.ligson.biz.core.model.Context;
import org.ligson.biz.core.user.enums.FailureCodeEnum;
import org.ligson.biz.core.utils.JodaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;


/**
 * 抽象业务类
 */
public abstract class AbstractBiz<Q extends BaseRequestDto, R extends BaseResponseDto> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractBiz.class);

	@Autowired
	protected DataStoreHandler dataStoreHandler;

	/**
	 * 初始化通用信息：
	 */
	public void init(Context context) {
		context.setBusinessDate(JodaUtils.currentDateToInt());
		context.setCurrentDate(JodaUtils.nowAsDate());
		before(context);
	}

	/**
	 * 核心业务入口方法
	 *
	 * @param dto
	 */
	@SuppressWarnings("rawtypes")
	public abstract Result<R> operation(Q dto);

	/**
	 * 前置方法
	 */
	public abstract void before(Context context);

	/**
	 * 参数检查
	 */
	public abstract Boolean paramCheck(Context context);

	/**
	 * 业务检查
	 */
	public abstract Boolean bizCheck(Context context);

	/**
	 * 数据预处理
	 */
	public abstract Boolean txnPreProcessing(Context context);

	/**
	 * 数据存储
	 */
	public abstract Boolean persistence(Context context);

	/**
	 * 后置方法
	 *
	 * @param context
	 */
	public abstract void after(Context context);

	@SuppressWarnings("rawtypes")
	public Result<R> result(Context context) {

		try {
			// 初始化
			init(context);
			// 交易处理
			operate(context);

		} catch (Throwable e) {
			logger.error("", e);
			if (context.getResult() == null) {
				context.setResult(Result.getFailureResult(FailureCodeEnum.SERVICE_EXCEPTION.getCode(),
						FailureCodeEnum.SERVICE_EXCEPTION.getMsg()));
			}
		} finally {
			Assert.notNull(context.getResult(), "返回结果不能为空");
			try {
				// 后置业务处理
				after(context);
			} catch (Exception e) {
				logger.error("后置业务处理异常.", e);
			}
		}

		return context.getResult();
	}

	/**
	 * 业务处理
	 *
	 * @param context
	 * @return
	 */
	private boolean operate(Context context) {
		Boolean paramCheck = null;
		Boolean bizCheck = null;
		Boolean txnPreProcessing = null;
		Boolean persistence = null;
		paramCheck = paramCheck(context);
		if (paramCheck != null && !paramCheck) {
			return false;
		}
		bizCheck = bizCheck(context);
		if (bizCheck != null && !bizCheck) {
			return false;
		}
		txnPreProcessing = txnPreProcessing(context);
		if (txnPreProcessing != null && !txnPreProcessing) {
			return false;
		}
		persistence = persistence(context);
		if (persistence != null && !persistence) {
			return false;
		}
		return true;
	}

	/**
	 * 异常消息截取
	 */
	@SuppressWarnings("unused")
	private String subErrorMsg(String msg) {
		if (msg.length() > 3500) {
			return msg.toString().substring(0, 3500);
		} else {
			return msg.toString().substring(0, msg.length());
		}
	}

	/**
	 * 获取异常的堆栈信息
	 *
	 * @param t
	 * @return
	 */
	protected String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		try {
			t.printStackTrace(pw);
			String s = sw.toString();
			if (s.length() > 3500) {
				return sw.toString().substring(0, 3500);
			} else {
				return sw.toString().substring(0, s.length());
			}

		} catch (Exception e) {

		} finally {
			pw.close();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	private Class getGenericType(int index) {
		Type genType = getClass().getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			throw new RuntimeException("Index outof bounds");
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	private String getGenericTypeName(int index) {
		return getGenericType(index).getSimpleName();
	}

	public void setSuccessResult(Context context, R responseDto) {
		Result<R> responseDtoResult = Result.getSuccessResult(responseDto);
		context.setResult(responseDtoResult);
	}

	public void setRequestDto(Context context, BaseRequestDto requestDto) {
		context.setAttr("requestDto", requestDto);
	}

	public Q getRequestDto(Context context) {
		Object object = context.getAttr("requestDto");
		if (object != null) {
			return (Q) object;
		}
		return null;
	}

	public R getResponseDto(Context context) {
		Object object = context.getAttr("responseDto");
		if (object != null) {
			return (R) object;
		} else {
			Class<R> rCls = getGenericType(1);
			try {
				R r = rCls.newInstance();
				context.setAttr("responseDto", r);
				return r;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
