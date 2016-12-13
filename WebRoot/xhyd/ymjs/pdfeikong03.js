$(function () { 
	(function($){
		$.getUrlParam
		 = function(name)
		{
		var reg
		 = new RegExp("(^|&)"+
		 name +"=([^&]*)(&|$)");
		var r
		 = window.location.search.substr(1).match(reg);
		if (r!=null) return unescape(r[2]); return null;
		}      
	})(jQuery);
function mydecode(name){
var url = window.location.search;
var length=name.length+1;
var loc2=url.substring(url.lastIndexOf(name)+length, url.length);
var end2=loc2.indexOf("&");
var key=loc2.substring(0,end2);
return  decodeURIComponent(decodeURIComponent(key));

}
	$("#sqjk").click(function(){	
		var saasid = document.getElementById("saasid").innerHTML;
		var saasmc = document.getElementById("saasmc").innerHTML;
		var jine = $(".form-control").val();
		var fenqi = $("#city option:selected").val();
		var dianmianid = mydecode('dianmianid');
		var dianmianname = mydecode('dianmianname');
		var man = mydecode('man');
		var phone = mydecode('phone');
		if(jine>=1&&jine<=50){
			jine = jine*10000;
			if(fenqi!=0){
				if($("input[type='checkbox']").is(':checked')){
					//保存pre
					$.ajax({
						type : "get",
						url : "../services/savepre",
						data : {
							saas : saasid,
							saasmc   : saasmc,
							dianmianname   : dianmianname,
							dianmianid     : dianmianid,
							man : man,
							phone : phone,
							jine : jine
						},
						dataType : "json",
						success : function(data) {
							if(data.result=="y"){
								var url ="process.html?jine="+jine+"&fenqi="+$("#city option:selected").text()+"&dianmianid="+dianmianid+"&dianmianname="+dianmianname+"&saasid="+saasid+"&saasmc="+saasmc+"&";
								window.location.href=url;							
							}else{
								window.location.href="error.html";
							}
						},
						error : function(data){
							window.location.href="bug.html";
						}
					});
					
				}else{
					$("#lb").css("color","red");
				}
			}else{
				/*$("#city").focus();*/
			}
		}else{
			$("#sqjk").css("background","#c0c0c0");
			$("#dkje").val("").focus();
		}
	});
	
	$(".form-control").keyup(function(){
		if($(".form-control").val()!=""&&$("#city option:selected").val()!=0&&$("input[type='checkbox']").is(':checked')){
			$("#sqjk").css("background","#ff7900");
		}else{
			$("#sqjk").css("background","#c0c0c0");
		}
	});
	$("#lb").change(function(){
		$("#lb").css("color","");
		if($(".form-control").val()!=""&&$("#city option:selected").val()!=0&&$("input[type='checkbox']").is(':checked')){
			$("#sqjk").css("background","#ff7900");
		}else{
			$("#sqjk").css("background","#c0c0c0");
		}
	}); 
	
	$("#city").change(function(){
		if($(".form-control").val()!=""&&$("#city option:selected").val()!=0&&$("input[type='checkbox']").is(':checked')){
			$("#sqjk").css("background","#ff7900");
		}else{
			$("#sqjk").css("background","#c0c0c0");
		}
	});
});
