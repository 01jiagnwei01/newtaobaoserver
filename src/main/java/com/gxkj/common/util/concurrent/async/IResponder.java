package com.gxkj.common.util.concurrent.async;

/**
 * AsyncToken的得到结果后的回调接口
 * @see AsyncToken
 * @author badqiu
 */
public interface IResponder<T> {
	
	public void onResult(T result);
	
	public void onFault(Exception fault);
	
}
