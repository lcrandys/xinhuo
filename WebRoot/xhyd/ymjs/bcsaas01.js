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
return decodeURIComponent(decodeURIComponent(key));

}	
	var dianmianname =mydecode('dianmianname');
	var dianmianid =  mydecode('dianmianid');
	console.log(dianmianname);
	//后台保存saas
	$.ajax({
		type : "get",
		url : "../services/fangwen",
		data : {
			saasmc : document.getElementById("saasmc").innerHTML,
			saas   : document.getElementById("saasid").innerHTML,
			dianmianname   : dianmianname,
			dianmianid     : dianmianid,
		},
		dataType : "json",
		success : function(data) {			
		}
	});
});
