package com.gxkj.taobaoservice.enums;

public enum BenefitTypes {
		FREE("免费"),
	 	PING_TAI("平台"),
	 	FA_REN_WU_ZHE("发任务者"),
	 	JIE_REN_WU_ZHE("接任务者");
		
		private String name; 
		private BenefitTypes(String name ) {  
			       this.name = name; 
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
}
