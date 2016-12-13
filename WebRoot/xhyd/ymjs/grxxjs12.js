//贷款流程 资产信息js
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
    //房下拉
    $("#home").change(function(){
        var home =$("#home option:selected").val();
        var car = $("#car option:selected").val();
        var credit =$("#credit option:selected").val();
        var shops =$("#shops option:selected").val();
        if(home!=0&&car!=0&&credit!=0&&shops!=0){
            $(".btn-tj").css("background","#ff7900");
        }else{
            $(".btn-tj").css("background","#c0c0c0");
        }
    });
    //车下拉
    $("#car").change(function(){
        var home =$("#home option:selected").val();
        var car = $("#car option:selected").val();
        var credit =$("#credit option:selected").val();
        var shops =$("#shops option:selected").val();
        if(home!=0&&car!=0&&credit!=0&&shops!=0){
            $(".btn-tj").css("background","#ff7900");
        }else{
            $(".btn-tj").css("background","#c0c0c0");
        }
    });
    //信用下拉
    $("#credit").change(function(){
        var home =$("#home option:selected").val();
        var car = $("#car option:selected").val();
        var credit =$("#credit option:selected").val();
        var shops =$("#shops option:selected").val();
        if(home!=0&&car!=0&&credit!=0&&shops!=0){
            $(".btn-tj").css("background","#ff7900");
        }else{
            $(".btn-tj").css("background","#c0c0c0");
        }
    });
    //商铺下拉
    $("#shops").change(function(){
        var home =$("#home option:selected").val();
        var car = $("#car option:selected").val();
        var credit =$("#credit option:selected").val();
        var shops =$("#shops option:selected").val();
        if(home!=0&&car!=0&&credit!=0&&shops!=0){
            $(".btn-tj").css("background","#ff7900");
        }else{
            $(".btn-tj").css("background","#c0c0c0");
        }
    });
    
    $("#tijiao").click(function(){
        var jine = mydecode('jine');
        var fenqi = mydecode('fenqi');
        var dianpu = mydecode('dianmianname');
        var dianpuid = mydecode('dianmianid');
        var saasid = mydecode('saasid');
        var saasmc = mydecode('saasmc');
        var xinming = $("#xinming").val();
        var shengfeng = $("#shengfeng").val();
        var juzhudizhi = $("#juzhudizhi").val();
        var yingyedizhi = $("#yingyedizhi").val();
        var phone =$("#phone").val();
        var hunyin = $("#marriage option:selected").text();
        var xueli  = $("#education option:selected").text();
        var nianxiaoshou = $("#nianxiaoshou").val();
        var yuexiaoshou = $("#yuexiaoshou").val();
        var kedan = $("#kedan").val();
        var danyueshuidian = $("#danyueshuidian").val();
        var danyueyuangong = $("#danyueyuangong").val();
        var danyuefangzu = $("#danyuefangzu").val();
        var percentage = $("#percentage").val();//原材料成本占月流水百分比
        var openYear = $("#openYear option:selected").val();//开业年
        var openMonth = $("#openMonth option:selected").val();//开业月
        var fang = $("#home option:selected").val();
        var che = $("#car option:selected").val();
        var xinyongqingkuang = $("#credit option:selected").val();
        var shangpu =$("#shops option:selected").val();
        if(fang!=0&&che!=0&&credit!=0&&shangpu!=0){
                        
                            $.ajax({
                                type : "get",
                                url : "../services/submitDaikuanshenqing",
                                data : {
                                    jine      :   jine,
                                    fenqi     :   fenqi,
                                    xinming   :  xinming,
                                    shengfeng :  shengfeng,
                                    phone     :  phone,
                                    juzhudizhi:   juzhudizhi,
                                    yingyedizhi:  yingyedizhi,
                                    hunyin    :  hunyin,
                                    xueli     :  xueli,
                                    nianxiaoshou  :  nianxiaoshou,
                                    yuexiaoshou   :  yuexiaoshou,
                                    kedan     :  kedan,
                                    danyueshuidian  :  danyueshuidian,
                                    danyueyuangong  :  danyueyuangong,
                                    danyuefangzu    :  danyuefangzu,
                                    percentage:percentage,
                                    openYear:$("#openYear option:selected").text(),
                                    openMonth:$("#openMonth option:selected").text(),
                                    fang   : $("#home option:selected").text(),
                                    che   :  $("#car option:selected").text(),
                                    shangpu   :  $("#shops option:selected").text(),
                                    xinyongqingkuang   :  $("#credit option:selected").text(),
                                    dianpuname   : dianpu,
                                    dianpuid : dianpuid,
                                    saasid   : saasid,
                                    saasmc : saasmc
                                },
                                dataType : "json",
                                success : function(data) {
                                    window.location.href="review.html";
                                },
                                error : function(data) {
                                    
                                }
                            });  
        }
        
    });  
