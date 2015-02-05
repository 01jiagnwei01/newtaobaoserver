package com.gxkj.taobaoservice.enums;

import java.math.BigDecimal;

/**
 * 
 * 好评时限
 *
 */
public enum GoodCommentTimeLimits {
	
	IMMEDIATELY ("立刻好评",BigDecimal.ZERO),
	THIRTYMMinuteLater ("30分钟后好评%f个发布点",new BigDecimal("0.5")),
	ONE_DAY_LATER ("1天后好评%f个发布点",new BigDecimal("0.6")),
	TWO_DAY_LATER ("2天后好评  %f个发布点",new BigDecimal("0.7")),
	THREE_DAY_LATER ("3天后好评 %f个发布点",new BigDecimal("0.8")),
	FOURE_DAY_LATER ("4天后好评 %f个发布点",new BigDecimal("0.9")),
	FIVE_DAY_LATER ("5天后好评%d个发布点",new BigDecimal("1")),
	SIX_DAY_LATER ("6天后好评 %f个发布点",new BigDecimal("1.1")),
	SEVEN_DAY_LATER ("7天后好评%f个发布点",new BigDecimal("1.2"));
	
	private String name; 
	
	private BigDecimal points;
	
	private GoodCommentTimeLimits(String name, BigDecimal points) {  
		       this.name = name; 
		       this.points = points;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPoints() {
		return points;
	}
	public void setPoints(BigDecimal points) {
		this.points = points;
	}
	

}
