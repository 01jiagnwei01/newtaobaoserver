if(0==0 || (0==1 && checkDate57('2011-1-4'))){
document.write("<span onclick=\"addHits57(0,96)\"><a href=\"/dsr/index.html\" target=\"_blank\"><img  alt=\"刷动态评分\"  border=\"0\"  height=50  width=510  src=\"/style/images/Temp_AD/help-ad-1.gif\"></a></span>&nbsp;");
}
if(0==0 || (0==1 && checkDate57('2011-1-4'))){
document.write("<span onclick=\"addHits57(0,97)\"><a href=\"/html/ptgg/4221.html\" target=\"_blank\"><img  alt=\"刷信誉快递单号\"  border=\"0\"  height=50  width=440  src=\"/style/images/Temp_AD/help-ad-2.gif\"></a></span>&nbsp;");
}
function addHits57(c,id){if(c==1){try{jQuery.getScript('http://www.shuakewang.com/plus/ajaxs.asp?action=HitsGuangGao&id='+id,function(){});}catch(e){}}}
function checkDate57(date_arr){
 var date=new Date();
 date_arr=date_arr.split("-");
var year=parseInt(date_arr[0]);
var month=parseInt(date_arr[1])-1;
var day=0;
if (date_arr[2].indexOf(" ")!=-1)
day=parseInt(date_arr[2].split(" ")[0]);
else
day=parseInt(date_arr[2]);
var date1=new Date(year,month,day);
if(date.valueOf()>date1.valueOf())
 return false;
else
 return true
}
