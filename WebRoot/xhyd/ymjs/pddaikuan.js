$(document).ready(function() {
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
return decodeURIComponent(key);
}	
	var dianmianid = mydecode('dianmianid');

//判断是否可以贷款
	$.ajax({
		type : "get",
		url : "../services/yndaikuan",
		data : {
			saas   : document.getElementById("saasid").innerHTML,
			name : dianmianid
		},
		dataType : "json",
		success : function(data) {
			if(data.result!=0){
				window.location.href="bug.html";
			}
		},
		error : function(data) {
			alert("不能贷款");
		}
	});
});
