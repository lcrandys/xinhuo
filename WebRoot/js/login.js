//用户首页登入
$(document).ready(function() {
	$(".btn").click(function(){
		var username = $("#u").val();
		var password = $("#p").val();
		
		$.ajax({
			type : "get",
			url : "services/getSession",
			data : {
				usr : username,
				pass : password,
			},
			dataType : "json",
			success : function(data) {
				document.cookie="session=1122";
				window.location.href="index.html";
			},
			error : function(data){
				alert("登入失败，请重试！");
			}	
		});
	});
});
