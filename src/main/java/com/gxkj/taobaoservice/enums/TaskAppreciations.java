package com.gxkj.taobaoservice.enums;
/**
 * 
 * 增值业务
 *  不建议修改ID
 */
public enum TaskAppreciations {
	
	
	FIND_PASSWORD_SEND_EMAIL(1,"店铺和宝贝双收藏","求接手方收藏您店铺和宝贝，需额外支付{needDot}个发布点奖励给接手方",
			1,1,TaskAppreciationTypes.DIAN_PU_AND_BAO_BEI_SHUANG_FANG_SHOU_CANG,BenefitTypes.JIE_REN_WU_ZHE),
	SHANG_JIA_BAO_ZHANG(2,"商家保障","要求接手人缴纳保证金加入平台商保服务的用户才可以接手此任务，需额外支付{needDot}个发布点奖励给接手方",
					0,2,TaskAppreciationTypes.SHANG_JIA_BAO_ZHENG,BenefitTypes.PING_TAI),
	ZHI_DING_SHOU_HUO_DI_ZHI(3,"指定收货地址","要求接手方拍下宝贝时收货地址按上面指定的收货地址填写，需额外支付{needDot}个发布点奖励给接手方",
							1,1,TaskAppreciationTypes.ZHI_DING_SHOU_HUO_DI_ZHI,BenefitTypes.JIE_REN_WU_ZHE),
	XIAN_ZHI_YONG_HU_BU_CHONG_FU(4,"限制用户不重复接手","限制同一天内接手人不允许重复接手相同掌柜的任务，需额外支付{needDot}个发布点",
									1,0.5,TaskAppreciationTypes.XIAN_ZHI_YONG_HU_BU_CHONG_FU_JIE_SHOU,BenefitTypes.PING_TAI),
	JI_FEN_BU_DI_YU(5,"经验积分","需额外支付{needDot}个发布点奖",
											1,0.5,TaskAppreciationTypes.JI_FEN_BU_DI_YU,BenefitTypes.PING_TAI),
	BEI_LA_RU_HEI_MING_DAN(6,"拉入黑名单次数","需额外支付{needDot}个发布点奖",
													1,0.5,TaskAppreciationTypes.BEI_LA_RU_HEI_MING_DAN_BU_GAO_YU,BenefitTypes.PING_TAI),
	ZHI_DING_JIE_SHOU_REN(7,"指定任务接手人","需额外支付{needDot}个发布点奖",
			1,1,TaskAppreciationTypes.ZHI_DING_JIE_SHOU_REN,BenefitTypes.PING_TAI),
	PI_LIANG_FA_BU(8,"批量发布任务","需额外支付{needDot}个发布点奖",
					1,1,TaskAppreciationTypes.PI_LIANG_FA_BU,BenefitTypes.PING_TAI),
	NEED_WANGWANG_TALK(9,"需要旺旺聊天","需额外支付{needDot}个发布点奖",
							1,1,TaskAppreciationTypes.PI_LIANG_FA_BU,BenefitTypes.PING_TAI)
													;
	
	private int id; 
	
	/**
	 * 任务标题
	 */
	private String taskTitle;
	
	/**
	 * 任务描述
	 */
	private String  taskDesc;
	
	/**
	 * 可用|不可用  
	 */
	private int enable;
	
	/**
	 * 需要点数
	 */
	private double needDot;
	
	/*
	 * 任务类型
	 */
	private TaskAppreciationTypes type;
	
	/**
	 * 收益人
	 */
	private BenefitTypes benefitType;
	
	//private 
	
	private TaskAppreciations(int id,String taskTitle,String taskDesc,int enable,double needDot,TaskAppreciationTypes type,BenefitTypes benefitType ) {  
	       this.id = id; 
	       this.taskTitle = taskTitle;
	       this.taskDesc = taskDesc;
	       this.enable = enable;
	       this.needDot = needDot;
	       this.type = type;
	       this.benefitType = benefitType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public double getNeedDot() {
		return needDot;
	}

	public void setNeedDot(double needDot) {
		this.needDot = needDot;
	}

	public TaskAppreciationTypes getType() {
		return type;
	}

	public void setType(TaskAppreciationTypes type) {
		this.type = type;
	}

	public BenefitTypes getBenefitType() {
		return benefitType;
	}

	public void setBenefitType(BenefitTypes benefitType) {
		this.benefitType = benefitType;
	}
	
}
