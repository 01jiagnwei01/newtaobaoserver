package com.gxkj.taobaoservice.enums;

public enum TaskAppreciationTypes {

	DIAN_PU_AND_BAO_BEI_SHUANG_FANG_SHOU_CANG("店铺和宝贝双收藏"),
	SHANG_JIA_BAO_ZHENG("商家保证"),
	ZHI_DING_SHOU_HUO_DI_ZHI("指定收货地址"),
	XIAN_ZHI_YONG_HU_BU_CHONG_FU_JIE_SHOU("限制用户不重复接手"),
	JI_FEN_BU_DI_YU("积分不低于"),
	BEI_LA_RU_HEI_MING_DAN_BU_GAO_YU("被拉黑名单数不大于"),
	ZHI_DING_JIE_SHOU_REN("指定任务接手人"),
	PI_LIANG_FA_BU("批量发布任务"),
	SAVE_TEMPLETATE("保存模板"),
	NEED_WANGWANG_TALK("需要旺旺聊天");

	private String name; 
	
	private TaskAppreciationTypes(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
