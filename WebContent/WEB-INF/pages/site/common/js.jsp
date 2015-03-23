<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/resources/js/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/resources/js/sonline.js"></script>
<script type="text/javascript">
<!--
function addCookie(name,value,expiresHours){ 
	var cookieString=name+"="+escape(value); 
	//判断是否设置过期时间 
	if(expiresHours && expiresHours>0){ 
	var date=new Date(); 
	date.setTime(date.getTime+expiresHours*3600*1000); 
	cookieString=cookieString+"; expires="+date.toGMTString(); 
	} 
	document.cookie=cookieString; 
} 
function getCookie(name){ 
	var strCookie=document.cookie; 
	var arrCookie=strCookie.split("; "); 
	for(var i=0;i<arrCookie.length;i++){ 
	var arr=arrCookie[i].split("="); 
	if(arr[0]==name)return arr[1]; 
	} 
	return ""; 
}
function deleteCookie(name){ 
	var date=new Date(); 
	date.setTime(date.getTime()-10000); 
	document.cookie=name+"=v; expires="+date.toGMTString(); 
} 
function checkEmail(str){
    var re = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
    if(re.test(str)){
       	return true;
    }else{
       return false;
    }
}
function checkPhone(mobile){
	var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
    var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;

    if(isMob.test(mobile)||isPhone.test(mobile)){
        return true;
    }
    else{
        return false;
    }
}
$(function(){
	/**
	 $("body").Sonline({
		  Position:"right",//left或right
		  Top:200,//顶部距离，默认200px
		  Effect:true, //滚动或者固定两种方式，布尔值：true或false
		  DefaultsOpen:true, //默认展开：true,默认收缩：false
		  Qqlist:"402719549|客服a,402719549|客服b,402719549|客服c,402719549|客服d,402719549|客服e" //多个QQ用','隔开，QQ和客服名用'|'隔开
		});
	*/
})
//-->
</script> 