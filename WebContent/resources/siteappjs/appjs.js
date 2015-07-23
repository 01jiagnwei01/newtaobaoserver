/**
 * 
 */
function addLookNum(url){
	$.ajax({
	  	  type:'post',
		  url: url,
		  context: document.body,
		  data:{},
		  success:function(json){
		  },
		  error:function(xhr,textStatus,errorThrown){
		  } 
	});
}
function praiseOrTireFn(baseurl,type,id,textid){
	var url= null;
	if(type=='praise'){
		url = baseurl+"/story/praise/"+id;
	}else if(type=='tire'){
		url = baseurl+"/story/tire/"+id;
	}
	if(url== null)return;
	$.ajax({
	  	  type:'post',
		  url: url,
		  context: document.body,
		  data:{},
		  success:function(json){
			  if(json.result){
				  var res = json.entity;
				  //alert(textid);
				  $("#"+textid).html(res);
			  }
		  },
		  error:function(xhr,textStatus,errorThrown){
		  } 
	});
}