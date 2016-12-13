//贷款流程 个人信息和店铺信息js
$(function() {
        var xm = /^[\u4E00-\u9FA5]{2,4}$/;
        var sjh = /^1\d{10}$/; 
        var sfz = /^\d{17}(\d|x)$/;
        
        var step= $("#myStep").step();
        
        $("#preBtn").click(function(event) {
            var yes=step.preStep();//涓婁竴姝�
        });
        $("#preBtn1").click(function(event) {
            var yes=step.preStep();//涓婁竴姝�
        });
        $("#nextBtn").click(function(event) {
            if(!xm.test($("#xinming").val())){
                $("#xinming").val("").focus();
            }else{
                if(!sjh.test($("#phone").val())){
                    $("#phone").val("").focus();
                }else{
                    if(!sfz.test($("#shengfeng").val())){
                        $("#shengfeng").val("").focus();
                    }else{
                        if($("#juzhudizhi").val()==""){
                            $("#juzhudizhi").val("").focus();
                        }else{
                            if(($("#marriage option:selected").val())!=0){
                                if(($("#education option:selected").val())!=0){
                                    var yes=step.nextStep();
                                }else{
                                    /*$("#education").focus();*/
                                }
                            }else{
                                /*$("#marriage").focus();*/
                            }                            
                        }
                    }
                }
            }
        });
        //下一步颜色判定
        $("#xinming").keyup(function(){
            if($("#xinming").val()!=""
            &&$("#phone").val()!=""
            &&$("#shengfeng").val()!=""
            &&$("#juzhudizhi").val()!=""
            &&$("#yingyedizhi").val()!=""
            &&$("#marriage option:selected").val()!=0
            &&$("#education option:selected").val()!=0){
                $("#nextBtn").css("background","#ff7900");
            }else{
                $("#nextBtn").css("background","#c0c0c0");
            }
        });
        $("#phone").change(function(){
            if($("#xinming").val()!=""
            &&$("#phone").val()!=""
            &&$("#shengfeng").val()!=""
            &&$("#juzhudizhi").val()!=""
            &&$("#yingyedizhi").val()!=""
            &&$("#marriage option:selected").val()!=0
            &&$("#education option:selected").val()!=0){
                $("#nextBtn").css("background","#ff7900");
            }else{
                $("#nextBtn").css("background","#c0c0c0");
            }
        });
        $("#shengfeng").change(function(){
            if($("#xinming").val()!=""
            &&$("#phone").val()!=""
            &&$("#shengfeng").val()!=""
            &&$("#juzhudizhi").val()!=""
            &&$("#yingyedizhi").val()!=""
            &&$("#marriage option:selected").val()!=0
            &&$("#education option:selected").val()!=0){
                $("#nextBtn").css("background","#ff7900");
            }else{
                $("#nextBtn").css("background","#c0c0c0");
            }
        });
        $("#juzhudizhi").change(function(){
            if($("#xinming").val()!=""
            &&$("#phone").val()!=""
            &&$("#shengfeng").val()!=""
            &&$("#juzhudizhi").val()!=""
            &&$("#yingyedizhi").val()!=""
            &&$("#marriage option:selected").val()!=0
            &&$("#education option:selected").val()!=0){
                $("#nextBtn").css("background","#ff7900");
            }else{
                $("#nextBtn").css("background","#c0c0c0");
            }
        });
        $("#yingyedizhi").change(function(){
            if($("#xinming").val()!=""
            &&$("#phone").val()!=""
            &&$("#shengfeng").val()!=""
            &&$("#juzhudizhi").val()!=""
            &&$("#yingyedizhi").val()!=""
            &&$("#marriage option:selected").val()!=0
            &&$("#education option:selected").val()!=0){
                $("#nextBtn").css("background","#ff7900");
            }else{
                $("#nextBtn").css("background","#c0c0c0");
            }
        });
        $("#marriage").change(function(){
            if($("#xinming").val()!=""
            &&$("#phone").val()!=""
            &&$("#shengfeng").val()!=""
            &&$("#juzhudizhi").val()!=""
            &&$("#marriage option:selected").val()!=0
            &&$("#education option:selected").val()!=0){
                $("#nextBtn").css("background","#ff7900");
            }else{
                $("#nextBtn").css("background","#c0c0c0");
            }
        });
        $("#education").change(function(){
            if($("#xinming").val()!=""
            &&$("#phone").val()!=""
            &&$("#shengfeng").val()!=""
            &&$("#juzhudizhi").val()!=""
            &&$("#marriage option:selected").val()!=0
            &&$("#education option:selected").val()!=0){
                $("#nextBtn").css("background","#ff7900");
            }else{
                $("#nextBtn").css("background","#c0c0c0");
            }
        });
        
        
        $("#applyBtn").click(function(event) {
            var yes=step.nextStep();

        });
        
      //下一步颜色判定
        $("#openYear").change(function(){
            if($("#openMonth option:selected").val()!=0
            &&$("#nianxiaoshou").val()!=""
            &&$("#yuexiaoshou").val()!=""
            &&$("#kedan").val()!=""
            &&$("#danyueshuidian").val()!=""
            &&$("#danyuefangzu").val()!=0
            &&$("#danyueyuangong").val()!=0
            &&$("#percentage").val()!=0){
                $("#submitBtn").css("background","#ff7900");
            }else{
                $("#submitBtn").css("background","#c0c0c0");
            }
        });
        $("#openMonth").change(function(){
            if($("#openYear option:selected").val()!=0
            	&&$("#nianxiaoshou").val()!=""
                &&$("#yuexiaoshou").val()!=""
                &&$("#kedan").val()!=""
                &&$("#danyueshuidian").val()!=""
                &&$("#danyuefangzu").val()!=0
                &&$("#danyueyuangong").val()!=0
                &&$("#percentage").val()!=0){
                $("#submitBtn").css("background","#ff7900");
            }else{
                $("#submitBtn").css("background","#c0c0c0");
            }
        });
        //年销售额
        $("#nianxiaoshou").change(function(){
        	if($("#openYear option:selected").val()!=0
        			&&$("#openMonth option:selected").val()!=0
                    &&$("#yuexiaoshou").val()!=""
                    &&$("#kedan").val()!=""
                    &&$("#danyueshuidian").val()!=""
                    &&$("#danyuefangzu").val()!=0
                    &&$("#danyueyuangong").val()!=0
                    &&$("#percentage").val()!=0){
                    $("#submitBtn").css("background","#ff7900");
                }else{
                    $("#submitBtn").css("background","#c0c0c0");
                }
        });
        //月销售额
        $("#yuexiaoshou").change(function(){
        	if($("#openYear option:selected").val()!=0
        			&&$("#openMonth option:selected").val()!=0
                    &&$("#nianxiaoshou").val()!=""
                    &&$("#kedan").val()!=""
                    &&$("#danyueshuidian").val()!=""
                    &&$("#danyuefangzu").val()!=0
                    &&$("#danyueyuangong").val()!=0
                    &&$("#percentage").val()!=0){
                    $("#submitBtn").css("background","#ff7900");
                }else{
                    $("#submitBtn").css("background","#c0c0c0");
                }
        });
        //客单价
        $("#kedan").change(function(){
        	if($("#openYear option:selected").val()!=0
        			&&$("#openMonth option:selected").val()!=0
                    &&$("#nianxiaoshou").val()!=""
                    &&$("#yuexiaoshou").val()!=""
                    &&$("#danyueshuidian").val()!=""
                    &&$("#danyuefangzu").val()!=0
                    &&$("#danyueyuangong").val()!=0
                    &&$("#percentage").val()!=0){
                    $("#submitBtn").css("background","#ff7900");
                }else{
                    $("#submitBtn").css("background","#c0c0c0");
                }
        });
        //单月水电费用
        $("#danyueshuidian").change(function(){
        	if($("#openYear option:selected").val()!=0
        			&&$("#openMonth option:selected").val()!=0
                    &&$("#nianxiaoshou").val()!=""
                    &&$("#yuexiaoshou").val()!=""
                    &&$("#kedan").val()!=""
                    &&$("#danyuefangzu").val()!=0
                    &&$("#danyueyuangong").val()!=0
                    &&$("#percentage").val()!=0){
                    $("#submitBtn").css("background","#ff7900");
                }else{
                    $("#submitBtn").css("background","#c0c0c0");
                }
        });
        //单月房租费用
        $("#danyuefangzu").change(function(){
        	if($("#openYear option:selected").val()!=0
        			&&$("#openMonth option:selected").val()!=0
                    &&$("#nianxiaoshou").val()!=""
                    &&$("#yuexiaoshou").val()!=""
                    &&$("#kedan").val()!=""
                    &&$("#danyueshuidian").val()!=0
                    &&$("#danyueyuangong").val()!=0
                    &&$("#percentage").val()!=0){
                    $("#submitBtn").css("background","#ff7900");
                }else{
                    $("#submitBtn").css("background","#c0c0c0");
                }
        });
        //单月员工工资
        $("#danyueyuangong").change(function(){
        	if($("#openYear option:selected").val()!=0
        			&&$("#openMonth option:selected").val()!=0
                    &&$("#nianxiaoshou").val()!=""
                    &&$("#yuexiaoshou").val()!=""
                    &&$("#kedan").val()!=""
                    &&$("#danyueshuidian").val()!=0
                    &&$("#danyuefangzu").val()!=0
                    &&$("#percentage").val()!=0){
                    $("#submitBtn").css("background","#ff7900");
                }else{
                    $("#submitBtn").css("background","#c0c0c0");
                }
        });
        //食材占比
        $("#percentage").change(function(){
        	if($("#openYear option:selected").val()!=0
        			&&$("#openMonth option:selected").val()!=0
                    &&$("#nianxiaoshou").val()!=""
                    &&$("#yuexiaoshou").val()!=""
                    &&$("#kedan").val()!=""
                    &&$("#danyueshuidian").val()!=0
                    &&$("#danyuefangzu").val()!=0
                    &&$("#danyueyuangong").val()!=0){
                    $("#submitBtn").css("background","#ff7900");
                }else{
                    $("#submitBtn").css("background","#c0c0c0");
                }
        });
        $("#submitBtn").click(function(event) { 
        	if($("#openYear option:selected").val()!=0){
        		if($("#openMonth option:selected").val()!=0){
        			if($("#nianxiaoshou").val()!=""){
                        if($("#yuexiaoshou").val()!=""){
                            if($("#kedan").val()!=""){
                                if($("#danyueshuidian").val()!=""){
                                    if($("#danyuefangzu").val()!=""){
                                        if($("#danyueyuangong").val()!=""){
                                            if($("#percentage").val()!=""){
                                                var yes=step.nextStep();
                                            }else{
                                                $("#percentage").focus();
                                            }
                                        }else{
                                            $("#danyueyuangong").focus();
                                        }
                                    }else{
                                        $("#danyuefangzu").focus();
                                    }
                                }else{
                                    $("#danyueshuidian").focus();
                                }
                            }else{
                                $("#kedan").focus();
                            }
                        }else{
                            $("#yuexiaoshou").focus();
                        }
                    }else{
                        $("#nianxiaoshou").focus();
                    }
            	}else{
            		$("#openMonth").focus();
            	}
        	}else{
        		$("#openYear").focus();
        	}
        });
    });